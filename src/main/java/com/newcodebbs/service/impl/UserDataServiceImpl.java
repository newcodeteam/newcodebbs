package com.newcodebbs.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.mapper.UserDataMapper;
import com.newcodebbs.service.IUserDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newcodebbs.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

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
    
    @Override
    public Result sendCode(String mail) {
        // 校验邮箱
        if (RegexUtils.isEmailInvalid(mail)) {
            //不合格
            return Result.error("邮箱格式错误");
        }
        // 生成验证码
        String code = RandomUtil.randomNumbers(6);
        //保存验证码进redis 保存临时session 五分钟过期
        stringRedisTemplate.opsForValue().set(USER_CODE_KEY + mail,code,USER_CODE_TTL, TimeUnit.MINUTES);
        //发送验证码 todo 邮件发送功能待完成
        log.debug("发送邮箱验证码,验证码{}",code);
        //返回成功
        return Result.success();
    }
}
