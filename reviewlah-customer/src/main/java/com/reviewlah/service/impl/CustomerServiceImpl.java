package com.reviewlah.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewlah.db.dao.CustomerDao;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public void insertCustomer(Customer customer) {
        this.customerDao.insertCustomer(customer);
    }

    @Override
    public Customer selectCustomerByCustomerId(BigInteger customer_id) {
        return this.customerDao.selectCustomerByCustomerId(customer_id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        this.customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomerById(BigInteger user_id) {
        this.customerDao.deleteCustomerById(user_id);
    }

    @Override
    public Customer selectCustomerByName(String name) {
        return this.customerDao.selectCustomerByName(name);
    }
    public ArrayList<Customer> selectAllCustomer(){
        return this.customerDao.selectAllCustomer();
    }
}
