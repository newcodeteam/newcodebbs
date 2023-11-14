package com.newcodebbs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserDTO;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.dto.UserInfoDTO;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.entity.UserToken;
import com.newcodebbs.mapper.UserDataMapper;
import com.newcodebbs.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newcodebbs.utils.*;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    private UserDataMapper userDataMapper;
    
    //    是否开启邮箱验证 默认为false
    @Value("${email.test:false}")
    private Boolean emailIF;
    //邮箱
    @Autowired
    private MailService mailService;
    
    @Resource
    private IUserTokenService iUserTokenService;
    @Resource
    private IUserTypeService userTypeService;
    
    @Value("${domain.name}")
    private String domain;
    
    
    
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
            String text = MAIL_CONTENT+"<h2>"+code+"</h2>五分钟后过期,请您尽快验证</p>";
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
    public Result loginAndRegister(UserForm userForm) {
        String mail = userForm.getMail();
        // 校验邮箱
        if (RegexUtils.isEmailInvalid(mail)) {
            // 不符合
            return Result.error(RESULT_CODE_ERROR,"邮箱不符合");
        }
        //检测是否携带了验证码
        if (userForm.getCode() == null && userForm.getCode().length() != 6) {
            return Result.error("没有填验证码");
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
//            log.info("test");
            //不存在 直接创建用户
            userData = createUserMail(mail);
            String JwtToken = getToken(userData);
            return Result.Register("注册成功",JwtToken);
            // XXX 已废弃 不存在,直接创建用户  邮箱验证发送注册验证 填密码
//            if (createUserMail(mail) !=null){
//                return Result.Register("注册成功,请从邮箱点击链接进行验证");
//            }
//            return Result.error("注册失败,邮箱错误");
        }
        //存在,判断是否注册验证通过或已被封禁 0是false
        if (!userData.getUserStatus()) {
            return Result.error("账号未通过注册验证或已被封禁");
        }
        String JwtToken = getToken(userData);
        return Result.success(JwtToken);
    }
    
    private String getToken(UserData userData) {
        // 保存用户信息进redis中,并生成jwt将用户基本信息与session返回给前端,同时插入mysql进行持久化处理
        // 随机token 作为登陆令牌
        String token = UUID.randomUUID().toString(true);
        // 将 userData对象转为 hashmap储存
        // 将 userData 对象的数据 转给UserDTO对象
        UserDTO userDTO = BeanUtil.copyProperties(userData,UserDTO.class);
        //获取权限id
        String[] resultData = userTypeService.getTypeDataString(userData.getUserId());
        if (resultData == null) {
            userDTO.setUserType(0);
            userDTO.setUserTypeName("用户组");
        } else {
            userDTO.setUserType(Integer.valueOf(resultData[4]));
            userDTO.setUserTypeName(resultData[3]);
        }
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
        //获取7天后时间
        LocalDateTime localDateTime = LocalDateTimeUtil.localDateTime();
        // 时间戳
        long date = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        //将token进行持久化 7天后过期
        stringRedisTemplate.opsForValue().set(USER_TOKEN_DATA + userData.getUserId(),Long.toString(date),USER_TOKEN_DATA_TTL, TimeUnit.DAYS);
        // 验证完之后颁发token令牌 令牌 7天后过期
        Map<String,Object> jwt =new HashMap<>();
        jwt.put("userId", userData.getUserId());
        jwt.put("userName", userData.getUserName());
        jwt.put("userMail", userData.getUserMail());
        jwt.put("userNickname", userData.getUserNickname());
        jwt.put("userIcon", userData.getUserIcon());
        jwt.put("token",token);
        String JwtToken = JwtUtil.generateJwt(jwt);
        
        // 创建Token实体类 并构造参数
        UserToken userToken = new UserToken(userData.getUserId(),LocalDateTimeUtil.localDateTime());
        // 调用 Token服务类保存token持久化
        iUserTokenService.createToken(userToken);
        return JwtToken;
    }
    
    @Override
    public Result logout(String userId) {
        return null;
    }
    
    @Override
    public Result followed(String myUserId, String userId) {
        return null;
    }
    
    @Override
    public Result cancelFollowed(String myUserId, String userId) {
        return null;
    }
    
    private UserData createUserMail(String mail) {
        // 实例化一个user类
        UserData userData =new UserData();
        // 随机用户id(唯一) ObjectId是MongoDB数据库的一种唯一ID生成策略，是UUID version1的变种
        userData.setUserId(IdUtil.objectId());
        // 随机密码 (用户注册成功后会提示更改) 可选发不发邮件
        userData.setUserPwd(MD5Util.Md5Code(RandomUtil.randomNumbers(10)));
        // 设置默认头像
        userData.setUserIcon(1);
        // 将邮箱传入
        userData.setUserMail(mail);
        // 随机用户名  user_10位数的随机数
        userData.setUserName(USER_SQL_NAME + RandomUtil.randomString(10));
        // 将昵称传入
        userData.setUserNickname(mail);
        //更改状态
        userData.setUserStatus(true);
        // 保存用户 插入数据库 通过邮箱验证不直接插入数据库
        save(userData);
        return userData;
        // XXX 废弃 邮件校验
//        if (sendRegisterURL(userData) == 1) {
//            //成功
//            return userData;
//        } else {
//            //失败
//            return null;
//        }
    }
    
    private int sendRegisterURL(UserData userData){
        // fixme 已经废弃功能
        // redis 临时id
        String sessionId = IdUtil.objectId();
        // 需要发送的邮箱
        String to = userData.getUserMail();
        // 发送主题
        String subject = MAIL_TITLE;
        // 发送内容 域名地址/redis地址/邮箱
        String text = MAIL_REGISTER_HEAD+"<h2>"+domain+"/api/user/"+sessionId+"/"+userData.getUserMail()+"</h2>30分钟后过期,请您尽快验证</p>";
        Map<String, Object> map = MapUtil.newHashMap();
        //  存入临时数据 保存临时用户数据
        map.put("userId",userData.getUserId());
        map.put("userName",userData.getUserName());
        map.put("userMail",userData.getUserMail());
        map.put("userNickname",userData.getUserNickname());
        map.put("userPwd",userData.getUserPwd());
//        map.put("userIcon",(String) ((Object) userData.getUserIcon()));
        // 存入 redis token
        String tokenKey = USER_EMAIL_USER_KEY +sessionId;
        stringRedisTemplate.opsForHash().putAll(tokenKey,map);
        //设置时效 30分钟 从redis过期
        stringRedisTemplate.expire(tokenKey,USER_EMAIL_TTL,TimeUnit.MINUTES);
        //发送验证路径 请在配置文件确认 是否开启了邮箱验证
        if (this.emailIF) {
            log.debug("邮件信息,邮件:{},标题:{},内容:{}",to,subject,text);
            //发送邮件
            if (mailService.sendHtmlMail(to,subject,text) == -1) {
                log.debug("邮箱有问题");
                return -1;
            }
        }
        //不发送邮箱就控制台提示
        log.debug("控制台邮件信息,邮件:{},标题:{},内容:{}",to,subject,text);
        return 1;
    }
    @Override
    public Result registerMail(String redisID, String mail, String password) {
        //验证是否有这个用户
        //临时用户数据地址
        String key = USER_EMAIL_USER_KEY + redisID;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
        // 判断用户是否存在
        if (userMap.isEmpty()) {
            return Result.error(RESULT_CODE_NULL,"注册时间已经过期,请重新注册");
        }
        // 将查询到的hash数据转为Userdata
        UserData userData = BeanUtil.fillBeanWithMap(userMap, new UserData(), false);
        if (!DataUtil.PatternData(password,DataUtil.PASSWORD_PATTERN)) {
            return Result.error("密码不正确,请输入8-16位的密码,并且要包含字母和数字");
        }
        // 更改密码
        userData.setUserPwd(MD5Util.Md5Code(password));
        //更改状态
        userData.setUserStatus(true);
        //保存进数据库
        save(userData);
        //删除数据库临时信息
        stringRedisTemplate.delete(key);
        return Result.success("注册成功");
    }
    
    @Override
    public Result login(UserForm userForm) {
        String mail = userForm.getMail();
        String password = userForm.getPassword();
        // 校验邮箱
        if (RegexUtils.isEmailInvalid(mail)) {
            // 不符合
            return Result.error(RESULT_CODE_ERROR,"邮箱不符合");
        }
        if (!DataUtil.PatternData(password,DataUtil.PASSWORD_PATTERN)) {
            return Result.error("密码不规范,请输入8-16位的密码,并且要包含字母和数字");
        }
        // 根据邮箱查询用户
        UserData userData = query().eq("user_mail",mail).one();
        //判断用户存在
        if (userData == null) {
            //用户不存在
            return Result.error("用户不存在或账号密码错误");
        }
        if (!userData.getUserPwd().equals(MD5Util.Md5Code(password))) {
            //用户不存在
            return Result.error("用户不存在或账号密码错误");
        }
        //存在,判断是否注册验证通过或已被封禁 0是false
        if (!userData.getUserStatus()) {
            return Result.error("账号已被封禁");
        }
        // 保存用户信息进redis中,并生成jwt将用户基本信息与session返回给前端,同时插入mysql进行持久化处理
        // 随机token 作为登陆令牌
        String JwtToken = getToken(userData);
        return Result.success(JwtToken);
    }
    
    @Override
    public Result setPassword(String password, HttpServletRequest httpServletRequest) {
        UserDTO userTokenData = UserHolder.getUser();
        String userId = userTokenData.getUserId();
        // 根据id查询用户
        UserData userData = query().eq("user_id",userId).one();
        log.info("用户状态{}",userData.getUserStatus());
        // 验证用户数据
        if (!userData.getUserStatus()) {
            return Result.error("用户数据已经过期或被封禁");
        }
        if (!DataUtil.PatternData(password,DataUtil.PASSWORD_PATTERN)) {
            return Result.error("密码不规范,请输入8-16位的密码,并且要包含字母和数字");
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("user_id",userId);
        updateWrapper.set("user_pwd",MD5Util.Md5Code(password));
        baseMapper.update(userData,updateWrapper);
        return Result.success("设置密码成功");
    }
    
    @Override
    public UserData userSelectUserIdData(String userId) {
        UserData userData = query().eq("user_id",userId).one();
        userData.setUserPwd("");
        return userData;
    }
    
    @Override
    public UserData userSelectMailData(String mail) {
        UserData userData = query().eq("user_mail",mail).one();
        userData.setUserPwd("");
        return userData;
    }
    
    @Override
    public UserData userSelectNameData(String name) {
        UserData userData = query().eq("user_name",name).one();
        userData.setUserPwd("");
        return userData;
    }
    
    @Override
    public List<?> userSelectNicknameBlurData(String nickname) {
        QueryWrapper<UserData> wrapper = new QueryWrapper<>();
        wrapper.like("user_nickname", nickname);
        return userDataMapper.selectList(wrapper);
    }
    
    @Override
    public List<?> userSelectNameBlurData(String name) {
        QueryWrapper<UserData> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", name);
        return userDataMapper.selectList(wrapper);
    }
    
    @Override
    public List<?> userSelectMailBlurData(String mail) {
        QueryWrapper<UserData> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("user_mail", mail);
        return userDataMapper.selectList(wrapper);
    }
    
    @Override
    public List<?> userSelectNicknameData(String nickname) {
        QueryWrapper<UserData> wrapper = new QueryWrapper<>();
        wrapper.eq("user_nickname",nickname);
        return userDataMapper.selectList(wrapper);
    }
    
    @Override
    public Result deleteUser(String userId) {
        UserDTO userDTO = UserHolder.getUser();
        Integer typeId = userDTO.getUserType();
        if (typeId == 1) {
            try {
                userDataMapper.deleteById(userId);
                return Result.success("删除该"+userId+"成功");
            } catch (Exception e) {
                return Result.error("删除失败，id不存在");
            }
        } else return Result.error("权限不足");
    }
    
    @Override
    public Result disableUser(String userId) {
        UserDTO userDTO = UserHolder.getUser();
        Integer typeId = userDTO.getUserType();
        if (typeId == 1) {
            try {
                userDataMapper.updateById(new UserData().setUserId(userId).setUserStatus(false));
                return Result.success("禁用该"+userId+"成功");
            } catch (Exception e) {
                return Result.error("禁用失败，id不存在");
            }
        } else return Result.error("权限不足");
    }
    
    @Override
    public Result startUser(String userId) {
        UserDTO userDTO = UserHolder.getUser();
        Integer typeId = userDTO.getUserType();
        if (typeId == 1) {
            try {
                userDataMapper.updateById(new UserData().setUserId(userId).setUserStatus(true));
                return Result.success("启用该"+userId+"成功");
            } catch (Exception e) {
                return  Result.error("启用失败，id不存在");
            }
        } else return  Result.error("权限不足");
    }
    
    @Override
    public Result updateUserDataInfo(UserInfoDTO userInfoDTO) {
        //todo 用户更改自己的信息
        UserDTO userDTO = UserHolder.getUser();
        return Result.success();
    }
}
