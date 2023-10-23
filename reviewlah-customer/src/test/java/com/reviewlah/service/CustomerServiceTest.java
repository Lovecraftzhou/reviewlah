package com.reviewlah.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Transactional
@SpringBootTest
class CustomerServiceTest {
    @Autowired
    private CustomerService service;

    @Test
    void test() {
        Customer customer = new Customer();
        customer.setPassword("password");
        customer.setAvator("avator");
        customer.setEmail("mock@test.com");
        customer.setName("mock");
        customer.setPhone_number("12345678");
        service.insertCustomer(customer);
        customer = service.selectCustomerByCustomerId(customer.getCustomer_id());
        assertNotNull(customer);
        customer = service.selectCustomerByName("mock");
        assertNotNull(customer);
        customer.setName("updated");
        service.updateCustomer(customer);
        customer = service.selectCustomerByCustomerId(customer.getCustomer_id());
        assertEquals("updated", customer.getName());
        service.deleteCustomerById(customer.getCustomer_id());
        customer = service.selectCustomerByCustomerId(customer.getCustomer_id());
        assertNull(customer);
    }
}
