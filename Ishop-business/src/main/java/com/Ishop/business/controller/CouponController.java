package com.Ishop.business.controller;

import com.Ishop.business.service.CouponService;
import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Resource
    CouponService couponService;
    @PostMapping("/getCouponByIdList")
    public RestBean getCouponByIdList(@RequestBody List<Long> list){
        if (ParamVail.isNull(list)) {
            return RestGenerator.errorResult("非法参数");
        }
        return RestGenerator.successResult(couponService.getCouponByIdList(list));
    }

    @GetMapping("/addCoupon")
    RestBean addCoupon(@RequestParam("cid") Long cid) {
        if( !ParamVail.vailNumber(Math.toIntExact(cid))) {
            return RestGenerator.errorResult("非法参数");
        }
        return RestGenerator.successResult(couponService.addCoupon(cid));
    }






}
