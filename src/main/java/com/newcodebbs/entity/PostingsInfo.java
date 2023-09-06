package com.newcodebbs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-09-06
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
    @TableField("postings_id")
    private Long postingsId;

    @ApiModelProperty(value = "帖子标题")
    @TableField("postings_title")
    private String postingsTitle;

    @ApiModelProperty(value = "帖子简介")
    @TableField("postings_outline")
    private String postingsOutline;

    @ApiModelProperty(value = "帖子标签，用,分割开关键词")
    @TableField("postings_tag")
    private String postingsTag;

    @ApiModelProperty(value = "默认0 是否是教程")
    @TableField("postings_tutorial")
    private Boolean postingsTutorial;

    @ApiModelProperty(value = "默认0 是否有付费，如果有则为id，没有则为-1")
    @TableField("postings_money_id")
    private Integer postingsMoneyId;

    @ApiModelProperty(value = "默认0 是否vip才能访问")
    @TableField("postings_vip_read")
    private Boolean postingsVipRead;

    @ApiModelProperty(value = "分类id")
    @TableField("postings_vip_category_id")
    private Integer postingsVipCategoryId;


}
