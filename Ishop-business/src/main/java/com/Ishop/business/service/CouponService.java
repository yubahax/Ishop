package com.Ishop.business.service;

import com.Ishop.common.entity.TbCoupon;

import java.util.List;

public interface CouponService {
    List<TbCoupon> getCouponByIdList(List<Long> ids);

    TbCoupon addCoupon(Long id);

    boolean delCoupon(Long id);

    boolean createCoupon(TbCoupon tbCoupon);

    boolean updateCouponNum(Long id,int num);
}
