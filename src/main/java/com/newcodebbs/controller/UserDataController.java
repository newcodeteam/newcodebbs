package com.newcodebbs.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.newcodebbs.dto.ChatDTO;
import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.service.IUserDataService;
import io.swagger.annotations.*;
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
    @PostMapping("/register/code")
    public Result sendCode(@ApiParam("邮箱") @RequestParam("mail") String mail, HttpServletRequest request) {
        String session = request.getHeader("captchaSession");
        //发送邮件验证码并保存验证码
        return iUserDataService.sendCode(mail,session);
    }
    @ApiOperation(value = "通过验证码注册和登陆")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "{<br>\"code\":200,\"<br>msg\":\"success\",<br>\"data\":\"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTWFpbCI6InNoYW5oZXRlc3RAcXEuY29tIiwidXNlck5pY2tuYW1lIjoic2hhbmhldGVzdEBxcS5jb20iLCJ1c2VyTmFtZSI6InVzZXJfNjgzczBubThvbyIsInVzZXJJY29uIjoxLCJ1c2VySWQiOiI2NGZjODE1MGEyMDJlYjExMDM0MmQxYjciLCJ0b2tlbiI6IjhhODcyOTNjZjQ2ZTQ5YzdhYjUxMDFjNDk1OTAwNDU4IiwiZXhwIjoxNjk0ODc0NTc2fQ.bDjUfBlAdX-Fd0ldj-tnTQGtNzOfXJwoh6y6V8S14bw\"<br>}"), @ApiResponse(code = 400, message = "失败"),
                  @ApiResponse(code = 404, message = "不存在") ,@ApiResponse(code = 401, message = "缺少参数") })
    @PostMapping("/register/loginMail")
    public Result loginAndRegister(@ApiParam("登陆/注册表单") @RequestBody UserForm userForm) {
        // 登陆或者注册
        return iUserDataService.loginAndRegister(userForm);
    }
    @ApiOperation(value = "注册之后的注册验证,并且设置密码")
    @PostMapping("/register/{uuid}/{mail}")
    public Result registerMail(@ApiParam("redis临时id") @PathVariable String uuid,@ApiParam("邮箱") @PathVariable String mail,
                               @ApiParam("密码") @RequestParam("password") String password) {
        // 注册用户校验,输入密码完成注册
        return iUserDataService.registerMail(uuid,mail,password);
    }
    @ApiOperation(value = "通过账号密码登陆")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "{<br>\"code\":200,\"<br>msg\":\"success\",<br>\"data\":\"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTWFpbCI6InNoYW5oZXRlc3RAcXEuY29tIiwidXNlck5pY2tuYW1lIjoic2hhbmhldGVzdEBxcS5jb20iLCJ1c2VyTmFtZSI6InVzZXJfNjgzczBubThvbyIsInVzZXJJY29uIjoxLCJ1c2VySWQiOiI2NGZjODE1MGEyMDJlYjExMDM0MmQxYjciLCJ0b2tlbiI6IjhhODcyOTNjZjQ2ZTQ5YzdhYjUxMDFjNDk1OTAwNDU4IiwiZXhwIjoxNjk0ODc0NTc2fQ.bDjUfBlAdX-Fd0ldj-tnTQGtNzOfXJwoh6y6V8S14bw\"<br>}"), @ApiResponse(code = 400, message = "失败"),
            @ApiResponse(code = 200, message = "{<br>\"code\":201,\"<br>msg\":\"注册成功,请在邮箱点击链接完成注册\",<br>\"data\":\"null\"<br>}"), @ApiResponse(code = 400, message = "失败"),
            @ApiResponse(code = 404, message = "不存在") ,@ApiResponse(code = 401, message = "缺少参数") })
    @PostMapping("/register/login")
    public Result login(@ApiParam("登陆表单") @RequestBody UserForm userForm) {
        // 用户登陆校验,输入账号密码完成登陆
        return iUserDataService.login(userForm);
    }
    @ApiOperation(value = "设置密码")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "失败"),
            @ApiResponse(code = 404, message = "不存在") ,@ApiResponse(code = 401, message = "缺少参数") })
    @PostMapping("/register/setPassword")
    public Result setPassword(@ApiParam("密码") @RequestParam("password") String password,HttpServletRequest httpServletRequest) {
        // 设置密码,通过获取jwt解析后进行设置
        return iUserDataService.setPassword(password,httpServletRequest);
    }
    
    @ApiOperation(value = "用户登出")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "{<br>\"code\":200,\"<br>msg\":\"成功退出系统\",<br>\"data\":\"null\"<br>}"), @ApiResponse(code = 400, message = "失败"),
            @ApiResponse(code = 404, message = "不存在") ,@ApiResponse(code = 401, message = "缺少参数") })
    @PostMapping("/register/logout")
    public Result logout(@ApiParam("用户数据") @RequestParam("userId") String userId) {
        // 用户登出
        return iUserDataService.logout(userId);
    }

    @PostMapping("followed")
    public Result followed(@ApiParam("我的ID") @RequestParam("myUserId") String myUserId,
                           @ApiParam("需要关注的ID") @RequestParam("userId") String userId){
        // todo 关注用户
        return iUserDataService.followed(myUserId,userId);
    }

   @PostMapping("followedAll")
   public Result followedAll(@RequestParam("userId") String userID) {
        // todo 查看所有关注的用户 分页查询
        return null;
   }

   @PostMapping("cancelFollowed")
   public Result cancelFollowed(@ApiParam("我的ID") @RequestParam("myUserId") String myUserId,
             @ApiParam("需要取消关注的ID") @RequestParam("userId") String userId) {
        // todo 取消关注用户
       return iUserDataService.cancelFollowed(myUserId,userId);
   }

   
}
