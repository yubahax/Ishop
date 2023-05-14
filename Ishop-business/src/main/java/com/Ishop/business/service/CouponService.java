package com.Ishop.business.service;

import com.Ishop.common.entity.TbCoupon;

import java.util.List;

public interface CouponService {
    List<TbCoupon> getCouponByIdList(List<Long> ids);

    TbCoupon addCoupon(Long id);
}
