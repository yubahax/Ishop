package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbOrder;
import com.Ishop.common.entity.TbOrderItem;
import com.Ishop.common.util.util.IDUtil;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.common.vo.Cart;
import com.Ishop.user.cache.CartCache;
import com.Ishop.user.mapper.ItemMapper;
import com.Ishop.user.mapper.OrderItemMapper;
import com.Ishop.user.mapper.OrderMapper;
import com.Ishop.user.service.CartService;
import com.Ishop.user.trans.UserTrans;
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

    @Resource
    Yedis yedis;

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderItemMapper orderItemMapper;

    @Resource
    ItemMapper itemMapper;

    @Resource
    CartCache cartCache;

    @Resource
    UserTrans userTrans;

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
            return false;
        }

        List<Cart> carts = cartCache.get();
        List<TbOrderItem> tbOrderItems = userTrans.OrderItemVotoTb(carts,orderid);
        List<BigDecimal> sum = tbOrderItems.stream().map(a-> a.getPrice().divide(BigDecimal.valueOf(a.getNum()))).collect(Collectors.toList());
        BigDecimal var1 = new BigDecimal("0");
        for (BigDecimal j:sum){
            var1 = var1.add(j);
        }
        tbOrder.setPayment(var1);
        for (TbOrderItem i:tbOrderItems) {
            if (orderItemMapper.insert(i) != 1) {
               return false;
            }
        }
        return true;
    }
}
