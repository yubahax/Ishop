package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbOrder;
import com.Ishop.common.entity.TbOrderItem;
import com.Ishop.common.util.util.RedisUtils;
import com.Ishop.common.vo.CartProduct;
import com.Ishop.common.vo.Order;
import com.Ishop.common.vo.PageOrder;
import com.Ishop.user.mapper.AddressMapper;
import com.Ishop.user.mapper.ItemMapper;
import com.Ishop.user.mapper.OrderItemMapper;
import com.Ishop.user.mapper.OrderMapper;
import com.Ishop.user.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemMapper orderItemMapper;

    @Resource
    AddressMapper addressMapper;

    @Resource
    ItemMapper itemMapper;

    public Order orderTbtoVo(TbOrder tbOrder){
        Order order = new Order();
        order.setOrderId(Long.valueOf(tbOrder.getOrderId()));
        order.setOrderStatus(String.valueOf(tbOrder.getStatus()));
        order.setOrderTotal(tbOrder.getPayment());
        order.setCreateDate(tbOrder.getCreateTime());
        order.setCloseDate(tbOrder.getCloseTime());
        HashMap<String,String> map = new HashMap<>();
        map.put("is_default","1");
        map.put("user_id",String.valueOf(RedisUtils.getUser().getId()));
        order.setAddressInfo(addressMapper.selectOne(new QueryWrapper<TbAddress>().allEq(map)));
        order.setFinishDate(tbOrder.getEndTime());
        return order;
    }

    public CartProduct itemTbtoVo(TbOrderItem tbItem) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProductId(Long.valueOf(tbItem.getItemId()));
//        cartProduct.setProductName();
        cartProduct.setProductImg(tbItem.getPicPath());
        cartProduct.setProductNum(Long.valueOf(tbItem.getNum()));
        cartProduct.setSalePrice(tbItem.getPrice());
        return cartProduct;
    }
    @Override
    public PageOrder getOrderList() {
        List<Order> orderList = new ArrayList<>();
        List<TbOrder> tbOrderList = orderMapper.selectList(new QueryWrapper<TbOrder>().eq("user_id", RedisUtils.getUser().getId()));
        for (TbOrder var : tbOrderList) {
            List<TbOrderItem> orderItems = orderItemMapper.selectList(new QueryWrapper<TbOrderItem>().eq("rder_id",var));
            List<CartProduct> cartProducts = new ArrayList<>();
            for (TbOrderItem var1:orderItems) {
                CartProduct var2 = this.itemTbtoVo(var1);
                cartProducts.add(var2);
            }
            Order order = new Order();
            order.setGoodsList(cartProducts);

            orderList.add(order);
        }
        PageOrder pageOrders = new PageOrder();
        pageOrders.setTbOrderList(orderList);
        pageOrders.setTotal(tbOrderList.size());
        return pageOrders;
    }
}