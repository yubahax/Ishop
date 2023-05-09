package com.Ishop.user.controller;

import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.common.vo.Cart;
import com.Ishop.user.service.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    CartService cartService;

    @PostMapping("/addCart")
    public RestBean addCart(@RequestBody Cart cart){
        if (ParamVail.isNull(cart)) {
            return RestGenerator.errorResult("非法参数");
        }
        return cartService.addCart(cart)? RestGenerator.successResult("添加成功"):RestGenerator.errorResult("添加失败");

    }

    @PostMapping("/delCart")
    public RestBean delCart(@RequestBody Cart cart){
        if (ParamVail.isNull(cart)) {
            return RestGenerator.errorResult("非法参数");
        }
        return cartService.delCart(cart)?RestGenerator.successResult("删除成功"):RestGenerator.errorResult("删除失败");
    }
    @PostMapping("/cartList")
    public RestBean cartList(){
        return RestGenerator.successResult(cartService.cartList());

    }
    @PostMapping("/delAllCart")
    public RestBean delAllCart(){
        return  cartService.delAllCart()?RestGenerator.successResult("删除成功"):RestGenerator.errorResult("删除失败");
    }

    @GetMapping("/createOrder")
    public RestBean createOrder() {
        return cartService.createOrder()?RestGenerator.successResult("创建成功"):RestGenerator.errorResult("创建失败");
    }
}
