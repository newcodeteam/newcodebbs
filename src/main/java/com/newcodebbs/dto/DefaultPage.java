package com.newcodebbs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class DefaultPage {
    
    @ApiModelProperty(value = "第几页")
    private Integer postingsStartPage;
    
    @ApiModelProperty(value = "数据数量")
    private Integer postingsEndPage;
    
}
