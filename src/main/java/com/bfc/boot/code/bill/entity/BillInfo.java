package com.bfc.boot.code.bill.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 账单表
 * </p>
 *
 * @author 白枫朝
 * @since 2022-03-02 19:10:27
 */
@Getter
@Setter
@TableName("tab_bill_info")
@ApiModel(value = "BillInfo对象", description = "账单表")
public class BillInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty("交易单号")
    @TableField("trade_code")
    private String tradeCode;

    @ApiModelProperty("商户单号")
    @TableField("merchant_code")
    private String merchantCode;

    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("交易账号")
    @TableField("account")
    private String account;

    @ApiModelProperty("交易类型")
    @TableField("trade_type")
    private String tradeType;

    @ApiModelProperty("交易对方")
    @TableField("trade_man")
    private String tradeMan;

    @ApiModelProperty("交易方账号")
    @TableField("trade_account")
    private String tradeAccount;

    @ApiModelProperty("商品说明")
    @TableField("trade_goods")
    private String tradeGoods;

    @ApiModelProperty("收/支")
    @TableField("in_or_out")
    private String inOrOut;

    @ApiModelProperty("金额(元)")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty("支付方式")
    @TableField("pay_way")
    private String payWay;

    @ApiModelProperty("交易平台")
    @TableField("trade_platform")
    private String tradePlatform;

    @ApiModelProperty("当前状态")
    @TableField("trade_status")
    private String tradeStatus;

    @ApiModelProperty("交易时间")
    @TableField("trade_time")
    private Date tradeTime;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

}
