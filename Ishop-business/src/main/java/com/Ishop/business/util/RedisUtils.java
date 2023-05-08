package com.Ishop.business.util;



import com.Ishop.common.entity.TbUser;
import com.Ishop.common.util.util.AbsRedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils implements AbsRedisUtils {

    @Resource
    RedisTemplate<String, Object> REDIS_TEMPLATE;

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
    public  TbUser getUser(String key) {
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
    public  Object getSet(final String str){
        try{
            return REDIS_TEMPLATE.opsForSet().members(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public  boolean hasKey(String key){
        return Boolean.TRUE.equals(REDIS_TEMPLATE.hasKey(key));
    }

    public  boolean delSet(String key,Object ... var){
        Long count = REDIS_TEMPLATE.opsForSet().remove(key, var);
        return count == null;
    }
}