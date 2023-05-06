package com.Ishop.user.controller;


import com.Ishop.common.entity.User;
import com.Ishop.common.util.util.*;
import com.Ishop.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;
    @GetMapping("/test")

    public RestBean test() {
        String str = RedisUtils.getName();
        return RestGenerator.successResult(str);
    }

    @PostMapping("/register")
    public RestBean register(@RequestBody User user) {
        if (ParamVail.isNull(user)) {
            return RestGenerator.errorResult("非法参数");
        }
        return userService.signInUser(user)?RestGenerator.successResult("注册成功"):RestGenerator.errorResult("注册失败");
    }

    @PostMapping("/update")
    public RestBean updateInfo(@RequestBody User user) {
        if (ParamVail.isNull(user)) {
            return RestGenerator.errorResult("非法参数");
        }
        return userService.updateUser(user)?RestGenerator.successResult("修改成功"):RestGenerator.errorResult("修改失败");
    }

    @GetMapping("/changeImage")
    public RestBean changeImage(@RequestParam("image") String image) {
        if (ParamVail.isNull(image) || !ParamVail.vailString(image)) {
            return RestGenerator.errorResult("非法参数");
        }
        return userService.changeImage(image)?RestGenerator.successResult("修改成功"):RestGenerator.errorResult("修改失败");
    }
}
