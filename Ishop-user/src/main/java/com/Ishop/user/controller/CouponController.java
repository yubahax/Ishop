package com.Ishop.user.controller;

import com.Ishop.common.entity.TbCoupon;
import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.user.service.CouponService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Resource
    CouponService couponService;

    @GetMapping("/addCoupon")
    public RestBean addCoupon (@RequestParam("cid") Long cid) {
        if (!ParamVail.vailNumber(Math.toIntExact(cid))) {
            return RestGenerator.errorResult("非法参数");
        }
        return couponService.addCoupon(cid)?RestGenerator.successResult("添加成功"):RestGenerator.errorResult("添加失败");
    }

    @GetMapping("/getCoupon")
    public RestBean getCoupon() {
        return  RestGenerator.successResult(couponService.getCoupon());
    }

    @GetMapping("/getVoCoupon")
    public RestBean getVoCoupon() {
        return  RestGenerator.successResult(couponService.getVoCoupon());
    }










}
