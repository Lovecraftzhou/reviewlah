package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Address;

public interface AddressDao {
    void insertAddress(Address address);
    void updateAddress(Address address);
}
