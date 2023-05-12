package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbCoupon;
import com.Ishop.common.entity.TbUser;
import com.Ishop.common.entity.TbUserCoupon;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.user.cache.CouponCache;
import com.Ishop.user.mapper.CouponMapper;
import com.Ishop.user.mapper.UserCouponMapper;
import com.Ishop.user.service.CouponService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    CouponMapper couponMapper;

    @Resource
    UserCouponMapper userCouponMapper;

    @Resource
    CouponCache couponCache;

    static final ReentrantLock lock = new ReentrantLock();

    @Resource
    Yedis yedis;
    @Override
    public boolean addCoupon(int cid) {
        if (couponCache.tryGetCoupon()) {
            Long id = yedis.getUser(yedis.getName()).getId();
//            couponMapper.update(null,new UpdateWrapper<TbCoupon>().set("total"))
            userCouponMapper.insert(new TbUserCoupon(id, (long) cid, TimeUtil.getTime(),TimeUtil.getTime()));
        }



        return false;
    }

    public void getCoupon(int cid) {
        lock.lock();
        try {
            TbCoupon coupon = couponMapper.selectById(cid);
            int i = coupon.getTotal();
            coupon.setTotal(--i);
            couponMapper.update(coupon,null);
        } finally {
            lock.unlock();
        }

    }
}
