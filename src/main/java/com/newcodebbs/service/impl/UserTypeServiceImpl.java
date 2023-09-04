package com.newcodebbs.service.impl;

import com.newcodebbs.entity.UserType;
import com.newcodebbs.mapper.UserTypeMapper;
import com.newcodebbs.service.IUserTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Service
public class UserTypeServiceImpl extends ServiceImpl<UserTypeMapper, UserType> implements IUserTypeService {

}
