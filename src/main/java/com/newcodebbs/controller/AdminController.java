package com.newcodebbs.controller;

import com.newcodebbs.dto.Result;
import com.newcodebbs.dto.SelectDTO;
import com.newcodebbs.entity.UserType;
import com.newcodebbs.service.ITypeGroupService;
import com.newcodebbs.service.IUserTypeService;
import com.newcodebbs.service.SelectDataService;
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
//         0 为单个数据
        if (selectDTO.getType() == 0) return selectDataService.selectIdData(selectDTO.getTypeName(),selectDTO.getTypeCategory(),selectDTO.getData());
//         1 为多个数据
        else return selectDataService.selectAllData(selectDTO.getTypeName(),selectDTO.getTypeCategory(),selectDTO.getData(),selectDTO.isBlur());
    }
}
