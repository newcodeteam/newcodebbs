package com.newcodebbs.service.impl;

import com.newcodebbs.dto.PostDTO;
import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.PostingsInfo;
import com.newcodebbs.mapper.PostingsInfoMapper;
import com.newcodebbs.service.IPostingsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 帖子信息表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Service
public class PostingsInfoServiceImpl extends ServiceImpl<PostingsInfoMapper, PostingsInfo> implements IPostingsInfoService {
    
    @Override
    public Result addPostAccept(PostDTO postDTO) {
        
        return null;
    }
}
