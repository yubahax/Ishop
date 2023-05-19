package com.Ishop.store.config;

import com.Ishop.common.util.util.BloomFilterHelper;
import com.Ishop.common.util.util.Yedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig{

    /**
     * RedisTemplate配置
     */
   @Bean
   public Yedis yedis(){
       return new Yedis();
   }



}
