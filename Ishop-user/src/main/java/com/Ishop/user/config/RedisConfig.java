package com.Ishop.user.config;

import com.Ishop.common.util.util.Yedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    /**
     * RedisTemplate配置
     *
     */

    @Bean
    public Yedis yedis (){
        return new Yedis();
    }

}
