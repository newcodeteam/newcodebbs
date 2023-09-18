package com.newcodebbs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {
    // 正则表达式：密码必须包含字母和数字，且长度在8到16位之间
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,16})$";
    
    /**
     * 正则表达式 校验
     * @param data 数据
     * @param patterns 校验规则
     * @return true 正确 false 错误
     */
    public static Boolean PatternData(String data,String patterns){
        //创建 pattern对象
        Pattern pattern = Pattern.compile(patterns);
        //校验数据
        Matcher matcher = pattern.matcher(data);
        //返回
        return matcher.matches();
    }
    
    
}
