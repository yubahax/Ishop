package com.Ishop.store.listener;

import com.Ishop.common.entity.TbCoupon;
import com.Ishop.store.service.CouponService;
import com.Ishop.store.service.ItemService;
import com.Ishop.store.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestListener {

    @Resource
    OrderService orderService;

    @Resource
    CouponService couponService;

    @Resource
    ItemService itemService;
    @RabbitListener(queues = "dl-yyds")
    public void order(String id){
        orderService.expireOrder(id);
    }


    @RabbitListener(queues = "dl-yyds",messageConverter = "jacksonConverter")
    public void coupon(TbCoupon coupon){

    }

    @RabbitListener(queues = "dl-yyds",messageConverter = "jacksonConverter")
    public void item(TbCoupon coupon){

    }

//        @RabbitListener(queues = "yuba",  containerFactory = "listenerContainer")
//        public void receiver(String data){
//            System.out.println("一号消息队列监听器 "+data);
//        }
//
//        @RabbitListener(queues = "yuba", containerFactory = "listenerContainer")
//        public void receiver2(String data){
//            System.out.println("二号消息队列监听器 "+data);
//        }

//    @RabbitListener(queues = "yyds1")
//    public void receiver3(String data){
//        System.out.println("一号消息队列监听器 "+data);
//    }
//
//    @RabbitListener(queues = "yyds2")
//    public void receiver24(String data){
//        System.out.println("二号消息队列监听器 "+data);
//    }



}
