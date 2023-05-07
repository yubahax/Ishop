package com.Ishop.user.service;

import com.Ishop.common.entity.TbUser;

public interface UserService {
    /**
     * 查找用户信息
     */
    TbUser getUseInfo();

    /**
     * 注册
     */
    boolean signInUser(TbUser tbUser);

    /**
     * 修改
     */
    boolean updateUser(TbUser tbUser);

    boolean changeImage(String image);
}
