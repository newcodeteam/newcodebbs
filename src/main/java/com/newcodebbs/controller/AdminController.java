package com.newcodebbs.controller;

import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.SelectDTO;
import com.newcodebbs.dto.UserDTO;
import com.newcodebbs.dto.UserInfoDTO;
import com.newcodebbs.entity.UserData;
import com.newcodebbs.entity.UserType;
import com.newcodebbs.service.ITypeGroupService;
import com.newcodebbs.service.IUserDataService;
import com.newcodebbs.service.IUserTypeService;
import com.newcodebbs.service.SelectDataService;
import com.newcodebbs.service.impl.UserDataServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private IUserDataService userDataService;
    
    @PostMapping("/select/selectType")
    public Result selectType(@RequestParam("userId") String userId){
        return userTypeService.getTypeData(userId);
    }
    
    
    @ApiOperation(value = "轻量条件模糊与精确查询(多个数据)")
    @ApiResponses(
            value = { @ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "失败"),
                    @ApiResponse(code = 404, message = "不存在") ,@ApiResponse(code = 401, message = "缺少参数") })
    @PostMapping("/select/selectData")
    public Result selectAllData(@RequestBody SelectDTO selectDTO) {
//         0 为单个数据 todo 需要重构为分页查询
        if (selectDTO.getType() == 0) return selectDataService.selectIdData(selectDTO.getTypeName(),selectDTO.getTypeCategory(),selectDTO.getData());
//         1 为多个数据
        else return selectDataService.selectAllData(selectDTO.getTypeName(),selectDTO.getTypeCategory(),selectDTO.getData(),selectDTO.isBlur());
    }
    @ApiOperation(value = "删除指定用户id")
    @PostMapping("/deleteUser/{userId}")
    public Result deleteUser(@PathVariable String userId) {
        return userDataService.deleteUser(userId);
    }
    
    @ApiOperation(value = "禁用指定用户id")
    @PostMapping("/disableUser/{userId}")
    public Result disableUser(@PathVariable String userId) {
        return userDataService.disableUser(userId);
    }
    
    @ApiOperation(value = "启用指定用户id")
    @PostMapping("/startUser/{userId}")
    public Result startUser(@PathVariable String userId) {
        return userDataService.startUser(userId);
    }
}
