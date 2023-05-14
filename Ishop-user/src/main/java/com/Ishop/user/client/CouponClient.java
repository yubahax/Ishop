package com.Ishop.user.client;

import com.Ishop.common.util.util.RestBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("business-service")
public interface CouponClient {

    @PostMapping("/coupon/getCouponByIdList")
    RestBean getCouponByIdList(@RequestBody List<Long> list);

    @GetMapping("/coupon/addCoupon")
    RestBean addCoupon(@RequestParam("cid") Long cid);



}
