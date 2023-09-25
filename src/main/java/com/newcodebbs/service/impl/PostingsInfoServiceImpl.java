package com.newcodebbs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newcodebbs.dto.DefaultPage;
import com.newcodebbs.dto.PostDTO;
import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.UserDTO;
import com.newcodebbs.entity.PostingsData;
import com.newcodebbs.entity.PostingsInfo;
import com.newcodebbs.entity.PostingsOther;
import com.newcodebbs.mapper.PostingsDataMapper;
import com.newcodebbs.mapper.PostingsInfoMapper;
import com.newcodebbs.service.IPostingsDataService;
import com.newcodebbs.service.IPostingsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newcodebbs.service.IPostingsOtherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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
    
    @Resource
    private PostingsInfoMapper postingsInfoMapper;
    
    @Resource
    private IPostingsDataService postingsDataService;
    
    @Resource
    private IPostingsOtherService postingsOtherService;
    
    
    @Override
    public Result addPostAccept(PostDTO postDTO) {
        
        try {
            PostingsInfo postingsInfo = BeanUtil.copyProperties(postDTO,PostingsInfo.class);
            // 设置 帖子id 当前时间 + 随机十位数
            Long postingId = Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomUtil.randomNumbers(10));
            // 设置 id
            postingsInfo.setPostingsId(postingId);
            // 保存
            save(postingsInfo);
            PostingsData postingsData = BeanUtil.copyProperties(postDTO,PostingsData.class);
            postingsData.setPostingsId(postingId);
            postingsDataService.save(postingsData);
        } catch (Exception e) {
            Result.error("报错");
        }
        return Result.success("保存成功");
    }
    
    @Override
    public Result defaultPost(DefaultPage defaultPage) {
        IPage page = new Page(defaultPage.getPostingsStartPage(),defaultPage.getPostingsEndPage());
        postingsInfoMapper.selectPage(page,null);
        //添加数据
        Map<String,Object> map = MapUtil.newHashMap();
        // 当前页数
        map.put("current",page.getCurrent());
        // 总页数
        map.put("pages",page.getPages());
        // 当前页数内容
        map.put("records",page.getRecords());
        
        return Result.success("查询成功",map);
    }
}
