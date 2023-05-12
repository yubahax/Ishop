package com.Ishop.user.cache;

import com.Ishop.common.util.util.Yedis;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CouponCache {


    private static final String COUPON_NAME = "coupon";

    @Resource
    Yedis yedis;


    public boolean  tryGetCoupon() {
        if (yedis.hasKey(COUPON_NAME + yedis.getName())) {
            int count = (int) yedis.get(COUPON_NAME + yedis.getName());
            if (count > 5) {
                return  false;
            }
            yedis.set(COUPON_NAME + yedis.getName(),++count);
        } else {
            yedis.set(COUPON_NAME + yedis.getName(),1);
        }
        return true;
    }





}
