package org.py.mymodule.submodule.entity;

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
@TableName("user_fans_double")
@ApiModel(value="UserFansDouble对象", description="")
public class UserFansDouble implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "粉丝id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "关注的用户id")
    @TableField("user_followed_id")
    private Long userFollowedId;


}
