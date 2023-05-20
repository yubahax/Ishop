package com.Ishop.store.listener;

import com.Ishop.store.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestListener {

    @Resource
    OrderService orderService;
    @RabbitListener(queues = "dl-yyds")
    public void test(String id){
        orderService.expireOrder(id);
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
