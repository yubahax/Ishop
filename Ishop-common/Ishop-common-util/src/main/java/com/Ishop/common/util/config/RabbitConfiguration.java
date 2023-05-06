package com.Ishop.common.util.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean("directExchange")
    //定义交换机Bean，可以很多个
    public Exchange exchange(){
        return ExchangeBuilder.directExchange("amq.direct").build();
    }

    @Bean("directDlExchange")
    public Exchange dlExchange(){
        //创建一个新的死信交换机
        return ExchangeBuilder.directExchange("dlx.direct").build();
    }

    @Bean("binding")
    public Binding binding(@Qualifier("directExchange") Exchange exchange,
                           @Qualifier("yydsQueue") Queue queue){
        //将我们刚刚定义的交换机和队列进行绑定
        return BindingBuilder
                .bind(queue)
                //绑定队列
                .to(exchange)
                //到交换机
                .with("yubahax")
                //使用自定义的routingKey
                .noargs();
    }

    /***
     *
     * 死信队列
     *
     */
    @Bean("yydsQueue")     //定义消息队列
    public Queue queue(){
        return QueueBuilder
                .nonDurable("yuba")
                .deadLetterExchange("dlx.direct")
                //指定死信交换机
                .deadLetterRoutingKey("dl-yyds")
                .ttl(5000)
                //非持久化类型
                .build();
    }


    @Bean("yydsDlQueue")   //创建一个新的死信队列
    public Queue dlQueue(){
        return QueueBuilder
                .nonDurable("dl-yyds")
                .build();
    }

    @Bean("dlBinding")   //死信交换机和死信队列进绑定
    public Binding dlBinding(@Qualifier("directDlExchange") Exchange exchange,
                             @Qualifier("yydsDlQueue") Queue queue){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("dl-yyds")
                .noargs();
    }



    @Bean("jacksonConverter")   //直接创建一个用于JSON转换的Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
}
