package com.newcodebbs.service.impl;

import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.UserType;
import com.newcodebbs.mapper.UserTypeMapper;
import com.newcodebbs.service.ITypeGroupService;
import com.newcodebbs.service.IUserTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newcodebbs.utils.TypeGroupSelect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Service
public class UserTypeServiceImpl extends ServiceImpl<UserTypeMapper, UserType> implements IUserTypeService {
    @Resource
    private UserTypeMapper userTypeMapper;
    
    public Result getTypeData(String userId) {
        UserType userType = query().eq("user_id",userId).one();
        if (userType == null) {
            return Result.error("未登录");
        }
        userType.setId(null);
        return Result.success(TypeGroupSelect.typeGroupNum(userType.getUserTypeId()),userType);
    }
    
    /**
     * 查询权限数组
     * @param userId id
     * @return 权限数组
     */
    public String[] getTypeDataString(String userId) {
        UserType userType = query().eq("user_id",userId).one();
        if (userType == null) {
            return null;
        }
        String[] stringArr = {userType.getUserId() ,userType.getUserId() ,userType.getUserTypeNickname() ,TypeGroupSelect.typeGroupNum(userType.getUserTypeId()) ,String.valueOf(userType.getUserTypeId())};
        return stringArr;
    }
}
