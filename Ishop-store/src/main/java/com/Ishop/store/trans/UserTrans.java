package com.Ishop.store.trans;

import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbOrderItem;
import com.Ishop.common.util.util.IDUtil;
import com.Ishop.common.vo.Cart;

import com.Ishop.store.mapper.ItemMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTrans {

    @Resource
    ItemMapper itemMapper;
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
}
