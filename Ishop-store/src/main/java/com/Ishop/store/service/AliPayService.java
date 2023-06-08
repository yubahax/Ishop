package com.Ishop.store.service;



import com.Ishop.store.pojo.Order;
import com.alipay.api.AlipayApiException;



public interface AliPayService {
    /**
     * 支付宝支付接口
     */
    String aliPay(Order order) throws AlipayApiException;
}
