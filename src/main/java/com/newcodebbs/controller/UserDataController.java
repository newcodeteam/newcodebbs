package com.newcodebbs.controller;


import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.service.IUserDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/api/user")
public class UserDataController {
    
    @Resource
    private IUserDataService iUserDataService;
    
    @PostMapping("/code")
    public Result sendCode(@RequestParam("mail") String mail) {
        //发送邮件验证码并保存验证码
        return iUserDataService.sendCode(mail);
    }
}
