package com.newcodebbs.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.service.IUserDataService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Api("用户基本接口")
@ApiSupport(author = "山河")
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserDataController {
    
    @Resource
    private IUserDataService iUserDataService;
    
    @ApiOperationSupport(author = "山河")
    @ApiOperation(value = "发送邮箱验证码")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "失败"),
            @ApiResponse(code = 404, message = "不存在") ,@ApiResponse(code = 401, message = "缺少参数") })
    @PostMapping("/code")
    public Result sendCode(@ApiParam("邮箱") @RequestParam("mail") String mail, HttpServletRequest request) {
        String session = request.getHeader("captchaSession");
        //发送邮件验证码并保存验证码
        return iUserDataService.sendCode(mail,session);
    }
    @ApiOperation(value = "通过验证码注册和登陆")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "{<br>\"code\":200,\"<br>msg\":\"success\",<br>\"data\":\"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTWFpbCI6InNoYW5oZXRlc3RAcXEuY29tIiwidXNlck5pY2tuYW1lIjoic2hhbmhldGVzdEBxcS5jb20iLCJ1c2VyTmFtZSI6InVzZXJfNjgzczBubThvbyIsInVzZXJJY29uIjoxLCJ1c2VySWQiOiI2NGZjODE1MGEyMDJlYjExMDM0MmQxYjciLCJ0b2tlbiI6IjhhODcyOTNjZjQ2ZTQ5YzdhYjUxMDFjNDk1OTAwNDU4IiwiZXhwIjoxNjk0ODc0NTc2fQ.bDjUfBlAdX-Fd0ldj-tnTQGtNzOfXJwoh6y6V8S14bw\"<br>}"), @ApiResponse(code = 400, message = "失败"),
            @ApiResponse(code = 404, message = "不存在") ,@ApiResponse(code = 401, message = "缺少参数") })
    @PostMapping("/login")
    public Result LoginAndRegister(@ApiParam("登陆/注册表单") @RequestBody UserForm userForm) {
        // 登陆或者注册
        return iUserDataService.LoginAndRegister(userForm);
    }
}
