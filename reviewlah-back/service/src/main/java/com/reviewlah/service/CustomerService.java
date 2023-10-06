package com.reviewlah.service;

import java.math.BigInteger;

public interface CustomerService {
    void insertMerchant(BigInteger user_id);
    BigInteger selectCustomerIdByUserId(BigInteger user_id);
}
