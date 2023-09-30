package com.newcodebbs.service.impl;

import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.TypeGroup;
import com.newcodebbs.entity.UserType;
import com.newcodebbs.mapper.TypeGroupMapper;
import com.newcodebbs.service.ITypeGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newcodebbs.utils.TypeGroupSelect;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 0 为空 1 代表有权限 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Service
public class TypeGroupServiceImpl extends ServiceImpl<TypeGroupMapper, TypeGroup> implements ITypeGroupService {
    
    @Override
    public Result getType(String userId) {
        // 查询权限
        TypeGroup userType = query().eq("user_id",userId).one();
        return Result.success(TypeGroupSelect.typeGroupNum(userType.getUserTypeId()),(Object) userType.getUserTypeId());
    }
}
