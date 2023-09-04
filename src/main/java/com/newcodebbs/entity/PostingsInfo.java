package com.newcodebbs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帖子信息表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("postings_info")
@ApiModel(value="PostingsInfo对象", description="帖子信息表")
public class PostingsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帖子id")
    private Long postingsId;

    @ApiModelProperty(value = "帖子标题")
    private String postingsTitle;

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

    @ApiModelProperty(value = "分类id")
    private Integer postingsVipCategoryId;


}
