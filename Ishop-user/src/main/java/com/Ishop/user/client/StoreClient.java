package com.Ishop.user.client;

import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.vo.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("store-service")
public interface StoreClient {
    @PostMapping("/order/createCartOrder")
    RestBean createCartOrder(@RequestParam("id") Long id, @RequestBody List<Cart> carts);



}
