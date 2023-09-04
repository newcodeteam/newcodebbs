package com.newcodebbs.service.impl;

import com.newcodebbs.entity.PostingsData;
import com.newcodebbs.mapper.PostingsDataMapper;
import com.newcodebbs.service.IPostingsDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 帖子表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Service
public class PostingsDataServiceImpl extends ServiceImpl<PostingsDataMapper, PostingsData> implements IPostingsDataService {

}
