package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.User;
import com.Ishop.common.util.util.RedisUtils;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.user.mapper.UserMapper;
import com.Ishop.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User getUseInfo() {
        String name = RedisUtils.getName();
        User user;
        if (RedisUtils.hasKey("user"+name)) {
            user = (User) RedisUtils.get("user"+name);
        } else {
            user = userMapper.selectOne(new QueryWrapper<User>().eq("username",name));
            RedisUtils.set("user"+name,user);
        }
        return user;
    }

    @Override
    public boolean signInUser(User user) {
        user.setCreated(TimeUtil.getTime());
        user.setUpdated(TimeUtil.getTime());
        user.setRoleId(0);
        user.setDescription("普通用户");
        user.setState(1);
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateById(user) == 1;
    }
}
