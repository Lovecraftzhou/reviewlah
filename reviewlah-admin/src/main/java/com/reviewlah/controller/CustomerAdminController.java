package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteCustomerByIdRequest;
import com.reviewlah.controller.form.InsertCustomerRequest;
import com.reviewlah.controller.form.UpdateCustomerRequest;
import com.reviewlah.remote.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping({"/admin/customer"})
public class CustomerAdminController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/customers/{user_id}")
    public RCode selectCustomerById(@PathVariable BigInteger user_id) {
        return this.customerService.selectCustomerById(user_id);
    }
    @PostMapping("/insert")
    public RCode insertCustomer(@RequestBody InsertCustomerRequest request) {
        return customerService.insertCustomer(request);
    }
    @PostMapping("/personalInfo/update")
    public RCode updateCustomer(@RequestBody UpdateCustomerRequest request) {
        return customerService.updateCustomer(request);
    }

    @PostMapping("/delete/{user_id}")
    public RCode deleteCustomerById(@PathVariable BigInteger user_id) {
        DeleteCustomerByIdRequest request = new DeleteCustomerByIdRequest();
        request.setUser_id(user_id);
        return customerService.deleteCustomerById(request);
    }

    @GetMapping("/all")
    public RCode selectAllCustomer() {
        return customerService.selectAllCustomer();
    }
}
