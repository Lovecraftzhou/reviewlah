package com.reviewlah.service;

import com.reviewlah.db.pojo.Customer;

import java.math.BigInteger;

public interface CustomerService {
    void insertCustomer(Customer customer);

    Customer selectCustomerByCustomerId(BigInteger customer_id);

    void updateCustomer(Customer customer);

    void deleteCustomerById(BigInteger user_id);

    Customer selectCustomerByName(String name);
}
