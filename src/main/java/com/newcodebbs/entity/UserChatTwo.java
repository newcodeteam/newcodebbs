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
 * 聊天表 > 300000 数据
 * </p>
 *
 * @author shanhe
 * @since 2023-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_chat_two")
@ApiModel(value="UserChatTwo对象", description="聊天表 > 300000 数据")
public class UserChatTwo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "跟聊天者的id")
    @TableField("user_chat_id")
    private Long userChatId;

    @ApiModelProperty(value = "聊天内容")
    @TableField("user_chat_text")
    private String userChatText;

    @ApiModelProperty(value = "是否是发送文件或者图片 0空 1文件 2图片")
    @TableField("user_chat_text_status")
    private Boolean userChatTextStatus;

    @ApiModelProperty(value = "文件或者图片id")
    @TableField("user_chat_addr")
    private Long userChatAddr;


}
