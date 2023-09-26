package com.newcodebbs.service;

import com.newcodebbs.dto.DefaultPage;
import com.newcodebbs.dto.PostDTO;
import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.PostingsInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 帖子信息表 服务类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
public interface IPostingsInfoService extends IService<PostingsInfo> {
    
    Result addPostAccept(PostDTO postDTO, HttpServletRequest httpServlet);
    
    Result defaultPost(DefaultPage defaultPage);
    
    Result updatePost(PostDTO postDTO);
}
