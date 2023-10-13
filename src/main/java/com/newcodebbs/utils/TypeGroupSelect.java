package com.newcodebbs.utils;

/**
 * @author shanhe
 */
public class TypeGroupSelect {
    /**
     * 返回权限昵称
     * @param typeId
     * @return
     */
    public static String typeGroupNum(int typeId) {
        switch (typeId){
            case 0:
                return "用户组";
            case 1:
                return "管理员组";
            default:
                return "无权限";
        }
    }
}
