package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.entity.TbCoupon;
import com.Ishop.common.entity.TbUser;
import com.Ishop.common.entity.TbUserCoupon;
import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.common.vo.VoCoupon;
import com.Ishop.user.cache.CouponCache;
import com.Ishop.user.client.CouponClient;
import com.Ishop.user.mapper.CouponMapper;
import com.Ishop.user.mapper.UserCouponMapper;
import com.Ishop.user.service.CouponService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

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

    @Resource
    CouponClient couponClient;
    @Override
    public boolean addCoupon(Long cid) {
        if (couponCache.tryGetCoupon()) {
            Long id = yedis.getUser(yedis.getName()).getId();
            RestBean i = couponClient.addCoupon(cid);
            if (i.getCode() == 500) {
                return false;
            }
            LinkedHashMap map = (LinkedHashMap) i.getData();
            TbCoupon coupon = (TbCoupon) map.get(0);
            if (ParamVail.isNull(coupon)) {
                return false;
            }
            TbUserCoupon tbUserCoupon = new TbUserCoupon();
            tbUserCoupon.setCid(coupon.getId());
            tbUserCoupon.setUid(yedis.getUser(yedis.getName()).getId());
            tbUserCoupon.setCreated(TimeUtil.getTime());
            tbUserCoupon.setEndtime(TimeUtil.addDayTime(coupon.getActivetime()));
            userCouponMapper.insert(tbUserCoupon);
            return true;
        }
        return false;
    }

    @Override
    public List<TbCoupon> getCoupon() {
        List<TbUserCoupon> cidList = userCouponMapper.selectList(new QueryWrapper<TbUserCoupon>().select("cid").eq("uid",yedis.getUser(yedis.getName()).getId()));
        List<Long> ids = cidList.stream().map(TbUserCoupon::getCid).collect(Collectors.toList());
        RestBean i = couponClient.getCouponByIdList(ids);
        LinkedHashMap map = (LinkedHashMap) i.getData();
        List<TbCoupon> j = (List<TbCoupon>)  map.get(0);
        return j;
    }

    @Override
    public List<VoCoupon> getVoCoupon() {
        List<TbUserCoupon> cidList = userCouponMapper.selectList(new QueryWrapper<TbUserCoupon>().eq("uid",yedis.getUser(yedis.getName()).getId()));
        List<Long> ids = cidList.stream().map(TbUserCoupon::getCid).collect(Collectors.toList());
        RestBean i = couponClient.getCouponByIdList(ids);
        LinkedHashMap map = (LinkedHashMap) i.getData();
        List<TbCoupon> j = (List<TbCoupon>)  map.get(0);
        List<VoCoupon> list = new ArrayList<>();
        for (TbUserCoupon userCoupon:cidList) {
            TbCoupon tbCoupon = j.stream().filter(a->a.getId().equals(userCoupon.getCid())).collect(Collectors.toList()).get(0);
            VoCoupon voCoupon = new VoCoupon();
            voCoupon.setId(userCoupon.getId());
            voCoupon.setLimitnum(tbCoupon.getLimitnum());
            voCoupon.setType(tbCoupon.getType());
            voCoupon.setValue(tbCoupon.getValue());
            voCoupon.setCid(tbCoupon.getId());
            list.add(voCoupon);
        }
        return list;
    }

    @Override
    public void delCoupon(Long id) {
        userCouponMapper.deleteById(id);
    }

    public void delDeadCoupon(){
        List<TbUserCoupon> tbUserCoupons = userCouponMapper.selectList(new QueryWrapper<TbUserCoupon>());
        List<Long> delIds =  tbUserCoupons.stream().filter(a -> !TimeUtil.checkTime(a.getEndtime())).map(TbUserCoupon::getId).collect(Collectors.toList());
        userCouponMapper.deleteBatchIds(delIds);
    }


}
