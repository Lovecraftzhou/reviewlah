package com.reviewlah.controller;

import com.reviewlah.controller.form.SelectMerchantByUserIdRequest;
import com.reviewlah.db.pojo.*;
import com.reviewlah.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({"/merchant"})
public class MerchantController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private DiningCommentService diningCommentService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MCService mcService;
    @GetMapping("/merchantList")
    public ArrayList<Map<String, Object>> selectAllMerchant() {
        refreshAllMerchantRate();
        ArrayList<Merchant> merchant_list = this.merchantService.selectAllMerchant();
        ArrayList<Map<String, Object>> res = new ArrayList<>();
        for(Merchant merchant : merchant_list) {
            if(merchant != null) {
                BigInteger user_id = merchant.getUser_id();
                User tmp = this.userService.selectUserById(user_id);
                BigInteger merchant_id = merchant.getMerchant_id();
                Address address = this.addressService.selectAddressByMerchantId(merchant_id);
                ArrayList<String> mc_list = this.mcService.selectMCByMerchantId(merchant_id);
                Map<String, Object> map = new HashMap<>();
                map.put("name", tmp.getName());
                map.put("avator", tmp.getAvator());
                map.put("avg_rate", merchant.getAvg_rate());
                map.put("address_code", address.getAddress_code());
                map.put("category", mc_list);
                res.add(map);
            }
        }
        return res;
    }
    @GetMapping("/merchantList/merchantPage")
    public Map<String, Object> selectMerchantByUserId(@RequestBody SelectMerchantByUserIdRequest request) {
        BigInteger user_id = request.getUser_id();
        Map<String, Object> map = new HashMap<>();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            Merchant merchant = this.merchantService.selectMerchantByUserId(user_id);
            if(merchant != null) {
                BigInteger merchant_id = merchant.getMerchant_id();
                Address address = this.addressService.selectAddressByMerchantId(merchant_id);
                ArrayList<String> mc_list = this.mcService.selectMCByMerchantId(merchant_id);
                ArrayList<HashMap> dish_list = this.dishService.selectDishByMerchantId(merchant_id);
                ArrayList<Announcement> announcements = this.announcementService.selectAnnouncementByMerchantId(merchant_id);
                ArrayList<DiningComment> diningComments = this.diningCommentService.selectDCByMerchantId(merchant_id);
                map.put("name", user.getName());
                map.put("phone_number", user.getPhone_number());
                map.put("avator", user.getAvator());
                map.put("avg_rate", merchant.getAvg_rate());
                map.put("address_code", address.getAddress_code());
                map.put("category", mc_list);
                map.put("dish", dish_list);
                map.put("announcement", announcements);
                map.put("diningComment", diningComments);
            }
            else {
                System.out.println("Merchant Does Not Exist");
            }
        }
        else {
            System.out.println("User Does Not Exist");
        }
        return map;
    }
    public void refreshAllMerchantRate() {
        ArrayList<Merchant> list = this.merchantService.selectAllMerchant();
        for(Merchant merchant : list) {
            BigInteger merchant_id = merchant.getMerchant_id();
            Double rate = this.diningCommentService.getAverageRateByMerchantId(merchant_id);
            if(rate == null) rate = 0.0;
            BigDecimal bd = new BigDecimal(rate);
            double avg_rate = bd.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
            this.merchantService.updateRateByMerchantId(merchant_id, avg_rate);
        }
    }
}
