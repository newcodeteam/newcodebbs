package com.newcodebbs.controller;

import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.UserType;
import com.newcodebbs.service.ITypeGroupService;
import com.newcodebbs.service.IUserTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 管理员控制器
 * </p>
 *
 * @author shanhe
 * @since 2023-09-20
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class AdminController {
    @Resource
    private IUserTypeService userTypeService;
    @PostMapping("/type")
    public Result selectType(@RequestParam("userId") String userId){
        return userTypeService.getTypeData(userId);
    }
}
