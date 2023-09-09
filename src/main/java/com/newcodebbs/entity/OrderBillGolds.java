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
 * 虚拟币流水表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_bill_golds")
@ApiModel(value="OrderBillGolds对象", description="虚拟币流水表")
public class OrderBillGolds implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "虚拟币具体信息")
    @TableField("bill_golds_data")
    private String billGoldsData;

    @ApiModelProperty(value = "管理员id,如果没有赠送事件就默认为-1")
    @TableField("bill_golds_admin_id")
    private Integer billGoldsAdminId;

    @ApiModelProperty(value = "给予原因,如果没有默认就为null")
    @TableField("bill_golds_admin_data")
    private String billGoldsAdminData;


}
