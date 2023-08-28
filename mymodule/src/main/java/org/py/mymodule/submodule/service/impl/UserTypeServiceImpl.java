package org.py.mymodule.submodule.service.impl;

import org.py.mymodule.submodule.entity.UserType;
import org.py.mymodule.submodule.mapper.UserTypeMapper;
import org.py.mymodule.submodule.service.IUserTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-08-28
 */
@Service
public class UserTypeServiceImpl extends ServiceImpl<UserTypeMapper, UserType> implements IUserTypeService {

}
