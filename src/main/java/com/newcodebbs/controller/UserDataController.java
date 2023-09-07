package com.newcodebbs.controller;


import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.service.IUserDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserDataController {
    
    @Resource
    private IUserDataService iUserDataService;
    
    
    @PostMapping("/code")
    public Result sendCode(@RequestParam("mail") String mail,HttpSession session) {
        //发送邮件验证码并保存验证码
        return iUserDataService.sendCode(mail,session);
    }
    @PostMapping("/login")
    public Result LoginAndRegister(@RequestBody UserForm userForm, HttpSession session) {
        // 登陆或者注册
        return iUserDataService.LoginAndRegister(userForm,session);
    }
}
