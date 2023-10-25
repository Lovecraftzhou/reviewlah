package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteCustomerByIdRequest;
import com.reviewlah.controller.form.InsertCustomerRequest;
import com.reviewlah.controller.form.UpdateCustomerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;

@FeignClient("customer")
public interface CustomerService {
    @GetMapping("/customer/customers/{customer_id}")
    RCode selectCustomerById(@PathVariable BigInteger customer_id);

    @PostMapping("/customer/insert")
    RCode insertCustomer(@RequestBody InsertCustomerRequest request);
    @PostMapping("/customer/personalInfo/update")
    RCode updateCustomer(@RequestBody UpdateCustomerRequest request);

    @PostMapping("/customer/delete")
    RCode deleteCustomerById(@RequestBody DeleteCustomerByIdRequest request);

    @GetMapping("/customer/all")
    RCode selectAllCustomer();
}
