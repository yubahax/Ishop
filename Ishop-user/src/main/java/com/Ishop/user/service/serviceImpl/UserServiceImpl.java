package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.User;
import com.Ishop.common.util.util.OssUtil;
import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RedisUtils;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.user.mapper.UserMapper;
import com.Ishop.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    private final static String USER_KEY = "user";
    @Resource
    UserMapper userMapper;
    @Override
    public User getUseInfo() {
        String name = RedisUtils.getName();
        User user;
        if (RedisUtils.hasKey(USER_KEY + name)) {
            user = (User) RedisUtils.get(USER_KEY + name);
        } else {
            user = userMapper.selectOne(new QueryWrapper<User>().eq("username",name));
            RedisUtils.set(USER_KEY + name,user);
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

    @Override
    public boolean changeImage(String image) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("file",image).eq("id",getUseInfo().getId());
        boolean flag = userMapper.update(null,wrapper) != 0;
        User userDetail;
        if (RedisUtils.hasKey(USER_KEY + RedisUtils.getName())) {
            userDetail = (User) RedisUtils.get(USER_KEY + RedisUtils.getName());
        } else {
            userDetail = userMapper.selectById(getUseInfo().getId());
        }
        String str = null;
        if (userDetail != null) {
            str = userDetail.getFile();
            userDetail.setFile(image);
        }
        if (str != null) {
            OssUtil.deleteLongFile(str);
        }
        RedisUtils.set(USER_KEY + RedisUtils.getName(),userDetail);
        return  flag;
    }
}
