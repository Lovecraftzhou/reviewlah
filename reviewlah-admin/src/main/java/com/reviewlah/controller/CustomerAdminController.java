package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteCustomerByIdRequest;
import com.reviewlah.controller.form.InsertCustomerRequest;
import com.reviewlah.controller.form.SelectCustomerByIdRequest;
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
    @PostMapping("/customerDetail")
    public RCode selectCustomerById(@RequestBody SelectCustomerByIdRequest request) {
        return this.customerService.selectCustomerById(request);
    }
    @PostMapping("/insert")
    public RCode insertCustomer(@RequestBody InsertCustomerRequest request) {
        return customerService.insertCustomer(request);
    }
    @PostMapping("/personalInfo/update")
    public RCode updateCustomer(@RequestBody UpdateCustomerRequest request) {
        return customerService.updateCustomer(request);
    }

    @PostMapping("/delete")
    public RCode deleteCustomerById(@RequestBody DeleteCustomerByIdRequest request) {
        return customerService.deleteCustomerById(request);
    }

    @GetMapping("/all")
    public RCode selectAllCustomer() {
        return customerService.selectAllCustomer();
    }
}
