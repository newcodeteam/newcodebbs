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
 * 权限表 0 为空 1 代表有权限
 * </p>
 *
 * @author shanhe
 * @since 2023-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("type_group")
@ApiModel(value="TypeGroup对象", description="权限表 0 为空 1 代表有权限")
public class TypeGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限Id")
    @TableId(value = "user_type_id", type = IdType.AUTO)
    private Integer userTypeId;

    @ApiModelProperty(value = "权限名称")
    @TableField("type_name")
    private String typeName;

    @ApiModelProperty(value = "任何权限")
    @TableField("type_all")
    private Integer typeAll;

    @ApiModelProperty(value = "编辑积分权限")
    @TableField("type_credits_from")
    private Integer typeCreditsFrom;

    @ApiModelProperty(value = "编辑虚拟币权限")
    @TableField("type_golds_from")
    private Integer typeGoldsFrom;

    @ApiModelProperty(value = "允许访问权限")
    @TableField("type_allow_read")
    private Integer typeAllowRead;

    @ApiModelProperty(value = "允许发帖子权限")
    @TableField("type_allow_article")
    private Integer typeAllowArticle;

    @ApiModelProperty(value = "允许评论权限")
    @TableField("type_allow_comments")
    private Integer typeAllowComments;

    @ApiModelProperty(value = "允许上传文件权限")
    @TableField("type_allow_attach")
    private Integer typeAllowAttach;

    @ApiModelProperty(value = "允许下载文件权限")
    @TableField("type_allow_down")
    private Integer typeAllowDown;

    @ApiModelProperty(value = "允许顶置帖子权限")
    @TableField("type_allow_top")
    private Integer typeAllowTop;

    @ApiModelProperty(value = "允许更改帖子权限")
    @TableField("type_allow_update")
    private Integer typeAllowUpdate;

    @ApiModelProperty(value = "允许删除帖子权限")
    @TableField("type_allow_delete")
    private Integer typeAllowDelete;

    @ApiModelProperty(value = "允许移动帖子板块权限")
    @TableField("type_allow_move")
    private Integer typeAllowMove;

    @ApiModelProperty(value = "允许禁止用户权限")
    @TableField("type_allow_ban_user")
    private Integer typeAllowBanUser;

    @ApiModelProperty(value = "允许删除用户权限")
    @TableField("type_allow_delete_user")
    private Integer typeAllowDeleteUser;

    @ApiModelProperty(value = "允许查看用户敏感信息权限")
    @TableField("type_allow_view_ip")
    private Integer typeAllowViewIp;

    @ApiModelProperty(value = "所属板块id")
    @TableField("type_allow_category")
    private Integer typeAllowCategory;


}
