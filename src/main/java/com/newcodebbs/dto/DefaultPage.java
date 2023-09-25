package com.newcodebbs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class DefaultPage {
    
    @ApiModelProperty(value = "开始")
    private Integer postingsStartPage;
    
    @ApiModelProperty(value = "结束")
    private Integer postingsEndPage;
    
}
