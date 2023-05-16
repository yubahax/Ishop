package com.Ishop.store.controller;

import com.Ishop.common.entity.TbOrderItem;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.store.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {


    @Resource
    OrderService orderService;

    @GetMapping("/getDayOrderItem")
    public RestBean getDayOrderItem(){
        return RestGenerator.successResult(orderService.getDayOrderItem());
    }

    @GetMapping("/getWeekOrderItem")
    public RestBean getWeekOrderItem(){
        return RestGenerator.successResult(orderService.getWeekOrderItem());
    }

    @GetMapping("/getMonthOrderItem")
    public RestBean getMonthOrderItem(){
        return RestGenerator.successResult(orderService.getMonthOrderItem());
    }



}
