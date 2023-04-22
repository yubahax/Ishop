package com.example.ishop.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.ishop.entity.User;
import com.example.ishop.mapper.UserMapper;
import com.example.ishop.service.CommonServcie;
import com.example.ishop.util.RedisUtils;
import com.example.ishop.util.TokenUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class CommonServiceImpl implements CommonServcie {

    @Resource
    UserMapper mapper;

    @Resource
    RedisUtils redisUtils;
    @Resource
    private AuthenticationManager authenticationManager;
    @Override
    @Async
    public Future<List<User>> getAllAsync() {
        long startTime = System.currentTimeMillis();
        List<User> users = mapper.getUsers();
//        System.out.println(users);
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + " ms");
//        return AsyncResult.forValue(users);
        return AsyncResult.forValue(users);
//        return AsyncResult.forValue(userMapper.getUsers());
    }

    @Override
    public List<User> getAll() {
        long startTime = System.currentTimeMillis();
        List<User> users = mapper.getUsers();
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + " ms");
        return users;
    }


    @Override
    public String login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        System.out.println("授权信息：" + authenticate);
        if (authenticate == null) {
            // 2. 如果认证没通过，给出对应的提示
            throw new RuntimeException("登陆失败");
        }
        // 3. 如果认证通过了，使用 userId 生成一个 jwt，jwt 存入 返回体 返回
        UserDetails loginUser = (UserDetails) authenticate.getPrincipal();
        int uid = mapper.selectOne(new QueryWrapper<User>().select("uid").eq("name",loginUser.getUsername())).getUid();
//        User user1 = new User(uid,loginUser.getUsername(),loginUser.getPassword());
//        String userId = loginUser.g().getId().toString();
        String jwt = TokenUtil.generateToken(uid);
//        HashMap<String, String> authToken = new HashMap<>();
//        authToken.put("auth",jwt);
        // 4. 以 userId 为key,用户信息为 value 放入缓存
        redisUtils.set("login:"+uid,mapper.selectById(uid));
        return  jwt;
    }
}
