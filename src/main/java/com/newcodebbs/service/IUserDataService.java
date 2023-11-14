package com.newcodebbs.service;

import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserForm;
import com.newcodebbs.dto.UserInfoDTO;
import com.newcodebbs.entity.UserData;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    
    Result loginAndRegister(UserForm userForm);
    
    Result logout(String userId);
    
    Result followed(String myUserId, String userId);
    
    Result cancelFollowed(String myUserId, String userId);
    
    Result registerMail(String redisID,String mail,String password);
    
    Result login(UserForm userForm);
    
    Result setPassword(String password, HttpServletRequest httpServletRequest);
    
    UserData userSelectUserIdData(String userId);
    
    UserData userSelectMailData(String mail);
    
    UserData userSelectNameData(String name);
    
    List<?> userSelectNicknameBlurData(String nickname);
    
    List<?> userSelectNameBlurData(String name);
    
    List<?> userSelectMailBlurData(String mail);
    
    List<?> userSelectNicknameData(String nickname);
    
    Result deleteUser(String userId);
    
    Result disableUser(String userId);
    
    Result startUser(String userId);
    
    Result updateUserDataInfo(UserInfoDTO userInfoDTO);
}
