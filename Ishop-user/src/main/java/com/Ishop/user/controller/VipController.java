package com.Ishop.user.controller;

import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.user.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vip")
public class VipController {

    @Resource
    MemberService memberService;
    @GetMapping("/getVipInfo")
    public RestBean getVipInfo() {
        return RestGenerator.successResult(memberService.getVipInfo());
    }









}
