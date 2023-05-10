package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.entity.TbUser;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.user.mapper.AddressMapper;
import com.Ishop.user.service.AddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 读写穿透，redis里修改数据直接删除
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressMapper addressMapper;
    @Resource
    Yedis yedis;

    private static final String ADDRESS_NAME = "address";

    private boolean check(int id){
        TbAddress tbAddress = new TbAddress();
        if (yedis.hasKey(ADDRESS_NAME + id)) {
            tbAddress = (TbAddress) yedis.get(ADDRESS_NAME + id);
        } else {
            tbAddress = addressMapper.selectById(id);
            yedis.set(ADDRESS_NAME + id,tbAddress);
        }
        return tbAddress.getUserId().equals(yedis.getUser(yedis.getName()).getId());
    }
    @Override
    public List<TbAddress> getAllAddress() {
        TbUser tbUser = yedis.getUser(yedis.getName());
        List<TbAddress> tbAddresses = addressMapper.selectList(new QueryWrapper<TbAddress>().eq("user_id",tbUser.getId()));
        tbAddresses.forEach(a -> yedis.set(ADDRESS_NAME + a.getAddressId(),a));
        return tbAddresses;
    }

    @Override
    public boolean delAddress(int id) {
        if (!check(id)) {
            return false;
        }
        yedis.del(ADDRESS_NAME + id);
        return addressMapper.deleteById(id) == 1;
    }

    @Override
    public boolean addAddress(TbAddress tbAddress) {
        TbUser user = yedis.getUser(yedis.getName());
        tbAddress.setIsDefault(0);
        tbAddress.setUserId(user.getId());
        tbAddress.setUserName(user.getName());
        int i = addressMapper.insert(tbAddress);
        yedis.set(ADDRESS_NAME + tbAddress.getAddressId(),tbAddress);
        return  i == 1;
    }

    @Override
    public boolean setAddressStatus(int id,int status) {
        if (!check(id)) {
            return false;
        }
        yedis.del(ADDRESS_NAME + id);
        return addressMapper.update(null,new UpdateWrapper<TbAddress>().set("is_default",status).eq("address_id",id)) == 1;
    }

    @Override
    public boolean updateAddress(TbAddress tbAddress) {
        if (!check(Math.toIntExact(tbAddress.getAddressId()))) {
            return false;
        }
        yedis.del(ADDRESS_NAME + tbAddress.getAddressId());
        return addressMapper.updateById(tbAddress) == 1;
    }
}
