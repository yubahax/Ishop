package com.Ishop.user.cache;

import com.Ishop.common.entity.TbAddress;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.user.mapper.AddressMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class AddressCache  {

    private static final String ADDRESS_NAME = "address";

    @Resource
    AddressMapper addressMapper;
    @Resource
    Yedis yedis;

    public boolean check(Long id) {
        TbAddress tbAddress = new TbAddress();
        if (yedis.hasKey(ADDRESS_NAME + id)) {
            tbAddress = (TbAddress) yedis.get(ADDRESS_NAME + id);
        } else {
            tbAddress = addressMapper.selectById(id);
            yedis.set(ADDRESS_NAME + id,tbAddress);
        }
        return tbAddress.getUserId().equals(yedis.getUser(yedis.getName()).getId());
    }

    public boolean check(int id) {
        TbAddress tbAddress = new TbAddress();
        if (yedis.hasKey(ADDRESS_NAME + id)) {
            tbAddress = (TbAddress) yedis.get(ADDRESS_NAME + id);
        } else {
            tbAddress = addressMapper.selectById(id);
            yedis.set(ADDRESS_NAME + id,tbAddress);
        }
        return tbAddress.getUserId().equals(yedis.getUser(yedis.getName()).getId());
    }



    public Object get(Long id) {
        TbAddress tbAddress = new TbAddress();
        if (yedis.hasKey(ADDRESS_NAME + id)) {
            tbAddress = (TbAddress) yedis.get(ADDRESS_NAME + id);
        } else {
            tbAddress = addressMapper.selectById(id);
            yedis.set(ADDRESS_NAME + id,tbAddress);
        }
        return tbAddress;
    }

    public Object get(int id) {
        TbAddress tbAddress = new TbAddress();
        if (yedis.hasKey(ADDRESS_NAME + id)) {
            tbAddress = (TbAddress) yedis.get(ADDRESS_NAME + id);
        } else {
            tbAddress = addressMapper.selectById(id);
            yedis.set(ADDRESS_NAME + id,tbAddress);
        }
        return tbAddress;
    }


    public boolean set(TbAddress tbAddress) {
        return yedis.set(ADDRESS_NAME + tbAddress.getAddressId(),tbAddress);
    }

    public boolean set(TbAddress tbAddress, int timeCount, TimeUnit timeUnit) {
        return yedis.set(ADDRESS_NAME + tbAddress.getAddressId(),tbAddress,timeCount,timeUnit);
    }

    public boolean del(Long id) {
        return yedis.del(ADDRESS_NAME + id);
    }

    public boolean del(int id) {
        return yedis.del(ADDRESS_NAME + id);
    }
}
