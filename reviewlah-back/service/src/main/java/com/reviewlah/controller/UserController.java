package com.reviewlah.controller;

import com.reviewlah.controller.form.DeleteUserRequest;
import com.reviewlah.controller.form.InsertUserRequest;
import com.reviewlah.controller.form.LoginRequest;
import com.reviewlah.controller.form.UpdateUserRequest;
import com.reviewlah.db.dao.UserDao;
import com.reviewlah.db.pojo.Address;
import com.reviewlah.db.pojo.MC;
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

    @RequestMapping("/personalInfo")
    public String personalInfo(){
        return "personalInfo";
    }
    @GetMapping("/get")
    public String get(){
        return "user";
    }
    @PostMapping({"/insert"})
    public String insertUser(@RequestBody InsertUserRequest request) {
        String name = request.getName();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        int type = request.getType();
        String avator = request.getAvator();
        User user = this.userService.selectUserByName(name);
        //        if(avator == null || avator == "") pic_post = "";
        if(user == null) {
            user = new User(name, phone_number, email, password, type, avator);
            this.userService.insertUser(user);
            if(type == 1) {
                User tmp = this.userService.selectUserByName(name);
                BigInteger user_id = tmp.getUser_id();
                this.customerService.insertCustomer(user_id);
            }
            if(type == 2) {
                //insert merchant
                User tmp = this.userService.selectUserByName(name);
                BigInteger user_id = tmp.getUser_id();
                this.merchantService.insertMerchant(user_id);
                //insert address
                BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
                String address_code = request.getAddress_code();
                String address_detail = request.getAddress_detail();
                String unitnum = request.getUnitnum();
                Address address = new Address(address_code, merchant_id,address_detail,unitnum);
                this.addressService.insertAddress(address);
                //insert MC
                ArrayList<String> category = request.getCategory();
                for(String category_name : category) {
                    int category_id = this.categoryService.selectCategoryIdByName(category_name);
                    MC mc = new MC(category_id, merchant_id);
                    this.mcService.insertMC(mc);
                }

            }
            System.out.println("successful");
        }
        else {
            System.out.println("failed");
        }
        return "register";
    }
    @PostMapping({"/personalInfo/update"})
    public String updateUser(@RequestBody UpdateUserRequest request) {
        BigInteger user_id = request.getUser_id();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        String avator = request.getAvator();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            user = new User(user_id, phone_number, email, password, avator);
            this.userService.updateUser(user);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
        }
        return "personalInfo";
    };
    @PostMapping({"/delete"})
    public void deleteUserById(@RequestBody DeleteUserRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            this.userService.deleteUserById(user_id);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
        }
    };
    @PostMapping({"/login"})
    public String login(@RequestBody LoginRequest request) {
        String name = request.getName();
        String password = request.getPassword();
        User user = this.userService.selectUserByName(name);
        if(user != null) {
            if(password.equals(user.getPassword())) {
                System.out.println("Login Successful");
                return "true";
            }
            else System.out.println("Password Error");
        }
        else {
            System.out.println("Username Error");
        }
        return "false";
    }
}
