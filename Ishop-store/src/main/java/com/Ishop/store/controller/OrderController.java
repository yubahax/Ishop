package com.Ishop.store.controller;

import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.common.vo.Cart;
import com.Ishop.store.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Resource
    OrderService orderService;
    @GetMapping("/getUserOrderList")
    public RestBean getUserOrderList() {
        return RestGenerator.successResult(orderService.getOrderList());
    }

    @PostMapping("/createCartOrder")
    public RestBean createCartOrder(@RequestBody Long id, @RequestBody List<Cart> carts) {
        if(!ParamVail.vailNumber(Math.toIntExact(id)) || ParamVail.isNull(carts)) {
            return RestGenerator.errorResult("非法参数");
        }
        return RestGenerator.successResult(orderService.createOrder(id, carts));
    }




}
