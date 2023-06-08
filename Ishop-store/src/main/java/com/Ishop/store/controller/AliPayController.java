package com.Ishop.store.controller;

import com.Ishop.store.pojo.Order;
import com.Ishop.store.service.AliPayService;
import com.alipay.api.AlipayApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;

    /**
     * 进入支付主页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index.html";
    }

    @ResponseBody
    @PostMapping("/order/alipay")
    public String aliPay(String outTradeNo,String subject,
                         String totalAmount) throws AlipayApiException {
        System.out.println("--------进入提交支付----------");
        Order order = new Order();
        order.setOut_trade_no(outTradeNo);
        order.setSubject(subject);
        order.setTotal_amount(totalAmount);
        System.out.println("order-----:"+order);
        return aliPayService.aliPay(order);
    }

    /**
     * 支付成功
     * @return
     */
    @GetMapping("/paySuccess")
    public String paySuccess(){
        System.out.println("--------支付成功----------");
        //如果支付成功，订单就要新增到数据库
        //如果取消支付，也要新增订单到数据库，取消订单-状态
        //订单查询能够查出订单状态 信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        System.out.println("支付成功");
        return "success.html";
    }
}
