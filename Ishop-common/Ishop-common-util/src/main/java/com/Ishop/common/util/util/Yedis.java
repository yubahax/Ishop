package com.Ishop.common.util.util;

import com.Ishop.common.entity.TbUser;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

class tmplate{

    public static RedisTemplate<String, Object> redisTemplate() {
        // 设置序列化
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(3);
        genericObjectPoolConfig.setMinIdle(2);
        genericObjectPoolConfig.setMaxTotal(3);
        genericObjectPoolConfig.setMaxWaitMillis(-1);
        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(100);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(5000))
                .shutdownTimeout(Duration.ofMillis(100))
                .poolConfig(genericObjectPoolConfig)
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
        factory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        // key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        // value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

public class Yedis {

    RedisTemplate<String, Object> REDIS_TEMPLATE ;



    public Yedis() {

        this.REDIS_TEMPLATE = tmplate.redisTemplate();
    }

    private final static String USER_KEY = "user";

    /**
     * 根据key读取数据
     */
    public  Object get(final String key) {
        try {
            return REDIS_TEMPLATE.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public TbUser getUser(String key) {
        try {
            return (TbUser) REDIS_TEMPLATE.opsForValue().get(USER_KEY+key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入数据
     */
    public  boolean set(String key, Object value) {
        try {
            REDIS_TEMPLATE.opsForValue().set(key, value,12, TimeUnit.HOURS);
            //储存信息12小时后过期
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public  boolean set(final String key, Object value,int timeCount,TimeUnit timeUnit) {
        try {
            REDIS_TEMPLATE.opsForValue().set(key, value,timeCount, timeUnit);
            //储存信息12小时后过期
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据token获取用户username
     */
    public  String getName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 认证信息可能为空，因此需要进行判断。
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
        } else {
            return "未认证用户！";
        }
        return (String) authentication.getPrincipal();
    }


    public  boolean setSet(final String str,Object ... var){
        try {
            REDIS_TEMPLATE.opsForSet().add(str,var);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            REDIS_TEMPLATE.opsForValue().setBit(key, i, true);
        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     */
    public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            if (!REDIS_TEMPLATE.opsForValue().getBit(key, i)) {
                return false;
            }
        }
        return true;
    }

    public  Object getSet(final String str){
        try{
            return REDIS_TEMPLATE.opsForSet().members(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  boolean del(final String str){
        if (Boolean.FALSE.equals(REDIS_TEMPLATE.hasKey(str))) {
            return true;
        }
        try{
             REDIS_TEMPLATE.delete(str);
             return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public  boolean hasKey(String key){
        return Boolean.TRUE.equals(REDIS_TEMPLATE.hasKey(key));
    }

    public  boolean delSet(String key,Object ... var){
        Long count = REDIS_TEMPLATE.opsForSet().remove(key, var);
        return count == null;
    }
}
