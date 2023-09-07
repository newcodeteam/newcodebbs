package com.newcodebbs.dto;

import lombok.Data;

@Data
public class UserForm {
//    邮箱
    private String mail;
//    邮箱验证码
    private String code;
//    密码
    private String password;
}
