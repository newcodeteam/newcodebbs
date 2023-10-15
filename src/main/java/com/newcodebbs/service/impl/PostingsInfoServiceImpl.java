package com.newcodebbs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newcodebbs.dto.DefaultPage;
import com.newcodebbs.dto.PostDTO;
import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.PostingsData;
import com.newcodebbs.entity.PostingsInfo;
import com.newcodebbs.entity.PostingsOther;
import com.newcodebbs.mapper.PostingsInfoMapper;
import com.newcodebbs.service.IPostingsDataService;
import com.newcodebbs.service.IPostingsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newcodebbs.service.IPostingsOtherService;
import com.newcodebbs.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public Result addPostAccept(PostDTO postDTO, HttpServletRequest httpServlet) {
        // XXX 需要将解析jwt令牌改为 本地线程获取数据
        Claims claims = tokenHttp(httpServlet);
        if (claims.isEmpty()) {
            return Result.error("token未携带");
        }
        // 获取Token的用户id
        postDTO.setUserId((String) claims.get("userId"));
        try {
            PostingsInfo postingsInfo = BeanUtil.copyProperties(postDTO,PostingsInfo.class);
            // 设置 帖子id 当前时间(最大13位，最小10位) + 随机5位数
            Long postingId = Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomUtil.randomNumbers(5));
            // 设置 id
            postingsInfo.setPostingsId(postingId);
            // 保存
            save(postingsInfo);
            PostingsData postingsData = BeanUtil.copyProperties(postDTO,PostingsData.class);
            // 设置data的id
            postingsData.setPostingsId(postingId);
            //保存
            postingsDataService.save(postingsData);
            PostingsOther postingsOther = new PostingsOther();
            //保存
            postingsOther.setPostingsId(postingId);
            postingsOtherService.save(postingsOther);
        } catch (Exception e) {
            return Result.error("报错");
        }
        return Result.success("保存成功");
    }
    
    private static Claims tokenHttp(HttpServletRequest httpServlet) {
        // XXX 即将废弃功能
        String token = httpServlet.getHeader("token");
        //定义局部变量
        Claims claims;
        // 解析jwt令牌
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //jwt解析失败
            return null;
        }
        return claims;
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
    
    @Override
    public Result updatePost(PostDTO postDTO) {
        // todo 更改帖子信息
        return null;
    }
    
    @Override
    public PostingsInfo  selectPostingInfoData(Object postingsId) {
        return query().eq("postings_id",postingsId).one();
    }
}
