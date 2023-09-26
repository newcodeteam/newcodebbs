package com.newcodebbs.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.newcodebbs.dto.DefaultPage;
import com.newcodebbs.dto.PostDTO;
import com.newcodebbs.dto.Result;
import com.newcodebbs.service.IPostingsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    //    默认报错为null
    private String paramError = null;
    @ApiOperation(value = "查询页面数据，{start}为页数,默认第一页")
    @GetMapping(value = {"/defaultPost/{start}","/defaultPost"})
    public Result defaultPost(@PathVariable(required = false) Integer start){
        // 如果默认获取，就是默认第一页
        if (start == null) {
            return iPostingsInfoService.defaultPost(new DefaultPage(1,10));
        }
        return iPostingsInfoService.defaultPost(new DefaultPage(start,10));
    }
    @ApiOperation(value = "添加帖子,需要携带Token")
    @PostMapping("/addAcceptPost")
    public Result addPostAccept(@RequestBody PostDTO postDTO, BindingResult result) {
        //        数据校验错误信息
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                this.paramError = (error.getCode()+ "-" + error.getDefaultMessage());
            }
        }
        if (this.paramError != null) {
            return Result.error(this.paramError);
        }
        //添加帖子
        return iPostingsInfoService.addPostAccept(postDTO);
    }
}
