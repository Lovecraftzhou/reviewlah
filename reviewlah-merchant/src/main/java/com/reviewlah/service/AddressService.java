package com.reviewlah.service;

import com.reviewlah.db.pojo.Address;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;


public interface AddressService {
    void insertAddress(Address address);
    void updateAddress(Address address);
    Address selectAddressByMerchantId(BigInteger merchant_id);
    ArrayList<Address> selectAllAddress();
    void deleteAddress(BigInteger address_id);
    Address selectAddressById(BigInteger address_id);
}
