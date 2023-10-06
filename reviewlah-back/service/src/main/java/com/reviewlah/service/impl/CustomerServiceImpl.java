package com.reviewlah.service.impl;

import com.reviewlah.db.dao.CustomerDao;
import com.reviewlah.service.CustomerService;

import java.math.BigInteger;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;
    public void insertMerchant(BigInteger user_id) {
        this.customerDao.insertCustomer(user_id);
    }
    public BigInteger selectCustomerIdByUserId(BigInteger user_id) {
        BigInteger customer_id = this.customerDao.selectCustomerIdByUserId(user_id);
        return customer_id;
    }
}
