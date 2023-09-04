package com.newcodebbs.utils;

public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();
//    todo 用户数据实体类
    public static void saveUser(UserDTO user){
        tl.set(user);
    }
    
    public static UserDTO getUser(){
        return tl.get();
    }
    
    public static void removeUser(){
        tl.remove();
    }
}
