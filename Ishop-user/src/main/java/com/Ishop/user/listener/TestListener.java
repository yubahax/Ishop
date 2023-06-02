package com.Ishop.user.listener;

import com.Ishop.user.service.CouponService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestListener {

    @Resource
    CouponService couponService;

    @RabbitListener(queues = "coupon")
    public void order(Long id){
       couponService.delCoupon(id);
        /**
         * 订单超时取消，保留订单信息，order和orderitem，回滚购物卷
         */
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
