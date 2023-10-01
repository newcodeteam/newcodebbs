package com.newcodebbs.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户账号")
    private String userName;
    @ApiModelProperty(value = "用户邮箱")
    private String userMail;
    @ApiModelProperty(value = "用户昵称")
    private String userNickname;
    @ApiModelProperty(value = "头像id,默认为默认头像地址")
    private Integer userIcon;
    @ApiModelProperty(value = "权限类型")
    private Integer userType;
    @ApiModelProperty(value = "权限昵称")
    private String userTypeName;
}
