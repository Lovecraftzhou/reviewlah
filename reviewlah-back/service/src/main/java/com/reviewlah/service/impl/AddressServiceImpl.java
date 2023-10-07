package com.reviewlah.service.impl;

import com.reviewlah.db.dao.AddressDao;
import com.reviewlah.db.pojo.Address;
import com.reviewlah.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;
    public void insertAddress(Address address) {
        this.addressDao.insertAddress(address);
    }
    public void updateAddress(Address address) {
        this.addressDao.updateAddress(address);
    }
}
