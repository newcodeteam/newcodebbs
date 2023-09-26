package com.newcodebbs.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ExtractedUtil {
    
    public static String extracted(BindingResult result) {
        //        数据校验错误信息
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                return (error.getCode()+ "-" + error.getDefaultMessage());
            }
        }
        return null;
    }
}
