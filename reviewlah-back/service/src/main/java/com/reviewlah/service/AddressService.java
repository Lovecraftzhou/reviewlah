package com.reviewlah.service;

import com.reviewlah.db.pojo.Address;
import org.springframework.stereotype.Service;


public interface AddressService {
    void insertAddress(Address address);
    void updateAddress(Address address);
}
