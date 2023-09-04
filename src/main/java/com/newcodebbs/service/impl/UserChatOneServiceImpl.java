package com.newcodebbs.service.impl;

import com.newcodebbs.entity.UserChatOne;
import com.newcodebbs.mapper.UserChatOneMapper;
import com.newcodebbs.service.IUserChatOneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天表 < 300000 数据 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Service
public class UserChatOneServiceImpl extends ServiceImpl<UserChatOneMapper, UserChatOne> implements IUserChatOneService {

}
