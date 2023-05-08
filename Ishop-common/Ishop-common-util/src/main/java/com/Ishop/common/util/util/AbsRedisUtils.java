package com.Ishop.common.util.util;



import com.Ishop.common.entity.TbUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public interface AbsRedisUtils {

    /**
     * 根据key读取数据
     */
    Object get(final String key);

    TbUser getUser(String key) ;

    /**
     * 写入数据
     */
    boolean set(String key, Object value);

    boolean set(final String key, Object value,int timeCount,TimeUnit timeUnit);


   String getName();


   boolean setSet(final String str,Object ... var);
   Object getSet(final String str);


   boolean hasKey(String key);

   boolean delSet(String key,Object ... var);
}