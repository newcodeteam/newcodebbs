package com.newcodebbs.service.impl;

import com.newcodebbs.entity.UserToken;
import com.newcodebbs.mapper.UserTokenMapper;
import com.newcodebbs.service.IUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 令牌持久化表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements IUserTokenService {

}
