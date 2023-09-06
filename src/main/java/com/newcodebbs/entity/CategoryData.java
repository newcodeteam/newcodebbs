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
 * 分类板块表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("category_data")
@ApiModel(value="CategoryData对象", description="分类板块表")
public class CategoryData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分类id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "分类名称")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty(value = "分类英文名")
    @TableField("category_english_name")
    private String categoryEnglishName;

    @ApiModelProperty(value = "分类关键词，用,分割")
    @TableField("category_keywords")
    private String categoryKeywords;

    @ApiModelProperty(value = "分类描述")
    @TableField("category_description")
    private String categoryDescription;


}
