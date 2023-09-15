package com.newcodebbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.entity.UserToken;
import com.newcodebbs.mapper.UserTokenMapper;
import com.newcodebbs.service.IUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 令牌持久化表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Slf4j
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements IUserTokenService {
    @Resource
    private UserTokenMapper userTokenMapper;
    @Override
    public void createToken(UserToken userTokens) {
        
        UserToken userToken = query().eq("user_id",userTokens.getUserId()).one();
        log.debug("查询到的token:{}",userToken);
        // 如果没有这个数据,那就添加token
        if (userToken == null) {
            save(userTokens);
            return;
        }
        // 如果有,那就更新时间
//        userTokenMapper.updateById(userTokens);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",userToken.getId());
        updateWrapper.set("token_expired_time",userTokens.getTokenExpiredTime());
        baseMapper.update(userTokens,updateWrapper);
        log.debug("查询到的token2:{}",userToken);
    }
}
