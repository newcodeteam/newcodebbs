package com.newcodebbs.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.mapper.UserDataMapper;
import com.newcodebbs.service.IUserDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newcodebbs.service.MailService;
import com.newcodebbs.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.concurrent.TimeUnit;

import static com.newcodebbs.Constants.MailConstants.*;
import static com.newcodebbs.Constants.RedisConstants.*;

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
    public Result sendCode(String mail,HttpSession session) {
        // 校验邮箱
        if (RegexUtils.isEmailInvalid(mail)) {
            //不合格
            return Result.error("邮箱格式错误");
        }
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
                return Result.error("邮箱发送失败,请重新注册填写注册");
            }
        }
        log.debug("发送邮箱验证码,验证码{}",code);
        //返回成功
        return Result.success();
    }
    
    @Override
    public Result LoginAndRegister(UserForm userForm, HttpSession session) {
        String mail = userForm.getMail();
        // 校验邮箱
        if (RegexUtils.isEmailInvalid(mail)) {
            // 不符合
            return Result.error("邮箱不符合");
        }
        String mailSession = stringRedisTemplate.opsForValue().get(USER_CODE_KEY + mail);
        String cacheCode = stringRedisTemplate.opsForValue().get(USER_CODE_KEY + mail);
//        if (session.equals())
        // 验证 redis 缓存的验证码是否一致
        return null;
    }
}
