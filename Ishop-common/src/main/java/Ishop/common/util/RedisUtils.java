package com.example.common.util;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    UserDetailMapper detailMapper;

    @Resource
    UserMapper userMapper;

    /**
     * 根据key读取数据
     */
    public Object get(final String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入数据
     */
    public boolean set(final String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value,12, TimeUnit.HOURS);
            //储存信息12小时后过期
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean set(final String key, Object value,int timeCount,TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, value,timeCount, timeUnit);
            //储存信息12小时后过期
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void setUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("name",name));
        this.set("user"+user.getId(),user);
       this.set(name,user);
    }
    public User getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        User user = (User) this.get(name);
        if(user == null) {
            user = userMapper.selectOne(new QueryWrapper<User>().eq("name",name));
            this.set("user"+user.getId(),user);
            this.set(name,user);
        }
        return user;
    }

    public UserDetail getStudent() {
        User user = this.getUser();
        UserDetail detail = (UserDetail) this.get("user"+user.getId()+"detail");
        if (detail == null) {
            detail = detailMapper.selectById(user.getId());
            this.set("user"+user.getId()+"detail",detail);
        }
        return detail;
    }
    public boolean setSet(final String str,Object ... var){
        try {
            redisTemplate.opsForSet().add(str,var);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Object getSet(final String str){
        try{
            return redisTemplate.opsForSet().members(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public  boolean delSet(String key,Object ... var){
        Long count = redisTemplate.opsForSet().remove(key, var);
        return count == null;
    }
}