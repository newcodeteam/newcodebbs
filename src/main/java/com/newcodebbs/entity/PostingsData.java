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
 * 帖子表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("postings_data")
@ApiModel(value="PostingsData对象", description="帖子表")
public class PostingsData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "帖子id")
    @TableField("postings_id")
    private Long postingsId;

    @ApiModelProperty(value = "帖子发布的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "帖子内容")
    @TableField("postings_content")
    private String postingsContent;

    @ApiModelProperty(value = "是否有文件 如有文件就是文件id地址 没有就是-1")
    @TableField("postings_file_id")
    private Long postingsFileId;


}
