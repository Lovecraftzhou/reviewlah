package com.reviewlah.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteAdministratorRequest;
import com.reviewlah.controller.form.InsertAdministratorRequest;
import com.reviewlah.controller.form.LoginRequest;
import com.reviewlah.controller.form.UpdateAdministratorRequest;
import com.reviewlah.db.pojo.Administrator;
import com.reviewlah.service.AdminService;

@RestController
@RequestMapping({"/admin"})
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping({"/insert"})
    public RCode insertUser(@RequestBody InsertAdministratorRequest request) {
        String name = request.getName();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        String avator = request.getAvator();
        Administrator administrator = this.adminService.selectAdministratorByName(name);
        if(administrator == null) {
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
            administrator = new Administrator();
            administrator.setName(name);
            administrator.setPhone_number(phone_number);
            administrator.setEmail(email);
            administrator.setPassword(password);
            administrator.setAvator(avator);
            this.adminService.insertAdministrator(administrator);
            System.out.println("successful");
        }
        else {
            System.out.println("Failed");
            return RCode.error("Failed");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/personalInfo/update"})
    public RCode updateAdministrator(@RequestBody UpdateAdministratorRequest request) {
        BigInteger user_id = request.getUser_id();
        String name = request.getName();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        String avator = request.getAvator();
        Administrator administrator = this.adminService.selectAdministratorById(user_id);
        Administrator tmp = this.adminService.selectAdministratorByName(name);
        if(administrator != null) {
            if(tmp != null && tmp != administrator) {
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
            administrator.setName(name);
            administrator.setPhone_number(phone_number);
            administrator.setEmail(email);
            administrator.setPassword(password);
            administrator.setAvator(avator);
            this.adminService.updateAdministrator(administrator);
            System.out.println("successful");
        }
        else {
            System.out.println("Administrator Does Not Exist");
            return RCode.error("Administrator Does Not Exist");
        }
        return RCode.ok("successful");
    };
    @PostMapping({"/delete"})
    public RCode deleteAdministratorById(@RequestBody DeleteAdministratorRequest request) {
        BigInteger user_id = request.getUser_id();
        Administrator administrator = this.adminService.selectAdministratorById(user_id);
        if(administrator != null) {
            this.adminService.deleteAdministratorById(user_id);
            System.out.println("successful");
        }
        else {
            System.out.println("Administrator Does Not Exist");
            return RCode.error("Administrator Does Not Exist");
        }
        return RCode.ok("successful");
    };
    @PostMapping({"/login"})
    public RCode login(@RequestBody LoginRequest request) {
        String name = request.getName();
        String password = request.getPassword();
        Administrator administrator = this.adminService.selectAdministratorByName(name);
        if(administrator != null) {
            if(password.equals(administrator.getPassword())) {
                System.out.println("Login Successful");
                administrator.setPassword("");
                return RCode.ok("Login Successful").put("list", administrator);
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
