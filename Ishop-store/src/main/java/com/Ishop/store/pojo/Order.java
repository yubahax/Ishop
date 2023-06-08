package com.Ishop.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //链式
public class Order {
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 订单名称
     */
    private String subject;
    /**
     * 付款金额
     */
    private String total_amount;
    /**
     * 超时时间参数
     */
    private String timeout_express = "60m";
    /**
     * 产品编号
     */
    private String product_code = "FAST_INSTANT_TRADE_PAY";
}
