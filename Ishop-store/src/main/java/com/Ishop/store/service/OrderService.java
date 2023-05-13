package com.Ishop.store.service;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.entity.TbOrder;
import com.Ishop.common.vo.Cart;
import com.Ishop.common.vo.PageOrder;

import java.util.List;

public interface OrderService {
    PageOrder getOrderList();

    boolean createOrder(Long userId ,List<Cart> carts);
}
