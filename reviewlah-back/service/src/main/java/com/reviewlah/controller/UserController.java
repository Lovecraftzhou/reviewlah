package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import com.reviewlah.db.dao.UserDao;
import com.reviewlah.db.pojo.Address;
import com.reviewlah.db.pojo.MC;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;

@RestController
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MCService mcService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/get")
    public RCode selectUserById(@RequestBody SelectUserByIdRequest request){
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if(user == null) {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", user);
    }
    @PostMapping({"/insert"})
    public RCode insertUser(@RequestBody InsertUserRequest request) {
        String name = request.getName();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        int type = request.getType();
        String avator = request.getAvator();
        User user = this.userService.selectUserByName(name);
        if(user == null) {
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

            if(type == 1) {
                user = new User(name, phone_number, email, password, type, avator);
                this.userService.insertUser(user);
                User tmp = this.userService.selectUserByName(name);
                BigInteger user_id = tmp.getUser_id();
                this.customerService.insertCustomer(user_id);
            }
            if(type == 2) {
                String address_code = request.getAddress_code();
                String address_detail = request.getAddress_detail();
                String unitnum = request.getUnitnum();
                if(address_code == null || address_code.isEmpty()) {
                    System.out.println("Address Code Cannot Be Empty");
                    return RCode.error("Address Code Cannot Be Empty");
                }
                else if(address_code.length() != 6) {
                    System.out.println("Address Code is Invalid");
                    return RCode.error("Address Code is Invalid");
                }
                user = new User(name, phone_number, email, password, type, avator);
                this.userService.insertUser(user);
                //insert merchant
                User tmp = this.userService.selectUserByName(name);
                BigInteger user_id = tmp.getUser_id();
                Merchant merchant = new Merchant();
                merchant.setUser_id(user_id);
                merchant.setAvg_rate(0);
                this.merchantService.insertMerchant(merchant);
                //insert address
                BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
                Address address = new Address(address_code, merchant_id,address_detail,unitnum);
                this.addressService.insertAddress(address);
                //insert MC
                ArrayList<String> category = request.getCategory();
                for(String category_name : category) {
                    int category_id = this.categoryService.selectCategoryByName(category_name).getCategory_id();
                    MC mc = new MC(category_id, merchant_id);
                    this.mcService.insertMC(mc);
                }

            }
            System.out.println("successful");
        }
        else {
            System.out.println("Failed");
            return RCode.error("Failed");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/personalInfo/update"})
    public RCode updateUser(@RequestBody UpdateUserRequest request) {
        BigInteger user_id = request.getUser_id();
        String name = request.getName();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        String avator = request.getAvator();
        User user = this.userService.selectUserById(user_id);
        User tmp = this.userService.selectUserByName(name);
        if(user != null) {
            if(tmp != null && tmp.getUser_id() != user.getUser_id()) {
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
            user.setName(name);
            user.setPhone_number(phone_number);
            user.setEmail(email);
            user.setPassword(password);
            user.setAvator(avator);
            this.userService.updateUser(user);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    };
    @PostMapping({"/delete"})
    public RCode deleteUserById(@RequestBody DeleteUserRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            this.userService.deleteUserById(user_id);
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
        User user = this.userService.selectUserByName(name);
        if(user != null) {
            if(password.equals(user.getPassword())) {
                System.out.println("Login Successful");
                user.setPassword("");
                return RCode.ok("Login Successful").put("list", user);
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
    }
}
