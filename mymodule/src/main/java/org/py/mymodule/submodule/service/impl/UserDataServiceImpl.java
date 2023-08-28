package org.py.mymodule.submodule.service.impl;

import org.py.mymodule.submodule.entity.UserData;
import org.py.mymodule.submodule.mapper.UserDataMapper;
import org.py.mymodule.submodule.service.IUserDataService;
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
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper, UserData> implements IUserDataService {

}
