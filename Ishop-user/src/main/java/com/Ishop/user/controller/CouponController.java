package com.Ishop.user.controller;

import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.user.service.CouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/coupon")
public class CouponController {



    @Resource
    CouponService couponService;


    @GetMapping("/addcoupon/{cid}")
    public RestBean addcoupon (@PathVariable int cid) {
        if (!ParamVail.vailNumber(cid)) {
            return RestGenerator.errorResult("非法参数");
        }
        return couponService.addCoupon(cid)?RestGenerator.successResult("添加成功"):RestGenerator.errorResult("添加失败");
    }








}
