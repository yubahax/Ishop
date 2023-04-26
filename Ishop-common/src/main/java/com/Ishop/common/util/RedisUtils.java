package com.Ishop.common.util;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class RedisUtils {

    private final static RedisTemplate<String, Object> REDIS_TEMPLATE = new RedisTemplate<>();

    /**
     * 根据key读取数据
     */
    public static Object get(final String key) {
        try {
            return REDIS_TEMPLATE.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入数据
     */
    public static boolean set(final String key, Object value) {
        try {
            REDIS_TEMPLATE.opsForValue().set(key, value,12, TimeUnit.HOURS);
            //储存信息12小时后过期
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean set(final String key, Object value,int timeCount,TimeUnit timeUnit) {
        try {
            REDIS_TEMPLATE.opsForValue().set(key, value,timeCount, timeUnit);
            //储存信息12小时后过期
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 认证信息可能为空，因此需要进行判断。
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
        }
        return (String) authentication.getPrincipal();
    }

    public static boolean setSet(final String str,Object ... var){
        try {
            REDIS_TEMPLATE.opsForSet().add(str,var);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static Object getSet(final String str){
        try{
            return REDIS_TEMPLATE.opsForSet().members(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean hasKey(String key){
        return REDIS_TEMPLATE.hasKey(key);
    }

    public static   boolean delSet(String key,Object ... var){
        Long count = REDIS_TEMPLATE.opsForSet().remove(key, var);
        return count == null;
    }
}