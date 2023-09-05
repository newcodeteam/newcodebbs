package com.newcodebbs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static com.newcodebbs.Constants.ResultConstants.*;

/**
 * 统一返回值
 * @author shanhe
 * @since 2023-09-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    @ApiModelProperty("响应码")
    private Integer code;
    @ApiModelProperty("响应信息 描述字符串")
    private String msg;
    @ApiModelProperty("返回的数据")
    private Object data;
    
    /**
     * 执行成功响应
     * @return true
     */
    public static Result success(){
        return new Result(RESULT_CODE_TRUE,"success","true");
    }
    
    /**
     * 执行成功响应 并需要返回内容
     * @param data 返回的内容
     * @return 执行成功并返回内容
     */
    public static Result success(Object data){
        return new Result(RESULT_CODE_TRUE,"success",data);
    }
    
    /**
     * 执行成功并返回值和描述
     * @param msg 返回描述
     * @param data 返回的内容
     * @return 返回值和描述
     */
    public static Result success(String msg,Object data){
        return new Result(RESULT_CODE_TRUE,msg,data);
    }
    
    /**
     * 执行成功并并返回一个集合
     * @param data 集合
     * @return 集合
     */
    public static Result success(List<?> data){
        return new Result(RESULT_CODE_TRUE, "success",data);
    }
    
    /**
     * 执行失败 返回描述
     * @param msg 失败描述
     * @return 返回描述
     */
    public static Result error(String msg){
        return new Result(RESULT_CODE_ERROR,msg,null);
    }
}
