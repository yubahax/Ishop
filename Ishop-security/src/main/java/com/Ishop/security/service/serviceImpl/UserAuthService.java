package com.Ishop.security.service.serviceImpl;


import com.Ishop.common.entity.TbUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.Ishop.security.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service

public class UserAuthService implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TbUser data = userMapper.selectOne(new QueryWrapper<TbUser>().eq("username",username));

        System.out.println("接收user信息：" + data);
        System.out.println("接收的username为："+username);
        if(data == null) {
            throw new UsernameNotFoundException("用户 "+username+" 登录失败，用户名不存在！");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(data.getName())
                .password(data.getPassword())
                .roles("user")
                .build();
    }
}

