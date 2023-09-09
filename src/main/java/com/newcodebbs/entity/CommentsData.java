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
 * 评论表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comments_data")
@ApiModel(value="CommentsData对象", description="评论表")
public class CommentsData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "帖子id")
    @TableField("postings_id")
    private Long postingsId;

    @ApiModelProperty(value = "评论id")
    @TableField("comments_id")
    private Long commentsId;

    @ApiModelProperty(value = "评论内容")
    @TableField("comments_chat_text")
    private String commentsChatText;

    @ApiModelProperty(value = "评论时间")
    @TableField("comments_time")
    private LocalDateTime commentsTime;


}
