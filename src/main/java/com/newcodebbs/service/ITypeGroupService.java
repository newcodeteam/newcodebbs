package com.newcodebbs.service;

import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.TypeGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限表 0 为空 1 代表有权限 服务类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
public interface ITypeGroupService extends IService<TypeGroup> {
    
    Result getType(String userId);
}
