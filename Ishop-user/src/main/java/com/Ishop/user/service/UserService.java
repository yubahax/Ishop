package com.Ishop.user.service;

import com.Ishop.common.entity.User;

public interface UserService {
    /**
     * 查找用户信息
     */
    User getUseInfo();

    /**
     * 注册
     */
    boolean signInUser(User user);

    /**
     * 修改
     */
    boolean updateUser(User user);

    boolean changeImage(String image);
}
