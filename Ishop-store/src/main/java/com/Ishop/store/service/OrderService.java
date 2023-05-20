package com.Ishop.store.service;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.entity.TbCoupon;
import com.Ishop.common.entity.TbOrder;
import com.Ishop.common.entity.TbOrderItem;
import com.Ishop.common.vo.Cart;
import com.Ishop.common.vo.CountOrderItem;
import com.Ishop.common.vo.PageOrder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface OrderService {
    PageOrder getOrderList();

    boolean createOrder(Long userId , List<Cart> carts, TbCoupon coupon);

    List<CountOrderItem>  getDayOrderItem();

    List<CountOrderItem>  getWeekOrderItem();
    List<CountOrderItem>  getMonthOrderItem();

    boolean payOrder(String orderId) throws IOException, TimeoutException;

    boolean expireOrder(String orderId);


    boolean toExcel(int type);
}
