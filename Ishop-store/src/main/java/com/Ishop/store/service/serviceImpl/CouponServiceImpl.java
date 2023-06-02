package com.Ishop.store.service.serviceImpl;


import com.Ishop.common.entity.TbCoupon;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.store.client.UserClient;
import com.Ishop.store.mapper.CouponMapper;
import com.Ishop.store.service.CouponService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CouponServiceImpl implements CouponService {


    @Resource
    CouponMapper couponMapper;

    private static final ReentrantLock LOCK = new ReentrantLock();
    @Resource
    RedissonClient redissonClient;

    @Resource
    Yedis yedis;

    @Resource
    UserClient userClient;

    @Override
    public List<TbCoupon> getCouponByIdList(List<Long> ids) {
        return couponMapper.selectBatchIds(ids);
    }

    @Override
    public TbCoupon addCoupon(Long id) {
        redissonClient.getLock("couponlock");
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
    public boolean maoAddCoupon(Long id) {
        RLock rlock = redissonClient.getLock("couponlock");
        if(!rlock.tryLock()) {
            return false;
        }
        try {
            int count = (int) yedis.get("coupon"+id);
            if (count < 1){
                return false;
            }
            yedis.set("coupon"+id,--count);
            userClient.addCoupon(id);
            return true;
        } finally {
            rlock.unlock();
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
