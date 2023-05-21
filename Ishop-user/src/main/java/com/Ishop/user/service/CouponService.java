package com.Ishop.user.service;

import com.Ishop.common.entity.TbCoupon;
import com.Ishop.common.vo.VoCoupon;

import java.util.List;

public interface CouponService {

    boolean addCoupon(Long cid);

    List<TbCoupon> getCoupon();

    List<VoCoupon> getVoCoupon();

    void delCoupon(Long id);
}
