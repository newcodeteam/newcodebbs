package com.newcodebbs.service.impl;

import com.newcodebbs.entity.CommentsData;
import com.newcodebbs.mapper.CommentsDataMapper;
import com.newcodebbs.service.ICommentsDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Service
public class CommentsDataServiceImpl extends ServiceImpl<CommentsDataMapper, CommentsData> implements ICommentsDataService {

}
