package com.reviewlah.controller;

import com.reviewlah.common.util.ImageUtil;
import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.db.pojo.Post;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.CustomerService;
import com.reviewlah.service.PostService;
import com.reviewlah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping({"/post"})
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PostService postService;
    @PostMapping({"/insert"})
    public RCode insertPost(@RequestBody InsertPostRequest request) {
        BigInteger user_id = request.getUser_id();
        String title = request.getTitle();
        String content = request.getContent();
        String pic_post = request.getPic_post();
        Date date = new Date();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            if(content == null || content.isEmpty()) {
                System.out.println("Content Cannot Be Empty");
                return RCode.error("Content Cannot Be Empty");
            }
            if(title == null || title.isEmpty()) {
                System.out.println("Title Cannot Be Empty");
                return RCode.error("Title Cannot Be Empty");
            }
//            String filename = "C://Users/86138/Desktop/reviewlah-postpic/" + user.getName() + title + ".jpg";
            String filename = System.getProperty("user.dir") + "\\reviewlah-back" + "\\pic\\pic-post\\" + user.getName() + title + ".jpg";
//            String test = "C://Users/86138/Desktop/reviewlah-postpic/userDefaultAva.jpg";
//            String base64 = ImageUtil.convertImageToBase64Str(test);
            if(pic_post == null || pic_post.isEmpty()) {
                filename = System.getProperty("user.dir") + "\\reviewlah-back" + "\\pic\\pic-post\\" + "reviewlah.jpg";
            }
            else {
                String[] tmp = pic_post.split(",");
                pic_post = tmp[1];
                ImageUtil.convertBase64StrToImage(pic_post, filename);
//                filename = "D://pic/food1.jpg";
            }
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_id);
            Post post = new Post();
            post.setCustomer_id(customer_id);
            post.setTitle(title);
            post.setContent(content);
            post.setPic_post(filename);
            post.setTime_post(date);
            this.postService.insertPost(post);
            System.out.println("successful");
        }
        else {
            System.out.println("Failed");
            return RCode.error("Failed");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/delete"})
    public RCode deletePost(@RequestBody DeletePostRequest request) {
        BigInteger post_id = request.getPost_id();
        Post post = this.postService.selectPostByPostId(post_id);
        if(post != null) {
            this.postService.deletePostByPostId(post_id);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/mypost"})
    public RCode selectPostByCustomerId(@RequestBody SelectPostByCustomerIdRequest request) {
        BigInteger user_Id = request.getUser_id();
        User user = this.userService.selectUserById(user_Id);
        ArrayList<HashMap> list = new ArrayList<>();
        if(user != null && user.getType() == 1) {
            Customer customer = this.customerService.selectCustomerByUserId(user_Id);
            if(customer != null) {
                list = this.postService.selectPostByCustomerId(customer.getCustomer_id());
                for(HashMap map : list) {
                    Object obj = map.get("pic_post");
                    String pic_post = obj.toString();
                    String base64 = ImageUtil.convertImageToBase64Str(pic_post);
                    String head = "data:image/jpg;base64,";
                    pic_post = head.concat(base64);
                    map.put("pic_post", pic_post);
                    Object obj_ava = map.get("avator");
                    String avator = obj_ava.toString();
                    String base64_ava = ImageUtil.convertImageToBase64Str(avator);
                    String head_ava = "data:image/jpg;base64,";
                    avator = head_ava.concat(base64_ava);
                    map.put("avator", avator);
                }
                System.out.println("successful");
            }
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/homepage"})
    public RCode selectAllPostExceptMine(@RequestBody SelectPostByCustomerIdRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        ArrayList<HashMap> list = new ArrayList<>();
        if(user != null) {
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_id);
            list = this.postService.selectAllPostExceptMine(customer_id);
            for(HashMap map : list) {
                Object obj = map.get("pic_post");
                String pic_post = obj.toString();
                String base64 = ImageUtil.convertImageToBase64Str(pic_post);
                String head = "data:image/jpg;base64,";
                pic_post = head.concat(base64);
                map.put("pic_post", pic_post);
                Object obj_ava = map.get("avator");
                String avator = obj_ava.toString();
                String base64_ava = ImageUtil.convertImageToBase64Str(avator);
                String head_ava = "data:image/jpg;base64,";
                avator = head_ava.concat(base64_ava);
                map.put("avator", avator);
            }
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/homepage/relative"})
    public RCode selectRelativePost(@RequestBody SelectRelativePostRequest request) {
        BigInteger user_id = request.getUser_id();
        String keyword = request.getKeyword();
        User user = this.userService.selectUserById(user_id);
        ArrayList<HashMap> list = new ArrayList<>();
        if(user != null) {
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_id);
            list = this.postService.selectRelativePost(keyword, customer_id);
            for(HashMap map : list) {
                Object obj = map.get("pic_post");
                String pic_post = obj.toString();
                String base64 = ImageUtil.convertImageToBase64Str(pic_post);
                String head = "data:image/jpg;base64,";
                pic_post = head.concat(base64);
                map.put("pic_post", pic_post);
                Object obj_ava = map.get("avator");
                String avator = obj_ava.toString();
                String base64_ava = ImageUtil.convertImageToBase64Str(avator);
                String head_ava = "data:image/jpg;base64,";
                avator = head_ava.concat(base64_ava);
                map.put("avator", avator);
            }
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/detail"})
    public RCode selectPostByPostId(@RequestBody SelectPostByPostIdRequest request) {
        BigInteger post_id = request.getPost_id();
        Post post = this.postService.selectPostByPostId(post_id);
        if(post == null) {
            System.out.println("Post Does Not Exist");
            return RCode.error("Post Does Not Exist");
        }
        else {
            String base64 = ImageUtil.convertImageToBase64Str(post.getPic_post());
            String head = "data:image/jpg;base64,";
            String pic_post = head.concat(base64);
            post.setPic_post(pic_post);
            System.out.println("successful");
        }
        return RCode.ok().put("list", post);
    }
}
