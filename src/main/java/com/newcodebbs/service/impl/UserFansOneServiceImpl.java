package com.newcodebbs.service.impl;

import com.newcodebbs.entity.UserFansOne;
import com.newcodebbs.mapper.UserFansOneMapper;
import com.newcodebbs.service.IUserFansOneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关注与粉丝表 < 5000 id 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Service
public class UserFansOneServiceImpl extends ServiceImpl<UserFansOneMapper, UserFansOne> implements IUserFansOneService {

}
