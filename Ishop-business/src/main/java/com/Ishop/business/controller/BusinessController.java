package com.Ishop.business.controller;

import com.Ishop.common.util.util.Yedis;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    Yedis yedis;
    @GetMapping("/test")
    public RestBean test(){
        yedis.set("yuba",1);
        System.out.println(yedis.get("yuba"));
        return RestGenerator.successResult();
    }
}
