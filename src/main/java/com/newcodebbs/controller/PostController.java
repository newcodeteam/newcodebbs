package com.newcodebbs.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.newcodebbs.dto.PostDTO;
import com.newcodebbs.dto.Result;
import com.newcodebbs.service.IPostingsInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 帖子控制器
 * </p>
 *
 * @author shanhe
 * @since 2023-09-19
 */
@Api("帖子接口")
@ApiSupport(author = "山河")
@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {
    
    @Resource
    private IPostingsInfoService iPostingsInfoService;
    
    @PostMapping("/addAcceptPost")
    public Result addPostAccept(@RequestBody PostDTO postDTO) {
        //添加帖子
        return iPostingsInfoService.addPostAccept(postDTO);
    }
}
