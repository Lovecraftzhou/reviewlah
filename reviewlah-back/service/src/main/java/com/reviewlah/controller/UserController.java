package com.reviewlah.controller;

import com.reviewlah.controller.form.InsertUserRequest;
import com.reviewlah.db.dao.UserDao;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;

@Controller
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/get")
    public  String get(){
        return "Hello";
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
        if(user == null) {
            user = new User(name, phone_number, email, password, type, avator);
            userService.insertUser(user);
            System.out.println("successful");
        }
        else {
            System.out.println("failed");
        }
        return "register";
    }
    public void updateUser(User user) {

    };
    public void deleteUserById(BigInteger user_id) {

    };
}
