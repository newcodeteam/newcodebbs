package com.newcodebbs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PostDTO {
    
    @ApiModelProperty(value = "发布帖子的用户id")
    private String userId;
    
    @ApiModelProperty(value = "帖子标题")
    private String postingsTitle;
    
    @ApiModelProperty(value = "帖子数据")
    private String postingsData;
    
    @ApiModelProperty(value = "是否有文件 如有文件就是文件id地址 没有就是-1")
    private Long postingsFileId;
    
    @ApiModelProperty(value = "分类id")
    private Integer postingsVipCategoryId;
    
    @ApiModelProperty(value = "帖子简介")
    private String postingsOutline;
    
    @ApiModelProperty(value = "帖子标签，用,分割开关键词")
    private String postingsTag;
    
    @ApiModelProperty(value = "默认0 是否是教程")
    private Boolean postingsTutorial;
    
    @ApiModelProperty(value = "默认0 是否有付费，如果有则为id，没有则为-1")
    private Integer postingsMoneyId;
    
    @ApiModelProperty(value = "默认0 是否vip才能访问")
    private Boolean postingsVipRead;
}
