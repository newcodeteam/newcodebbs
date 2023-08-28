package org.py.mymodule.submodule.entity;

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
 * 
 * </p>
 *
 * @author shanhe
 * @since 2023-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_data")
@ApiModel(value="UserData对象", description="")
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "账号")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "邮箱")
    @TableField("user_mail")
    private String userMail;

    @ApiModelProperty(value = "密码")
    @TableField("user_pwd")
    private String userPwd;

    @ApiModelProperty(value = "关注数量")
    @TableField("user_concern")
    private Integer userConcern;

    @ApiModelProperty(value = "粉丝数量")
    @TableField("user_fans")
    private Integer userFans;

    @ApiModelProperty(value = "文章数量")
    @TableField("user_article")
    private Integer userArticle;

    @ApiModelProperty(value = "评论数量")
    @TableField("user_comments")
    private Integer userComments;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
