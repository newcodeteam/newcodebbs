package com.newcodebbs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 关注与粉丝表 < 5000 id
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_fans_one")
@ApiModel(value="UserFansOne对象", description="关注与粉丝表 < 5000 id")
public class UserFansOne implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "粉丝id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "关注的用户id")
    @TableField("user_fans_followed_id")
    private Long userFansFollowedId;

    @ApiModelProperty(value = "关注状态(0关注 1取消)")
    @TableField("user_fans_status")
    private Boolean userFansStatus;


}
