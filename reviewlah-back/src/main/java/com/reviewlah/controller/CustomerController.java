package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.SelectUserByIdRequest;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.CustomerService;
import com.reviewlah.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;


@Tag(name = "客户模块")
@RestController
@RequestMapping({"/customer"})
public class CustomerController {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Operation(summary = "获取个人信息")
    @GetMapping("personalPage")
    public RCode selectUserById(@RequestBody SelectUserByIdRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            System.out.println("Successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", user);
    }
}
