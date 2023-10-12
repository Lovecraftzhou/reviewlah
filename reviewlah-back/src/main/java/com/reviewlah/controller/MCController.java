package com.reviewlah.controller;

import com.reviewlah.controller.form.UpdateAddressRequest;
import com.reviewlah.controller.form.UpdateMCRequest;
import com.reviewlah.db.pojo.Address;
import com.reviewlah.db.pojo.MC;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.CategoryService;
import com.reviewlah.service.MCService;
import com.reviewlah.service.MerchantService;
import com.reviewlah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;

@RestController
@RequestMapping({"/merchant/category"})
public class MCController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MCService mcService;
    @Autowired
    private CategoryService categoryService;
    @PostMapping({"/update"})
    public String updateMC(@RequestBody UpdateMCRequest request) {
        BigInteger user_id = request.getUser_id();
        ArrayList<String> category = request.getCategory();
        User user = this.userService.selectUserById(user_id);
        if(user != null && user.getType() == 2) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            this.mcService.deleteMCByMerchantId(merchant_id);
            for(String category_name : category) {
                int category_id = this.categoryService.selectCategoryIdByName(category_name);
                MC mc = new MC(category_id, merchant_id);
                this.mcService.insertMC(mc);
            }
            System.out.println("successful");
        }
        else {
            System.out.println("Merchant Does Not Exist");
        }
        return "merchant/address";
    }
}
