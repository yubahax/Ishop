package com.Ishop.business.controller;

import com.Ishop.business.service.CouponService;
import com.Ishop.common.entity.TbCoupon;
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
    public RestBean addCoupon(@RequestParam("cid") Long cid) {
        if( !ParamVail.vailNumber(Math.toIntExact(cid))) {
            return RestGenerator.errorResult("非法参数");
        }
        return RestGenerator.successResult(couponService.addCoupon(cid));
    }

    @GetMapping("/delCoupon")
    public RestBean delCoupon(@RequestParam("cid") Long cid) {
        if( !ParamVail.vailNumber(Math.toIntExact(cid))) {
            return RestGenerator.errorResult("非法参数");
        }
        return RestGenerator.successResult(couponService.delCoupon(cid));
    }

    @PostMapping("/createCoupon")
    public RestBean createCoupon(@RequestBody TbCoupon tbCoupon){
        if (ParamVail.isNull(tbCoupon)) {
            return RestGenerator.errorResult("非法参数");
        }
        return couponService.createCoupon(tbCoupon)?RestGenerator.successResult("创建成功"):RestGenerator.errorResult("创建失败");
    }

    @GetMapping("/updateCouponNum")
    public RestBean updateCouponNum(@RequestParam("id") Long id,@RequestParam("num") int num){
        int cid = Math.toIntExact(id);
        if (!ParamVail.vailNumber(cid,num)) {
            return RestGenerator.errorResult("非法参数");
        }
        return couponService.updateCouponNum(id, num)?RestGenerator.successResult("更改成功"):RestGenerator.errorResult("更改失败");

    }





}
