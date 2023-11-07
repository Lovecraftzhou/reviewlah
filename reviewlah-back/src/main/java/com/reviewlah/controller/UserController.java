package com.reviewlah.controller;

import com.reviewlah.common.util.ImageUtil;
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
import java.util.Objects;

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
        String base64 = ImageUtil.convertImageToBase64Str(user.getAvator());
        String head = "data:image/jpg;base64,";
        String avator = head.concat(base64);
        user.setAvator(avator);
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
//            String test = "C://Users/86138/Desktop/reviewlah-avator/userDefaultAva.jpg";
//            String base64 = ImageUtil.convertImageToBase64Str(test);
//            String base64 = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEAAQADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD3+iiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKA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g6XcSOSzPNZxuWJ5JJI61Rb4deDXJJ8M6YpP9y3Vf5UAfK1B45NfU4+G/g0HP/CO2JHo0eR+Rq5beDPC1m6vbeG9JideQ6WUYYfjjNAHy5o+hat4hkCaPptzfZON8KZRT/tOflX8SK9k8EfByHTZotR8StFdXSEPFZx/NFGfVyR85HHH3Qc/e4I9bAAAAAAHQCnUAFfM3xk1CKb4l3kck0atbW8MGC2MDb5n/tQ19M1E1vC7FmhjZj1JUEmgD4rlu4UhdlmiLBSQN45NfZ2m2i6fpVnZL923gSIfRVA/pUv2W3/594v++BU1ABXn3xou47X4a3iO4Q3Fxbxgk46Sq5/RDXoNMeNJF2uqsOuGGaAPiz7Vb/8APeL/AL7Fe0fs/ojyeIrlGVwRbRgqQcY80n+Yr2j7Lb/88Iv++BTkijiB2IqZ67RigCSiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD/9k=";

//            String filename = "D://pic/" + name + ".jpg";
            String filename = System.getProperty("user.dir") + "\\reviewlah-back" + "\\pic\\pic-ava\\" + name + ".jpg";
            if(email == null || email.isEmpty()) {
                System.out.println("Email Cannot Be Empty");
                return RCode.error("Email Cannot Be Empty");
            }
            if(phone_number == null || phone_number.isEmpty()) {
                System.out.println("phone_number Cannot Be Empty");
                return RCode.error("phone_number Cannot Be Empty");
            }
            if(avator == null || avator.isEmpty()) {
                filename = System.getProperty("user.dir") + "\\reviewlah-back" + "\\pic\\pic-ava\\" + "reviewlah.jpg";
            }
            else {
                String[] tmp = avator.split(",");
                avator = tmp[1];
                ImageUtil.convertBase64StrToImage(avator, filename);
            }

            if(type == 1) {
                user = new User(name, phone_number, email, password, type, filename);
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
                user = new User(name, phone_number, email, password, type, filename);
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
            if(tmp != null && !Objects.equals(tmp.getUser_id(), user.getUser_id())) {
                System.out.println("UserName Already Exists");
                return RCode.error("UserName Already Exists");
            }
            String filename = "C://Users/86138/Desktop/reviewlah-avator/" + name + ".jpg";
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
                avator = "C://Users/86138/Desktop/reviewlah-avator/userDefaultAva.jpg";
                filename = "D://pic/reviewlah.jpg";
            }
            else {
                String[] str = avator.split(",");
                avator = str[1];
                ImageUtil.convertBase64StrToImage(avator, filename);
//                filename = "D://pic/reviewlah.jpg";
            }
            user.setName(name);
            user.setPhone_number(phone_number);
            user.setEmail(email);
            user.setPassword(password);
            user.setAvator(filename);
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
                String base64 = ImageUtil.convertImageToBase64Str(user.getAvator());
                String head = "data:image/jpg;base64,";
                String avator = head.concat(base64);
                user.setAvator(avator);
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
