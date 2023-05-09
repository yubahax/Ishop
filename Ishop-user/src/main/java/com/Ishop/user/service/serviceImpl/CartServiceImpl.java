package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbOrder;
import com.Ishop.common.entity.TbOrderItem;
import com.Ishop.common.util.util.IDUtil;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.common.vo.Cart;
import com.Ishop.user.mapper.ItemMapper;
import com.Ishop.user.mapper.OrderItemMapper;
import com.Ishop.user.mapper.OrderMapper;
import com.Ishop.user.service.CartService;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private static final String CART = "cart";
    @Resource
    Yedis yedis;

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderItemMapper orderItemMapper;

    @Resource
    ItemMapper itemMapper;

    /**
     * 写stream流一时爽，一直写stream流一直爽
     */
    public List<TbOrderItem> OrderItemVotoTb (List<Cart> carts,String orderId){
        List<TbOrderItem> orderItems = new LinkedList<>();
        List<TbItem> tbItems = itemMapper.selectBatchIds(carts.stream().map(Cart::getProductId).collect(Collectors.toList()));
        for (TbItem var:tbItems) {
            TbOrderItem tmp = new TbOrderItem();
            tmp.setOrderId(orderId);
            tmp.setItemId(String.valueOf(var.getId()));
            tmp.setId(String.valueOf(IDUtil.getRandomId()));
            tmp.setNum(carts.stream().filter(a->a.getProductId().equals(var.getId())).collect(Collectors.toList()).get(0).getProductNum());
            tmp.setPrice(var.getPrice());
            tmp.setTitle(var.getTitle());
            tmp.setPicPath(var.getImage());
            tmp.setTotalFee(BigDecimal.valueOf(0));
            orderItems.add(tmp);
        }
        return orderItems;
    }

    @Override
    public boolean addCart(Cart cart) {
        List<Cart> carts = new ArrayList<>();
        if (yedis.hasKey(yedis.getName() + cart)) {
            carts = (List<Cart>) yedis.get(yedis.getName() + CART);
            if (carts.stream().noneMatch(a -> a.getProductId().equals(cart.getProductId()))) {
                carts.add(cart);
            } else {
                carts.stream().filter(a -> a.getProductId().equals(cart.getProductId())).forEach(a -> a.setProductNum(a.getProductNum()+ cart.getProductNum()));
            }

        } else {
            carts.add(cart);
        }
        return yedis.set(yedis.getName() + CART, carts);
    }

    @Override
    public boolean delCart(Cart cart) {
        if (!yedis.hasKey(yedis.getName() + CART)) {
            return true;
        }
        List<Cart> carts = (List<Cart>) yedis.get(yedis.getName() + CART);
        List<Cart> var = carts.stream().filter(a -> !a.getProductId().equals(cart.getProductId())).collect(Collectors.toList());
        return yedis.set(yedis.getName() + CART, var);
    }

    @Override
    public List<Cart> cartList() {
        List<Cart> carts = (List<Cart>) yedis.get(yedis.getName() + CART);
        return carts;
    }

    @Override
    public boolean delAllCart() {
        return yedis.del(yedis.getName() + CART);
    }

    @Override
    public boolean createOrder() {
        String orderid = String.valueOf(IDUtil.getRandomId());
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(orderid);
        tbOrder.setCreateTime(TimeUtil.getTime());
        tbOrder.setBuyerComment(null);
        tbOrder.setBuyerNick(null);
        tbOrder.setBuyerMessage(null);
        tbOrder.setCloseTime(TimeUtil.getNext30Time());
        tbOrder.setConsignTime(null);
        tbOrder.setEndTime(null);
        tbOrder.setPaymentTime(null);
        tbOrder.setPaymentType(5);
        tbOrder.setPostFee(BigDecimal.valueOf(0));
        tbOrder.setUserId(yedis.getUser(yedis.getName()).getId());
        tbOrder.setUpdateTime(TimeUtil.getTime());
        tbOrder.setStatus(0);
        tbOrder.setShippingName(null);
        tbOrder.setShippingCode(null);
        if (orderMapper.insert(tbOrder) != 1) {
            throw  new RuntimeException("创建失败");
        }

        List<Cart> carts = (List<Cart>) yedis.get(yedis.getName() + CART);
        List<TbOrderItem> tbOrderItems = this.OrderItemVotoTb(carts,orderid);
        List<BigDecimal> sum = tbOrderItems.stream().map(a-> a.getPrice().divide(BigDecimal.valueOf(a.getNum()))).collect(Collectors.toList());
        BigDecimal var1 = new BigDecimal("0");
        for (BigDecimal j:sum){
            var1 = var1.add(j);
        }
        tbOrder.setPayment(var1);
        for (TbOrderItem i:tbOrderItems) {
            if (orderItemMapper.insert(i) != 1) {
                throw  new RuntimeException("创建失败");
            }
        }
        return true;
    }
}
