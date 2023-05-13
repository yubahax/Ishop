package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.common.vo.Cart;
import com.Ishop.user.cache.CartCache;
import com.Ishop.user.client.StoreClient;
import com.Ishop.user.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    Yedis yedis;

    @Resource
    CartCache cartCache;

    @Resource
    StoreClient storeClient;



    /**
     * 写stream流一时爽，一直写stream流一直爽
     */

    @Override
    public boolean addCart(Cart cart) {
        return cartCache.set(cart);
    }

    @Override
    public boolean delCart(Cart cart) {
       return cartCache.del(cart);
    }

    @Override
    public List<Cart> cartList() {
        return cartCache.get();
    }

    @Override
    public boolean delAllCart() {
        return cartCache.del();
    }

    @Override
    public boolean createOrder() {
     RestBean bean = storeClient.createCartOrder(yedis.getUser(yedis.getName()).getId(),cartCache.get());
     return bean.getCode() != 500;
    }
}
