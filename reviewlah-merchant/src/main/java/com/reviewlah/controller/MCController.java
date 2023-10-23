package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.UpdateMCRequest;
import com.reviewlah.db.pojo.*;
import com.reviewlah.service.CategoryService;
import com.reviewlah.service.MCService;
import com.reviewlah.service.MerchantService;

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
    private MerchantService merchantService;
    @Autowired
    private MCService mcService;
    @Autowired
    private CategoryService categoryService;
    @PostMapping({"/update"})
    public RCode updateMC(@RequestBody UpdateMCRequest request) {
        BigInteger user_id = request.getUser_id();
        ArrayList<String> category_list = request.getCategory();
        Boolean flag = false;
        BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
        if(merchant_id != null) {
            for(String category_name : category_list) {
                Category category = this.categoryService.selectCategoryByName(category_name);
                if(category == null) {
                    System.out.println("Category Does Not Exist");
                    return RCode.error("Category Does Not Exist");
                }
                if(!flag) {
                    this.mcService.deleteMCByMerchantId(merchant_id);
                    flag = true;
                }
                MC mc = new MC(category.getCategory_id(), merchant_id);
                this.mcService.insertMC(mc);
                System.out.println("successful");
            }
        }
        else {
            System.out.println("Merchant Does Not Exist");
            return RCode.error("Merchant Does Not Exist");
        }
        return RCode.ok("successful");
    }
}
