package com.newcodebbs.controller;

import com.newcodebbs.dto.Result;
import com.newcodebbs.entity.UserType;
import com.newcodebbs.service.ITypeGroupService;
import com.newcodebbs.service.IUserTypeService;
import com.newcodebbs.service.SelectDataService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @Resource
    private SelectDataService selectDataService;
    
    @PostMapping("/select/selectType")
    public Result selectType(@RequestParam("userId") String userId){
        return userTypeService.getTypeData(userId);
    }
    @ApiOperation(value = "轻量条件查询(单个数据)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "查询类型id，0 -> 用户 , 1 -> 帖子, 2 -> 评论"),
            @ApiImplicitParam(name = "type",value = "（具体去文档看,这里列举的是用户）查询字段类型，0 -> id , 1 -> 邮箱, 2 -> 用户名 "),
            @ApiImplicitParam(name = "data",value = "查询的数据"),
    })
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "失败"),
            @ApiResponse(code = 404, message = "不存在") ,@ApiResponse(code = 401, message = "缺少参数") })
    @PostMapping("/select/data")
    public Result selectIdData(@RequestParam Integer name, @RequestParam Integer type,
                               @RequestParam Object data){
        return selectDataService.selectIdData(name,type,data);
    }
}
