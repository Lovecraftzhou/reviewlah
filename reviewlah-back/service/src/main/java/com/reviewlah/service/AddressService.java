package com.reviewlah.service;

import com.reviewlah.db.vo.Address;

public interface AddressService {
    void insertAddress(Address address);
    void updateAddress(Address address);
}
