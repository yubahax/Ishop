package com.Ishop.store.trans;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbOrder;
import com.Ishop.common.entity.TbOrderItem;
import com.Ishop.common.util.util.IDUtil;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.vo.Cart;

import com.Ishop.common.vo.CartProduct;
import com.Ishop.common.vo.Order;
import com.Ishop.common.vo.Product;
import com.Ishop.store.client.UserClient;
import com.Ishop.store.mapper.ItemMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTrans {

    @Resource
    ItemMapper itemMapper;

    @Resource
    UserClient userClient;
    public List<TbOrderItem> OrderItemVotoTb (List<Cart> carts, String orderId){
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

    public TbOrderItem OrderItemVotoVo(Product var, String orderId,int count){
        TbOrderItem tmp = new TbOrderItem();
        tmp.setOrderId(orderId);
        tmp.setItemId(String.valueOf(var.getProductId()));
        tmp.setId(String.valueOf(IDUtil.getRandomId()));
        tmp.setNum(count);
        tmp.setPrice(var.getSalePrice());
        tmp.setTitle(var.getSubTitle());
        tmp.setPicPath(var.getProductImageBig());
        tmp.setTotalFee(BigDecimal.valueOf(0));
        return tmp;
    }

    public Order orderTbtoVo(TbOrder tbOrder){
        Order order = new Order();
        order.setOrderId(Long.valueOf(tbOrder.getOrderId()));
        order.setOrderStatus(String.valueOf(tbOrder.getStatus()));
        order.setOrderTotal(tbOrder.getPayment());
        order.setCreateDate(tbOrder.getCreateTime());
        order.setCloseDate(tbOrder.getCloseTime());
        RestBean i = userClient.getDefaultAddress();
        LinkedHashMap map = (LinkedHashMap) i.getData();
        TbAddress j = (TbAddress)  map.get(0);
        order.setAddressInfo(j);
        order.setFinishDate(tbOrder.getEndTime());
        return order;
    }



    public CartProduct itemTbtoVo(TbOrderItem tbItem) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProductId(Long.valueOf(tbItem.getItemId()));
        cartProduct.setProductName(itemMapper.selectOne(new QueryWrapper<TbItem>().eq("id",tbItem.getItemId())).getTitle());
        cartProduct.setProductImg(tbItem.getPicPath());
        cartProduct.setProductNum(Long.valueOf(tbItem.getNum()));
        cartProduct.setSalePrice(tbItem.getPrice());
        return cartProduct;
    }
}
