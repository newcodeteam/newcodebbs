package com.newcodebbs.entity;

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
 * @since 2023-09-04
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
    private Long userId;

    @ApiModelProperty(value = "账号")
    private String userName;

    @ApiModelProperty(value = "邮箱")
    private String userMail;

    @ApiModelProperty(value = "密码")
    private String userPwd;

    @ApiModelProperty(value = "关注数量")
    private Integer userConcern;

    @ApiModelProperty(value = "粉丝数量")
    private Integer userFans;

    @ApiModelProperty(value = "QQ")
    private String userQq;

    @ApiModelProperty(value = "积分")
    private Integer userCredits;

    @ApiModelProperty(value = "预留 虚拟币")
    private Integer userGolds;

    @ApiModelProperty(value = "人民币")
    private Integer userRmbs;

    @ApiModelProperty(value = "创建时IP")
    private Integer userCreateIp;

    @ApiModelProperty(value = "登录时IP")
    private Integer userLoginIp;

    @ApiModelProperty(value = "文章数量")
    private Integer userArticle;

    @ApiModelProperty(value = "评论数量")
    private Integer userComments;

    @ApiModelProperty(value = "用户是否封禁 0 正常")
    private Boolean userStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "登陆时间")
    private LocalDateTime loginTime;


}
