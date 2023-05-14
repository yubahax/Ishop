package com.Ishop.user.service;

import com.Ishop.common.entity.TbCoupon;

import java.util.List;

public interface CouponService {

    boolean addCoupon(Long cid);

    List<TbCoupon> getCoupon();
}
