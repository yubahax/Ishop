package com.Ishop.store.config;

import com.Ishop.common.util.util.BloomFilterHelper;
import com.Ishop.common.util.util.Yedis;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig{

    /**
     * RedisTemplate配置
     */
    @Bean
    public Yedis yedis (){
        return new Yedis();
    }

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("127.0.0.1:7001")
                .addNodeAddress("127.0.0.1:7002")
                .addNodeAddress("127.0.0.1:7003")
                .addNodeAddress("127.0.0.1:6001")
                .addNodeAddress("127.0.0.1:6002")
                .addNodeAddress("127.0.0.1:6003");
        return Redisson.create(config);
    }

    @Bean
    public RBloomFilter<String> rBloomFilter() {
        RBloomFilter<String> bloomFilter = redissonClient().getBloomFilter("storeList");
        bloomFilter.tryInit(100000000L,0.03);
        return bloomFilter;
    }



}
