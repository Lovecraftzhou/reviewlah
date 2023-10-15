package com.reviewlah.service;

import com.reviewlah.db.pojo.Address;
import org.springframework.stereotype.Service;

import java.math.BigInteger;


public interface AddressService {
    void insertAddress(Address address);
    void updateAddress(Address address);
    Address selectAddressByMerchantId(BigInteger merchant_id);
}
