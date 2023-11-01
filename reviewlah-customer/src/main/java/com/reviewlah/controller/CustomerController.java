package com.reviewlah.controller;

import java.math.BigInteger;
import java.util.ArrayList;

import com.reviewlah.controller.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewlah.common.util.RCode;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.service.CustomerService;

@RestController
@RequestMapping({"/customer"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customerDetail")
    public RCode selectCustomerById(@RequestBody SelectCustomerByIdRequest request) {
        BigInteger customer_id = request.getUser_id();
        Customer customer = customerService.selectCustomerByCustomerId(customer_id);
        if(customer != null) {
            return RCode.ok().put("customer", customer);
        }
        else {
            return RCode.error("Customer Does Not Exist");
        }
    }

    @GetMapping("personalPage")
    public RCode personalPage(@RequestBody SelectUserByIdRequest request) {
        BigInteger user_id = request.getUser_id();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        if(customer != null) {
            System.out.println("Successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", customer);
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/get")
    public RCode selectUserById(@RequestBody SelectUserByIdRequest request){
        BigInteger user_id = request.getUser_id();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        if(customer == null) {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", customer);
    }
    @PostMapping({"/insert"})
    public RCode insertCustomer(@RequestBody InsertCustomerRequest request) {
        String name = request.getName();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        String avator = request.getAvator();
        Customer customer = this.customerService.selectCustomerByName(name);
        if(customer == null) {
            if(password == null || password.isEmpty()) {
                System.out.println("Password Cannot Be Empty");
                return RCode.error("Password Cannot Be Empty");
            }
            if(email == null || email.isEmpty()) {
                System.out.println("Email Cannot Be Empty");
                return RCode.error("Email Cannot Be Empty");
            }
            if(phone_number == null || phone_number.isEmpty()) {
                System.out.println("phone_number Cannot Be Empty");
                return RCode.error("phone_number Cannot Be Empty");
            }
            if(avator == null || avator.isEmpty()) {
                avator = "http://defaultUserAvator";
            }
            customer = new Customer();
            customer.setName(name);
            customer.setPhone_number(phone_number);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setAvator(avator);
            this.customerService.insertCustomer(customer);
            System.out.println("successful");
        }
        else {
            System.out.println("Failed");
            return RCode.error("Failed");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/personalInfo/update"})
    public RCode updateCustomer(@RequestBody UpdateCustomerRequest request) {
        BigInteger user_id = request.getUser_id();
        String name = request.getName();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        String avator = request.getAvator();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        Customer tmp = this.customerService.selectCustomerByName(name);
        if(customer != null) {
            if(tmp != null && tmp.getCustomer_id() != customer.getCustomer_id()) {
                System.out.println("UserName Already Exists");
                return RCode.error("UserName Already Exists");
            }
            if(password == null || password.isEmpty()) {
                System.out.println("Password Cannot Be Empty");
                return RCode.error("Password Cannot Be Empty");
            }
            if(email == null || email.isEmpty()) {
                System.out.println("Email Cannot Be Empty");
                return RCode.error("Email Cannot Be Empty");
            }
            if(phone_number == null || phone_number.isEmpty()) {
                System.out.println("PhoneNumber Cannot Be Empty");
                return RCode.error("PhoneNumber Cannot Be Empty");
            }
            if(avator == null || avator.isEmpty()) {
                avator = "http://defaultUserAvator";
            }
            customer.setName(name);
            customer.setPhone_number(phone_number);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setAvator(avator);
            this.customerService.updateCustomer(customer);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    };
    @PostMapping({"/delete"})
    public RCode deleteCustomerById(@RequestBody DeleteCustomerRequest request) {
        BigInteger user_id = request.getUser_id();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        if(customer != null) {
            this.customerService.deleteCustomerById(user_id);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    };
    @PostMapping({"/login"})
    public RCode login(@RequestBody LoginRequest request) {
        String name = request.getName();
        String password = request.getPassword();
        Customer customer = this.customerService.selectCustomerByName(name);
        if(customer != null) {
            if(password.equals(customer.getPassword())) {
                System.out.println("Login Successful");
            }
            else {
                System.out.println("Password Error");
                return RCode.error("Password Error");
            }
        }
        else {
            System.out.println("Username Error");
            return RCode.error("Username Error");
        }
        return RCode.ok("Login Successful");
    }
    @GetMapping("/all")
    public RCode selectAllCustomer() {
        ArrayList<Customer> list = this.customerService.selectAllCustomer();
        return RCode.ok().put("list", list);
    }
}
