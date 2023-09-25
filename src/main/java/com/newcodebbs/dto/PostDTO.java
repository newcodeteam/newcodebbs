package com.newcodebbs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostDTO {
    
    @ApiModelProperty(value = "发布帖子的用户id")
    private String userId;
    
    @ApiModelProperty(value = "帖子标题")
    @NotEmpty(message = "帖子名不能为空")
    private String postingsTitle;
    
    @ApiModelProperty(value = "帖子内容")
    @NotEmpty(message = "帖子内容不能为空")
    private String postingsContent;
    
    @ApiModelProperty(value = "是否有文件 如有文件就是文件id地址 没有就是-1")
    private Long postingsFileId;
    
    @ApiModelProperty(value = "分类id")
    @NotEmpty(message = "分类Id不能为空")
    private Integer postingsVipCategoryId;
    
    @ApiModelProperty(value = "帖子简介")
    private String postingsOutline;
    
    @ApiModelProperty(value = "帖子标签，用,分割开关键词")
    @NotEmpty(message = "帖子标签不能为空")
    private String postingsTag;
    
    @ApiModelProperty(value = "默认0 是否是教程")
    private Boolean postingsTutorial;
    
    @ApiModelProperty(value = "默认0 是否有付费，如果有则为id，没有则为-1")
    private Integer postingsMoneyId;
    
    @ApiModelProperty(value = "默认0 是否vip才能访问")
    private Boolean postingsVipRead;
}
