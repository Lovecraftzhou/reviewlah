package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Customer;

import java.math.BigInteger;

public interface CustomerDao {
    void insertCustomer(BigInteger user_id);
    BigInteger selectCustomerIdByUserId(BigInteger user_id);
    BigInteger selectUserIdByCustomerId(BigInteger customer_id);
    Customer selectCustomerByUserId(BigInteger user_id);
}
