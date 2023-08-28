package com.newcodebbs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("new_code_bbs_user")
@ApiModel(value="NewCodeBbsUser对象", description="")
public class NewCodeBbsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "账号")
    private String userName;

    @ApiModelProperty(value = "邮箱")
    private String userMail;

    @ApiModelProperty(value = "密码")
    private String userPwd;

    @ApiModelProperty(value = "关注数量")
    private Integer userConcern;

    @ApiModelProperty(value = "粉丝数量")
    private Integer userFans;

    @ApiModelProperty(value = "文章数量")
    private Integer userArticle;

    @ApiModelProperty(value = "评论数量")
    private Integer comments;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
