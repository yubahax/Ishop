package com.Ishop.user.service;

import com.Ishop.common.entity.TbAddress;

import java.util.List;

public interface AddressService {

    List<TbAddress> getAllAddress();

    boolean delAddress(int id);

    boolean addAddress(TbAddress tbAddress);

    boolean updateAddress(TbAddress tbAddress);

    boolean setAddressStatus(int id,int status);
}
