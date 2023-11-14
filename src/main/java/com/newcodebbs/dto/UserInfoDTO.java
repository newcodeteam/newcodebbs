package com.newcodebbs.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoDTO {
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户账号")
    private String userName;
    @ApiModelProperty(value = "密码")
    @TableField("user_pwd")
    private String userPwd;
    @ApiModelProperty(value = "用户邮箱")
    private String userMail;
    @ApiModelProperty(value = "用户昵称")
    private String userNickname;
    @ApiModelProperty(value = "头像id,默认为默认头像地址")
    private Integer userIcon;
    @ApiModelProperty(value = "QQ")
    @TableField("user_qq")
    private String userQq;
}
