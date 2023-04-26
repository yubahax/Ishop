package com.Ishop.security.service.serviceImpl;

import com.Ishop.common.entity.User;
import com.Ishop.common.util.RedisUtils;
import com.Ishop.security.util.TokenUtil;
import com.Ishop.security.service.CommonServcie;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.Ishop.security.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class CommonServiceImpl implements CommonServcie {

    @Resource
    UserMapper mapper;


    @Resource
    private AuthenticationManager authenticationManager;

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
        int uid = mapper.selectOne(new QueryWrapper<User>().select("uid").eq("name",loginUser.getUsername())).getId();
//        User user1 = new User(uid,loginUser.getUsername(),loginUser.getPassword());
//        String userId = loginUser.g().getId().toString();
        String jwt = TokenUtil.generateToken(uid);
//        HashMap<String, String> authToken = new HashMap<>();
//        authToken.put("auth",jwt);
        // 4. 以 userId 为key,用户信息为 value 放入缓存
        RedisUtils.set("login:"+uid,mapper.selectById(uid));
        return  jwt;
    }
}
