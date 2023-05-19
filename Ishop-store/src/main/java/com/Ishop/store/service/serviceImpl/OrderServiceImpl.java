package com.Ishop.store.service.serviceImpl;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbOrder;
import com.Ishop.common.entity.TbOrderItem;
import com.Ishop.common.util.util.IDUtil;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.common.vo.*;
import com.Ishop.store.client.UserClient;
import com.Ishop.store.mapper.ItemMapper;
import com.Ishop.store.mapper.OrderItemMapper;
import com.Ishop.store.mapper.OrderMapper;
import com.Ishop.store.service.OrderService;
import com.Ishop.store.trans.UserTrans;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemMapper orderItemMapper;

    @Resource
    ItemMapper itemMapper;

    @Resource
    UserTrans userTrans;

    @Resource
    UserClient userClient;

    @Resource
    Yedis yedis;

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
    @Override
    public PageOrder getOrderList() {
        List<Order> orderList = new ArrayList<>();
        List<TbOrder> tbOrderList = orderMapper.selectList(new QueryWrapper<TbOrder>().eq("user_id", yedis.getUser(yedis.getName()).getId()));
        for (TbOrder var : tbOrderList) {
            List<TbOrderItem> orderItems = orderItemMapper.selectList(new QueryWrapper<TbOrderItem>().eq("order_id",var.getOrderId()));
            List<CartProduct> cartProducts = new ArrayList<>();
            for (TbOrderItem var1:orderItems) {
                CartProduct var2 = this.itemTbtoVo(var1);
                cartProducts.add(var2);
            }
            Order order = this.orderTbtoVo(var);
            order.setGoodsList(cartProducts);

            orderList.add(order);
        }
        PageOrder pageOrders = new PageOrder();
        pageOrders.setTbOrderList(orderList);
        pageOrders.setTotal(tbOrderList.size());
        return pageOrders;
    }

    @Override
    public boolean createOrder(Long userId ,List<Cart> carts) {
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
        tbOrder.setUserId(userId);
        tbOrder.setUpdateTime(TimeUtil.getTime());
        tbOrder.setStatus(0);
        tbOrder.setShippingName(null);
        tbOrder.setShippingCode(null);
        if (orderMapper.insert(tbOrder) != 1) {
            return false;
        }

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

    @Override
    public List<CountOrderItem> getDayOrderItem() {
       List<String> list =  orderMapper.selectList(new QueryWrapper<TbOrder>().select("order_id").like("create_time",TimeUtil.getSimpleTime() + "%")).stream().map(TbOrder::getOrderId).collect(Collectors.toList());
       List<TbOrderItem> tbOrderItems = new LinkedList<>();
       for (String s:list) {
           List<TbOrderItem> var = orderItemMapper.selectList(new QueryWrapper<TbOrderItem>().eq("order_id",s));
           tbOrderItems.addAll(var);
       }
       Map<String,List<TbOrderItem>> lists = tbOrderItems.stream().collect(Collectors.groupingBy(TbOrderItem::getItemId));
       List<CountOrderItem> counterOrderList = new LinkedList<>();
       for (String key:lists.keySet()) {
           List<TbOrderItem> var = lists.get(key);
           CountOrderItem countOrderItem = new CountOrderItem();
           countOrderItem.setItemId(var.get(0).getItemId());
           countOrderItem.setNum(var.stream().map(TbOrderItem::getNum).reduce(0,Integer::sum));
           countOrderItem.setTitle(var.get(0).getTitle());
           countOrderItem.setTotalPrice(new BigDecimal(var.stream().map(TbOrderItem::getNum).reduce(0,Integer::sum)).multiply(var.get(0).getPrice()));
           counterOrderList.add(countOrderItem);
       }

        return counterOrderList;
    }

    @Override
    public List<CountOrderItem>  getWeekOrderItem() {
        List<String> list =  orderMapper.selectList(new QueryWrapper<TbOrder>().select("order_id").between("create_time",TimeUtil.getLastWeek(),TimeUtil.getNextWeek())).stream().map(TbOrder::getOrderId).collect(Collectors.toList());

        List<TbOrderItem> tbOrderItems = new LinkedList<>();
        for (String s:list) {
            List<TbOrderItem> var = orderItemMapper.selectList(new QueryWrapper<TbOrderItem>().eq("order_id",s));
            tbOrderItems.addAll(var);
        }
        Map<String,List<TbOrderItem>> lists = tbOrderItems.stream().collect(Collectors.groupingBy(TbOrderItem::getItemId));
        List<CountOrderItem> counterOrderList = new LinkedList<>();
        for (String key:lists.keySet()) {
            List<TbOrderItem> var = lists.get(key);
            CountOrderItem countOrderItem = new CountOrderItem();
            countOrderItem.setItemId(var.get(0).getItemId());
            countOrderItem.setNum(var.stream().map(TbOrderItem::getNum).reduce(0,Integer::sum));
            countOrderItem.setTitle(var.get(0).getTitle());
            countOrderItem.setTotalPrice(new BigDecimal(var.stream().map(TbOrderItem::getNum).reduce(0,Integer::sum)).multiply(var.get(0).getPrice()));
            counterOrderList.add(countOrderItem);
        }

        return counterOrderList;

    }

    @Override
    public List<CountOrderItem>  getMonthOrderItem() {
        List<String> list =  orderMapper.selectList(new QueryWrapper<TbOrder>().select("order_id").between("create_time",TimeUtil.getLastMonth(),TimeUtil.getNextMonth())).stream().map(TbOrder::getOrderId).collect(Collectors.toList());

        List<TbOrderItem> tbOrderItems = new LinkedList<>();
        for (String s:list) {
            List<TbOrderItem> var = orderItemMapper.selectList(new QueryWrapper<TbOrderItem>().eq("order_id",s));
            tbOrderItems.addAll(var);
        }
        Map<String,List<TbOrderItem>> lists = tbOrderItems.stream().collect(Collectors.groupingBy(TbOrderItem::getItemId));
        List<CountOrderItem> counterOrderList = new LinkedList<>();
        for (String key:lists.keySet()) {
            List<TbOrderItem> var = lists.get(key);
            CountOrderItem countOrderItem = new CountOrderItem();
            countOrderItem.setItemId(var.get(0).getItemId());
            countOrderItem.setNum(var.stream().map(TbOrderItem::getNum).reduce(0,Integer::sum));
            countOrderItem.setTitle(var.get(0).getTitle());
            countOrderItem.setTotalPrice(new BigDecimal(var.stream().map(TbOrderItem::getNum).reduce(0,Integer::sum)).multiply(var.get(0).getPrice()));
            counterOrderList.add(countOrderItem);
        }

        return counterOrderList;
    }

    @Override
    public boolean toExcel(int type) {
        List<CountOrderItem> list = new LinkedList<>();
        switch (type) {
            case 1:
                list = this.getDayOrderItem();
                break;
            case 2:
                list = this.getWeekOrderItem();
                break;
            case 3:
                list = this.getMonthOrderItem();
                break;
            default:
                return false;
        }
        System.out.println(list);
        String fileName = "E:\\java.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        //write方法两个参数 ，第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(fileName, CountOrderItem.class).sheet("第一个sheet").doWrite(list);
        return true;
    }
}
