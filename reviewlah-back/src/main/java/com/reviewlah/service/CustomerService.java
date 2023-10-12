package com.reviewlah.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public interface CustomerService {
    void insertCustomer(BigInteger user_id);
    BigInteger selectCustomerIdByUserId(BigInteger user_id);
}
