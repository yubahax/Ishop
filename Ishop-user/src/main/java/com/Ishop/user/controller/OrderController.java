package com.Ishop.user.controller;

import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.user.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;
    @GetMapping("/getUserOrderList")
    public RestBean getUserOrderList() {
        return RestGenerator.successResult(orderService.getOrderList());
    }




}
