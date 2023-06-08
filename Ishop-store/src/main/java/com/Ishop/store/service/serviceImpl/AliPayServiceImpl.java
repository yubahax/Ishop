package com.Ishop.store.service.serviceImpl;


import com.Ishop.store.pojo.Order;
import com.Ishop.store.service.AliPayService;
import com.Ishop.store.util.AliPay;
import com.alipay.api.AlipayApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date 2023/3/23 10:47
 */
@Service
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private AliPay aliPay;


    @Override
    public String aliPay(Order order) throws AlipayApiException {
        System.out.println("order----service--:"+order);
        return aliPay.pay(order);
    }
}
