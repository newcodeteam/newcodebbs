package com.newcodebbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newcodebbs.entity.PostingsInfo;
import com.newcodebbs.entity.PostingsOther;
import com.newcodebbs.mapper.PostingsOtherMapper;
import com.newcodebbs.service.IPostingsOtherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 帖子其他信息 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2023-10-02
 */
@Slf4j
@Service
public class PostingsOtherServiceImpl extends ServiceImpl<PostingsOtherMapper, PostingsOther> implements IPostingsOtherService {
    @Resource
    private PostingsOtherMapper postingsOtherMapper;
    
    @Override
    public List<?> selectPostingOtherData(Object postingsId) {
        Page<PostingsOther> page =query().eq("postings_id",postingsId).page(new Page<>(1,10));
        List<?> list = page.getRecords();
        log.info("测试{}",list);
        return page.getRecords();
    }
}
