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
 * 充值流水表
 * </p>
 *
 * @author shanhe
 * @since 2023-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_bill_recharge")
@ApiModel(value="OrderBillRecharge对象", description="充值流水表")
public class OrderBillRecharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "充值信息")
    @TableField("bill_recharge_info")
    private String billRechargeInfo;

    @ApiModelProperty(value = "充值来源")
    @TableField("bill_recharge_addr")
    private String billRechargeAddr;

    @ApiModelProperty(value = "充值账单")
    @TableField("bill_recharge_data")
    private String billRechargeData;


}
