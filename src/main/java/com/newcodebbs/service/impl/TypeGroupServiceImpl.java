package com.newcodebbs.service.impl;

import com.newcodebbs.entity.TypeGroup;
import com.newcodebbs.mapper.TypeGroupMapper;
import com.newcodebbs.service.ITypeGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 0 为空 1 代表有权限 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Service
public class TypeGroupServiceImpl extends ServiceImpl<TypeGroupMapper, TypeGroup> implements ITypeGroupService {

}
