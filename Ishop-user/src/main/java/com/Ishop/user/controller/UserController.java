package com.Ishop.user.controller;


import com.Ishop.common.entity.TbUser;
import com.Ishop.common.util.util.*;
import com.Ishop.common.vo.Cart;
import com.Ishop.common.vo.CartProduct;
import com.Ishop.user.mapper.UserMapper;
import com.Ishop.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    UserMapper userMapper;

    @Resource
    Yedis yedis;
    @PostMapping("/test")
    public RestBean test() {
        String str = yedis.getName();
        TbUser user = userMapper.selectOne(new QueryWrapper<TbUser>().eq("username",str));
        System.out.println(user);
        yedis.set("user"+str,user);


        return RestGenerator.successResult(user);
    }

    @PostMapping("/register")
    public RestBean register(@RequestBody TbUser tbUser) {
        if (ParamVail.isNull(tbUser)) {
            return RestGenerator.errorResult("非法参数");
        }
        return userService.signInUser(tbUser)?RestGenerator.successResult("注册成功"):RestGenerator.errorResult("注册失败");
    }

    @PostMapping("/update")
    public RestBean updateInfo(@RequestBody TbUser tbUser) {
        if (ParamVail.isNull(tbUser)) {
            return RestGenerator.errorResult("非法参数");
        }
        return userService.updateUser(tbUser)?RestGenerator.successResult("修改成功"):RestGenerator.errorResult("修改失败");
    }

    @GetMapping("/changeImage")
    public RestBean changeImage(@RequestParam("image") String image) {
        if (ParamVail.isNull(image) || !ParamVail.vailString(image)) {
            return RestGenerator.errorResult("非法参数");
        }
        return userService.changeImage(image)?RestGenerator.successResult("修改成功"):RestGenerator.errorResult("修改失败");
    }




}
