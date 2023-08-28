package com.newcodebbs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author shanhe
 * @since 2023-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_type")
@ApiModel(value="UserType对象", description="")
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "头衔昵称,多个昵称以,隔开")
    private String userNickname;

    @ApiModelProperty(value = "权限id,默认0无权限")
    private Integer userType;


}
