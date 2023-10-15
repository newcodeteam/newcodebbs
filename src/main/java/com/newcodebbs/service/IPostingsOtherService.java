package com.newcodebbs.service;

import com.newcodebbs.entity.PostingsOther;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 帖子其他信息 服务类
 * </p>
 *
 * @author shanhe
 * @since 2023-10-02
 */
public interface IPostingsOtherService extends IService<PostingsOther> {
    
    PostingsOther selectPostingOtherData(Object postingsId);
}
