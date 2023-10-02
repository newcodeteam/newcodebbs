package com.newcodebbs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("后台轻量查询")
public class SelectDTO {
    @ApiModelProperty(value = "0为单个数据,1为多个数据",required = true)
    private Integer type;
    @ApiModelProperty(value = "查询类型id,具体看文档",required = true)
    private Integer typeName;
    @ApiModelProperty(value = "查询字段类型",required = true)
    private Integer typeCategory;
    @ApiModelProperty(value = "查询的数据",required = true)
    private Object data;
    @ApiModelProperty(value = "是否是模糊查询 true 模糊查询 false 精确查询,单个数据查询不用填",required = true)
    private boolean blur;
}
