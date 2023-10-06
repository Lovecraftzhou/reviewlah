package com.reviewlah.db.dao;

import com.reviewlah.db.vo.Address;

public interface AddressDao {
    void insertAddress(Address address);
    void updateAddress(Address address);
}
