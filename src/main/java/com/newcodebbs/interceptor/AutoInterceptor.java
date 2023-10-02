package com.newcodebbs.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.newcodebbs.dto.UserDTO;
import com.newcodebbs.utils.JwtUtil;
import com.newcodebbs.utils.UserHolder;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.newcodebbs.Constants.RedisConstants.*;

/**
 * 作为拦截器第二次校验,如果存在了就不重复写入线程
 */
public class AutoInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;
    
    public AutoInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头的Token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            return true;
        }
        //定义局部变量
        String session = "";
        String id = "";
        Claims claims;
        // 解析jwt令牌
        try {
            claims = JwtUtil.parseJWT(token);
            session = (String) claims.get("token");
            id = (String) claims.get("userId");
        } catch (Exception e) {
            //jwt解析失败
            return true;
        }
        // 基于TOKEN获取redis中的用户
        String key  = USER_TOKEN_KEY + session;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
        // 判断用户是否存在
        if (userMap.isEmpty()) {
            // 根据id查用户  token持久化判断
            String tokenKey = USER_TOKEN_DATA + id;
            // 查持久化时间
            String tokenSessionDate = stringRedisTemplate.opsForValue().get(tokenKey);
            if (tokenSessionDate != null && Long.parseLong(tokenSessionDate) >= System.currentTimeMillis()) {
                // jwt数据没有过期,将数据继续持久化
                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(id);
                userDTO.setUserName((String) claims.get("userName"));
                userDTO.setUserMail((String)claims.get("userMail"));
                userDTO.setUserNickname((String) claims.get("userNickname"));
                userDTO.setUserIcon((Integer) claims.get("userIcon"));
                // 存在，保存用户信息到 ThreadLocal
                UserHolder.saveUser(userDTO);
                // 重新写入redis作为缓存
                // 随机token 作为登陆令牌
                String tokenID = UUID.randomUUID().toString(true);
                // 转为Hashmap存储
                // 将userDTO对象转换为map对象, CopyOptions.create() 是复制一些选项
                // setIgnoreNullValue(true) 忽略空值
                // setFieldValueEditor 修改字段的值,用 lambda表达式将字段的值转换为字符串
                Map<String,Object> userHashMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                        CopyOptions.create()
                                .setIgnoreNullValue(true)
                                .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
                // 存入 redis token
                String tokenRedisKey = USER_TOKEN_KEY +tokenID;
                stringRedisTemplate.opsForHash().putAll(tokenRedisKey,userHashMap);
                // 刷新token有效期
                stringRedisTemplate.expire(tokenRedisKey, USER_TOKEN_TTL, TimeUnit.MINUTES);
            }
            return true;
        }
        // 将查询到的hash数据转为UserDTO
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        // 存在，保存用户信息到 ThreadLocal
        UserHolder.saveUser(userDTO);
        // 刷新token有效期
        stringRedisTemplate.expire(key, USER_TOKEN_TTL, TimeUnit.MINUTES);
        // 放行
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
