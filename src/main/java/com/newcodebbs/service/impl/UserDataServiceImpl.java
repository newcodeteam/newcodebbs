package com.newcodebbs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserDTO;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.mapper.UserDataMapper;
import com.newcodebbs.service.IUserDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newcodebbs.service.MailService;
import com.newcodebbs.utils.JwtUtil;
import com.newcodebbs.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.newcodebbs.Constants.MailConstants.*;
import static com.newcodebbs.Constants.RedisConstants.*;
import static com.newcodebbs.Constants.ResultConstants.RESULT_CODE_ERROR;
import static com.newcodebbs.Constants.ResultConstants.RESULT_CODE_NULL;
import static com.newcodebbs.Constants.SqlConstants.USER_SQL_NAME;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Slf4j
@Service
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper, UserData> implements IUserDataService {
    
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    //    是否开启邮箱验证 默认为false
    @Value("${email.test:false}")
    private Boolean emailIF;
    //邮箱
    @Autowired
    private MailService mailService;
    
    
    
    @Override
    public Result sendCode(String mail,String session) {
        // 校验邮箱
        if (RegexUtils.isEmailInvalid(mail)) {
            //不合格
            return Result.error(RESULT_CODE_ERROR,"邮箱格式错误");
        }
////        滑块验证码
//        String captchaSession = stringRedisTemplate.opsForValue().get(USER_CAPTCHA_KEY + mail);
//        if (!session.equals(captchaSession)) {
//            return Result.error("未进行滑块验证码验证");
//        }
        // 生成验证码
        String code = RandomUtil.randomNumbers(6);
        //保存验证码进redis 五分钟过期
        stringRedisTemplate.opsForValue().set(USER_CODE_KEY + mail,code,USER_CODE_TTL, TimeUnit.MINUTES);
        //发送验证码 请在配置文件确认 是否开启了邮箱验证
        if (this.emailIF) {
            // 需要发送的邮箱
            String to = mail;
            // 发送主题
            String subject = MAIL_NAME;
            // 发送内容
            String text = MAIL_CONTENT+"<h2>"+code+"<h2>五分钟后过期,请您尽快验证</p>";
            log.debug("邮件信息,邮件:{},标题:{},内容:{}",to,subject,text);
            //发送邮件
            if (mailService.sendHtmlMail(to,subject,text) == -1) {
                log.debug("邮箱有问题");
                return Result.error(RESULT_CODE_ERROR,"邮箱发送失败,请重新注册填写注册");
            }
        }
        log.debug("发送邮箱验证码,验证码{}",code);
        //返回成功
        return Result.success();
    }
    
    @Override
    public Result LoginAndRegister(UserForm userForm) {
        String mail = userForm.getMail();
        // 校验邮箱
        if (RegexUtils.isEmailInvalid(mail)) {
            // 不符合
            return Result.error(RESULT_CODE_ERROR,"邮箱不符合");
        }
        //验证邮箱验证码是否正确
        String mailSession = stringRedisTemplate.opsForValue().get(USER_CODE_KEY + mail);
        if (mailSession == null || !mailSession.equals(userForm.getCode())) {
            // XXX 验证次数限制
            return Result.error(RESULT_CODE_NULL,"邮箱验证码验证失败");
        }
        //根据邮箱查询用户是否存在,如果存在就登陆,不存在则注册
        // 根据邮箱查询用户
        UserData userData = query().eq("user_mail",mail).one();
        log.debug("{}",userData);
        //判断用户存在
        if (userData == null) {
            // 不存在,直接创建用户
            userData = createUserMail(mail);
        }
        // 保存用户信息进redis中,并生成jwt将用户基本信息与session返回给前端,同时插入mysql进行持久化处理
        // 随机token 作为登陆令牌
        String token = UUID.randomUUID().toString(true);
        // 将 userData对象转为 hashmap储存
        // 将 userData 对象的数据 转给UserDTO对象
        UserDTO userDTO = BeanUtil.copyProperties(userData,UserDTO.class);
        // 转为Hashmap存储
        // 将userDTO对象转换为map对象, CopyOptions.create() 是复制一些选项
        // setIgnoreNullValue(true) 忽略空值
        // setFieldValueEditor 修改字段的值,用 lambda表达式将字段的值转换为字符串
        Map<String,Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // 存入 redis token
        String tokenKey = USER_TOKEN_KEY +token;
        stringRedisTemplate.opsForHash().putAll(tokenKey,userMap);
        //设置时效 24小时 从redis过期
        stringRedisTemplate.expire(tokenKey,USER_TOKEN_TTL,TimeUnit.MINUTES);
        // 验证完之后颁发token令牌 令牌 7天后过期
        Map<String,Object> jwt =new HashMap<>();
        jwt.put("userId",userData.getUserId());
        jwt.put("userName",userData.getUserName());
        jwt.put("userMail",userData.getUserMail());
        jwt.put("userNickname",userData.getUserNickname());
        jwt.put("userIcon",userData.getUserIcon());
        jwt.put("token",token);
        String JwtToken = JwtUtil.generateJwt(jwt);
        return Result.success(JwtToken);
    }
    
    private UserData createUserMail(String mail) {
        // 实例化一个user类
        UserData userData =new UserData();
        // 随机用户id(唯一) ObjectId是MongoDB数据库的一种唯一ID生成策略，是UUID version1的变种
        userData.setUserId(IdUtil.objectId());
        // 随机密码 (用户注册成功后会提示更改)
        userData.setUserPwd(RandomUtil.randomNumbers(10));
        // 设置默认头像
        userData.setUserIcon(1);
        // 将邮箱传入
        userData.setUserMail(mail);
        // 随机用户名  user_10位数的随机数
        userData.setUserName(USER_SQL_NAME + RandomUtil.randomString(10));
        // 将昵称传入
        userData.setUserNickname(mail);
        // 保存用户 插入数据库
        save(userData);
        return userData;
    }
}
