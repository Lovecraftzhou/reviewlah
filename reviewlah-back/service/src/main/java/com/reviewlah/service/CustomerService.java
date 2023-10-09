package com.reviewlah.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;


public interface CustomerService {
    void insertCustomer(BigInteger user_id);
    BigInteger selectCustomerIdByUserId(BigInteger user_id);
    BigInteger selectUserIdByCustomerId(BigInteger customer_id);
}
