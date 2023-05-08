package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbUser;
import com.Ishop.common.util.util.OssUtil;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
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

    @Resource
    Yedis yedis;
    @Override
    public TbUser getUseInfo() {
        String name = yedis.getName();
        TbUser tbUser;
        if (yedis.hasKey(USER_KEY + name)) {
            tbUser = (TbUser) yedis.get(USER_KEY + name);
        } else {
            tbUser = userMapper.selectOne(new QueryWrapper<TbUser>().eq("username",name));
            yedis.set(USER_KEY + name, tbUser);
        }
        return tbUser;
    }

    @Override
    public boolean signInUser(TbUser tbUser) {
        tbUser.setCreated(TimeUtil.getTime());
        tbUser.setUpdated(TimeUtil.getTime());
        tbUser.setRoleId(0);
        tbUser.setDescription("普通用户");
        tbUser.setState(1);
        return userMapper.insert(tbUser) == 1;
    }

    @Override
    public boolean updateUser(TbUser tbUser) {
        return userMapper.updateById(tbUser) == 1;
    }

    @Override
    public boolean changeImage(String image) {
        UpdateWrapper<TbUser> wrapper = new UpdateWrapper<>();
        wrapper.set("file",image).eq("id",getUseInfo().getId());
        boolean flag = userMapper.update(null,wrapper) != 0;
        TbUser tbUserDetail;
        if (yedis.hasKey(USER_KEY + yedis.getName())) {
            tbUserDetail =  yedis.getUser(yedis.getName());
        } else {
            tbUserDetail = userMapper.selectById(getUseInfo().getId());
        }
        String str = null;
        if (tbUserDetail != null) {
            str = tbUserDetail.getFile();
            tbUserDetail.setFile(image);
        }
        if (str != null) {
            OssUtil.deleteLongFile(str);
        }
        yedis.set(USER_KEY + yedis.getName(), tbUserDetail);
        return  flag;
    }
}
