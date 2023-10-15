package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Address;

import java.math.BigInteger;

public interface AddressDao {
    void insertAddress(Address address);
    void updateAddress(Address address);
    Address selectAddressByMerchantId(BigInteger merchant_id);
}
