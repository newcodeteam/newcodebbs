package com.newcodebbs.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.newcodebbs.dto.Result;
import com.newcodebbs.service.ICommonService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Api("公共接口")
@ApiSupport(author = "山河")
@Slf4j
@RestController
@RequestMapping("/api")
public class CommonController {
    @Resource
    private ICommonService commonService;
    
    @GetMapping("/body")
    public Result bodyData(){
        // fixme bug 太多 待修复 暂时无法使用
        return commonService.getBodyData();
    }
}
