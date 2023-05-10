package com.Ishop.user.cache;

import com.Ishop.common.util.util.Yedis;
import com.Ishop.common.vo.Cart;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class CartCache{

    private static final String CART = "cart";
    @Resource
    Yedis yedis;

    public List<Cart> get() {
        return (List<Cart>) yedis.get(yedis.getName() + CART);
    }

    public boolean set(Cart cart) {
        List<Cart> carts = new ArrayList<>();
        if (yedis.hasKey(yedis.getName() + CART)) {
            carts = (List<Cart>) yedis.get(yedis.getName() + CART);
            if (carts.stream().noneMatch(a -> a.getProductId().equals(cart.getProductId()))) {
                carts.add(cart);
            } else {
                carts.stream().filter(a -> a.getProductId().equals(cart.getProductId())).forEach(a -> a.setProductNum(a.getProductNum()+ cart.getProductNum()));
            }

        } else {
            carts.add(cart);
        }
        return yedis.set(yedis.getName() + CART, carts,99, TimeUnit.DAYS);
    }


    public boolean del(Cart cart) {
        if (!yedis.hasKey(yedis.getName() + CART)) {
            return true;
        }
        List<Cart> carts = (List<Cart>) yedis.get(yedis.getName() + CART);
        List<Cart> var1 = carts.stream().filter(a -> !a.getProductId().equals(cart.getProductId())).collect(Collectors.toList());
        return yedis.set(yedis.getName() + CART, var1);
    }

    public boolean del() {
        return yedis.del(yedis.getName() + CART);
    }
}
