package com.reviewlah.service;

import com.reviewlah.db.pojo.Customer;
import org.springframework.stereotype.Service;

import java.math.BigInteger;


public interface CustomerService {
    void insertCustomer(BigInteger user_id);
    BigInteger selectCustomerIdByUserId(BigInteger user_id);
    BigInteger selectUserIdByCustomerId(BigInteger customer_id);
    Customer selectCustomerByUserId(BigInteger user_id);
}
