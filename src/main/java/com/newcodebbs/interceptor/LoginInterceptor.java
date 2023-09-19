package com.newcodebbs.interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.newcodebbs.dto.Result;
import com.newcodebbs.utils.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.判断是否需要拦截（ThreadLocal中是否有用户）
        if (UserHolder.getUser() == null) {
            Result error = Result.error("not-login");
            //手动转换 对象--json
            String notLogin = JSONUtil.toJsonStr(error);
            response.setCharacterEncoding("UTF-8");
            // 返回报错
            response.getWriter().write(notLogin);
            // 没有，需要拦截，设置状态码
            response.setStatus(401);
            // 拦截
            return false;
        }
//         有用户，则放行
        return true;
    }
}
