package com.Ishop.user.service;

import com.Ishop.common.vo.Cart;

import java.util.List;

public interface CartService {

    boolean addCart(Cart cart);

    boolean delCart(Cart cart);

    List<Cart> cartList();

    boolean delAllCart();

    boolean createOrder();
}
