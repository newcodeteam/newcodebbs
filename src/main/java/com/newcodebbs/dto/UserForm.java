package com.newcodebbs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserForm {
    @ApiModelProperty(value = "用户邮箱",example = "shanhetest@qq.com")
    private String mail;
    @ApiModelProperty(value = "邮箱验证码",example = "123456")
    private String code;
    @ApiModelProperty(value = "用户密码")
    private String password;
}
