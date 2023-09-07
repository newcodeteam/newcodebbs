package com.newcodebbs.service;

import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.entity.UserData;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
public interface IUserDataService extends IService<UserData> {
    
    Result sendCode(String mail,HttpSession session);
    
    Result LoginAndRegister(UserForm userForm, HttpSession session);
}
