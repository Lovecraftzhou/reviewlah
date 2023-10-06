package com.reviewlah.service;

import com.reviewlah.db.pojo.Address;

public interface AddressService {
    void insertAddress(Address address);
    void updateAddress(Address address);
}
