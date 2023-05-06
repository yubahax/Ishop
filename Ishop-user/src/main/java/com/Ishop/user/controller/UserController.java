package com.Ishop.user.controller;


import com.Ishop.common.entity.User;
import com.Ishop.common.util.util.OssUtil;
import com.Ishop.common.util.util.RedisUtils;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
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
        return userService.signInUser(user)?RestGenerator.successResult("注册成功"):RestGenerator.errorResult("注册失败");
    }

    @PostMapping("/update")
    public RestBean updateInfo(@RequestBody User user) {
        return userService.updateUser(user)?RestGenerator.successResult("修改成功"):RestGenerator.errorResult("修改失败");
    }
}
