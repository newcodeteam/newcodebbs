package com.newcodebbs.service.impl;

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
 * @since 2023-09-04
 */
@Service
public class PostingsInfoServiceImpl extends ServiceImpl<PostingsInfoMapper, PostingsInfo> implements IPostingsInfoService {

}
