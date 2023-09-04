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
 * 推荐信息表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("recommend_data")
@ApiModel(value="RecommendData对象", description="推荐信息表")
public class RecommendData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "操作者id")
    private Long userId;

    @ApiModelProperty(value = "推荐id")
    private Integer recommendId;

    @ApiModelProperty(value = "推荐类型id，自己定义")
    private Integer recommendType;

    @ApiModelProperty(value = "推荐原因")
    private String recommendText;


}
