package com.Ishop.user.controller;

import com.Ishop.common.util.RedisUtils;
import com.Ishop.common.util.RestBean;
import com.Ishop.common.util.RestGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/test")
    public RestBean test() {
        String str = RedisUtils.getName();

        return RestGenerator.successResult(str);
    }
}
