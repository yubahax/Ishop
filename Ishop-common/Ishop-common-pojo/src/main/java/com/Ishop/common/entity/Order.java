package com.Ishop.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @TableId(type = IdType.NONE)
    private String orderId;

    @TableField("payment")
    private BigDecimal payment;

    @TableField("payment_type")
    private Integer paymentType;

    @TableField("post_fee")
    private BigDecimal postFee;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;

    @TableField("payment_time")
    private String paymentTime;

    @TableField("consign_time")
    private String consignTime;

    @TableField("end_time")
    private String endTime;

    @TableField("close_time")
    private String closeTime;

    @TableField("shipping_name")
    private String shippingName;

    @TableField("shipping_code")
    private String shippingCode;

    @TableField("user_id")
    private Long userId;

    @TableField("buyer_message")
    private String buyerMessage;

    @TableField("buyer_nick")
    private String buyerNick;

    @TableField("buyer_comment")
    private Boolean buyerComment;
}
