package com.reviewlah.service.impl;

import com.reviewlah.db.dao.CustomerDao;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    public void insertCustomer(BigInteger user_id) {
        this.customerDao.insertCustomer(user_id);
    }
    public BigInteger selectCustomerIdByUserId(BigInteger user_id) {
        return this.customerDao.selectCustomerIdByUserId(user_id);
    }
    public BigInteger selectUserIdByCustomerId(BigInteger customer_id) {
        return this.customerDao.selectUserIdByCustomerId(customer_id);
    }
   public Customer selectCustomerByUserId(BigInteger user_id) {
        return this.customerDao.selectCustomerByUserId(user_id);
   }
}
