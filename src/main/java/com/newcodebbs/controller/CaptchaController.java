package com.newcodebbs.controller;


import cn.hutool.core.util.RandomUtil;
import com.newcodebbs.service.IUserDataService;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import com.xingyuv.captcha.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static com.newcodebbs.Constants.RedisConstants.*;

/**
 * 图形验证码
 * @author captcha-plus
 * @since 2023-09-04
 */
@Slf4j
@RestController
@RequestMapping("api/captcha")
public class CaptchaController {
    
    @Resource
    private CaptchaService captchaService;
    
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    @PostMapping("/get")
    public ResponseModel get(@RequestBody CaptchaVO data, HttpServletRequest request) {
        String captchaId = request.getHeader("captchaId");
//        if(captchaId !=null) {
//            stringRedisTemplate.opsForValue().set(USER_CAPTCHA_KEY + captchaId,"1",10L, TimeUnit.SECONDS);
//        }
//        if (request.getHeader("captcha") != null) {
//            log.info("验证成功{}",request.getHeader("captcha"));
//            return null;
//        }
        assert request.getRemoteHost() != null;
        data.setBrowserInfo(getRemoteId(request));
        return captchaService.get(data);
    }
    
    @PostMapping("/check")
    public ResponseModel check(@RequestBody CaptchaVO data, HttpServletRequest request) {
//        if (request.getHeader("captcha") != null) {
//            log.info("验证成功{}",request.getHeader("captcha"));
//            return null;
//        }
        data.setBrowserInfo(getRemoteId(request));
        ResponseModel responseModel = captchaService.check(data);
        if (responseModel.getRepCode().equals("0000")) {
            String captchaId = request.getHeader("captchaId");
            String param = stringRedisTemplate.opsForValue().get(USER_CAPTCHA_KEY+captchaId);
//            if (param.equals("1") || param.equals("-1")) {
                // 临时session
                String session = RandomUtil.randomNumbers(16);
                //保存临时验证进session 进redis 五分钟过期
                stringRedisTemplate.opsForValue().set(USER_CAPTCHA_KEY + captchaId,session,USER_CAPTCHA_TTL, TimeUnit.MINUTES);
                responseModel.setRepMsg(session);
//            }

            log.debug("验证码返回了临时session:{}",responseModel);
        }
        return responseModel;
    }
    
    //@PostMapping("/verify")
    public ResponseModel verify(@RequestBody CaptchaVO data, HttpServletRequest request) {
        return captchaService.verification(data);
    }
    
    public static String getRemoteId(HttpServletRequest request) {
        String xfwd = request.getHeader("X-Forwarded-For");
        String ip = getRemoteIpFromXfwd(xfwd);
        String ua = request.getHeader("user-agent");
        if (StringUtils.isNotBlank(ip)) {
            return ip + ua;
        }
        return request.getRemoteAddr() + ua;
    }
    
    private static String getRemoteIpFromXfwd(String xfwd) {
        if (StringUtils.isNotBlank(xfwd)) {
            String[] ipList = xfwd.split(",");
            return StringUtils.trim(ipList[0]);
        }
        return null;
    }
    
}

