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
 * 用户表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_data")
@ApiModel(value="UserData对象", description="用户表")
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

    @ApiModelProperty(value = "QQ")
    @TableField("user_qq")
    private String userQq;

    @ApiModelProperty(value = "积分")
    @TableField("user_credits")
    private Integer userCredits;

    @ApiModelProperty(value = "预留 虚拟币")
    @TableField("user_golds")
    private Integer userGolds;

    @ApiModelProperty(value = "人民币")
    @TableField("user_rmbs")
    private Integer userRmbs;

    @ApiModelProperty(value = "创建时IP")
    @TableField("user_create_ip")
    private Integer userCreateIp;

    @ApiModelProperty(value = "登录时IP")
    @TableField("user_login_ip")
    private Integer userLoginIp;

    @ApiModelProperty(value = "文章数量")
    @TableField("user_article")
    private Integer userArticle;

    @ApiModelProperty(value = "评论数量")
    @TableField("user_comments")
    private Integer userComments;

    @ApiModelProperty(value = "用户是否封禁 0 正常")
    @TableField("user_status")
    private Boolean userStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "登陆时间")
    @TableField("login_time")
    private LocalDateTime loginTime;


}
