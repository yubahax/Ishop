package com.Ishop.store.service;

import com.Ishop.common.entity.*;
import com.Ishop.common.vo.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface OrderService {
    PageOrder getOrderList();

    boolean createOrder(Long userId , List<Cart> carts, VoCoupon voCoupon);

    boolean buyItem(Product product, int count, VoCoupon voCoupon);

    List<CountOrderItem>  getDayOrderItem();

    List<CountOrderItem>  getWeekOrderItem();
    List<CountOrderItem>  getMonthOrderItem();

    boolean payOrder(String orderId) throws IOException, TimeoutException;

    boolean expireOrder(String orderId);


    boolean toExcel(int type);
}
