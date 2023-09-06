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
 * 数据分析以及推荐权重表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("analyse_data")
@ApiModel(value="AnalyseData对象", description="数据分析以及推荐权重表")
public class AnalyseData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帖子id")
    @TableField("postings_id")
    private Long postingsId;

    @ApiModelProperty(value = "帖子权重")
    @TableField("postings_weighted")
    private Integer postingsWeighted;

    @ApiModelProperty(value = "帖子所属标签")
    @TableField("weighted_tag")
    private Integer weightedTag;

    @ApiModelProperty(value = "帖子所属板块")
    @TableField("weighted_category")
    private Integer weightedCategory;

    @ApiModelProperty(value = "推荐理由")
    @TableField("weighted_type")
    private String weightedType;


}
