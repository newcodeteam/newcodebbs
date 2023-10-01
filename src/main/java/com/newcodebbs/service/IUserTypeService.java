package com.newcodebbs.service;

import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.UserType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
public interface IUserTypeService extends IService<UserType> {
    
    
    Result getTypeData(String userId);
    
    String[] getTypeDataString(String userId);
}
