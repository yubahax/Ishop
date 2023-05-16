package com.Ishop.business.service.serviceImpl;

import com.Ishop.business.mapper.CouponMapper;
import com.Ishop.business.service.CouponService;
import com.Ishop.common.entity.TbCoupon;
import com.Ishop.common.util.util.TimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CouponServiceImpl implements CouponService {


    @Resource
    CouponMapper couponMapper;

    private static final ReentrantLock LOCK = new ReentrantLock();

    @Override
    public List<TbCoupon> getCouponByIdList(List<Long> ids) {
        return couponMapper.selectBatchIds(ids);
    }

    @Override
    public TbCoupon addCoupon(Long id) {
        LOCK.lock();
        try {
            TbCoupon coupon = couponMapper.selectById(id);
            if (coupon.getTotal() <= 0) {
                return null;
            }
            coupon.setTotal(coupon.getTotal() - 1);
            couponMapper.updateById(coupon);
            return coupon;
        } finally {
          LOCK.unlock();
        }
    }

    @Override
    public boolean delCoupon(Long id) {
        return couponMapper.deleteById(id) == 1;
    }

    @Override
    public boolean createCoupon(TbCoupon tbCoupon) {
        tbCoupon.setCreated(TimeUtil.getTime());
        tbCoupon.setUpdated(TimeUtil.getTime());
        return couponMapper.insert(tbCoupon) == 1;
    }

    @Override
    public boolean updateCouponNum(Long id, int num) {
        return couponMapper.update(null,new UpdateWrapper<TbCoupon>().set("limit_num",num).eq("id",id)) == 1;
    }
}
