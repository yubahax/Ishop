package com.Ishop.store.cache;

import com.Ishop.common.entity.TbOrder;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;

import com.Ishop.store.mapper.OrderMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderCache {
    private static final String ORDER_NAME = "unpayorders";

    @Resource
    OrderMapper orderMapper;
    @Resource
    Yedis yedis;
    public boolean add(TbOrder tbOrder) {
        List<TbOrder> orders = new ArrayList<>();
        if (yedis.hasKey(ORDER_NAME)) {
            orders = (List<TbOrder>) yedis.get(ORDER_NAME);
            orders.add(tbOrder);
        } else {
            orders.add(tbOrder);
        }
        return yedis.set(ORDER_NAME,orders);
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void checkOrderTime() {
        if (yedis.hasKey(ORDER_NAME)) {
            List<TbOrder> orders = (List<TbOrder>) yedis.get(ORDER_NAME);
            List<TbOrder> unpayList = orders.stream().filter( a -> TimeUtil.checkTime(a.getEndTime())).collect(Collectors.toList());
            List<TbOrder> normalList = orders.stream().filter(a-> !TimeUtil.checkTime(a.getEndTime())).collect(Collectors.toList());
            unpayList.forEach(a-> {
             a.setStatus(6);
             orderMapper.insert(a);
            });
            yedis.set(ORDER_NAME,normalList);
        }
    }



}
