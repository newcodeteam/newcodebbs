package com.newcodebbs.utils;

import com.newcodebbs.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();
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
