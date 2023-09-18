package com.newcodebbs.service;

import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.entity.UserData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
public interface IUserDataService extends IService<UserData> {
    
    Result sendCode(String mail,String session);
    
    Result LoginAndRegister(UserForm userForm);
    
    Result logout(String userId);
    
    Result followed(String myUserId, String userId);
    
    Result cancelFollowed(String myUserId, String userId);
    
    Result RegisterMail(String redisID,String mail,String password);
    
    Result Login(UserForm userForm);
}
