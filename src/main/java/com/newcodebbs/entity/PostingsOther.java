package com.newcodebbs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帖子其他信息
 * </p>
 *
 * @author shanhe
 * @since 2023-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("postings_other")
@ApiModel(value="PostingsOther对象", description="帖子其他信息")
public class PostingsOther implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帖子id")
    @TableField("postings_id")
    private Long postingsId;

    @ApiModelProperty(value = "帖子点赞数")
    @TableField("postings_likes")
    private Long postingsLikes;

    @ApiModelProperty(value = "帖子浏览量数")
    @TableField("postings_views")
    private Long postingsViews;

    @ApiModelProperty(value = "帖子创建时间")
    @TableField("postings_create_time")
    private LocalDateTime postingsCreateTime;

    @ApiModelProperty(value = "帖子更改时间")
    @TableField("postings_update_time")
    private LocalDateTime postingsUpdateTime;


}
