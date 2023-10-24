package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.SelectAllRecommendMerchantRequest;
import com.reviewlah.controller.form.SelectMerchantByUserIdRequest;
import com.reviewlah.controller.form.SelectTop3CategoryFromBrowseHistoryRequest;
import com.reviewlah.db.pojo.*;
import com.reviewlah.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Tag(name = "商家模块")
@RestController
@RequestMapping({"/merchant"})
public class MerchantController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private DiningCommentService diningCommentService;
    @Autowired
    private BrowseHistoryService browseHistoryService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MCService mcService;
    @Operation(summary = "获取所有商家")
    @GetMapping("/merchantList")
    public RCode selectAllMerchant() {
        this.refreshAllMerchantRate();
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
                map.put("user_id", tmp.getUser_id());
                map.put("name", tmp.getName());
                map.put("avator", tmp.getAvator());
                map.put("avg_rate", merchant.getAvg_rate());
                map.put("address_code", address.getAddress_code());
                map.put("category", mc_list);
                res.add(map);
            }
        }
        return RCode.ok().put("list", res);
    }

    @Operation(summary = "获取推荐列表")
    @PostMapping("/merchantRecommendList")
    public RCode selectAllRecommendMerchant(@RequestBody SelectAllRecommendMerchantRequest request) {
        this.refreshAllMerchantRate();
        BigInteger user_id = request.getUser_id();
        Customer customer = this.customerService.selectCustomerByUserId(user_id);
        if(customer == null) {
            System.out.println("Customer Does Not Exist");
            return RCode.error("Customer Does Not Exist");
        }
        ArrayList<Integer> cate_list = this.selectTop3CategoryFromBrowseHistory(user_id);
        ArrayList<BigInteger> merchant_id_list = new ArrayList<>();
        for(Integer category_id : cate_list) {
            ArrayList<BigInteger> tmp_list = this.merchantService.selectRecMerchantByCategoryId(category_id);
            merchant_id_list.addAll(tmp_list);
        }
        HashSet<BigInteger> set = new HashSet<>();
        for (BigInteger i : merchant_id_list) {
            set.add(i);
        }
        merchant_id_list.clear();
        merchant_id_list.addAll(set);
        ArrayList<Map<String, Object>> res = new ArrayList<>();
        for(BigInteger merchant_id : merchant_id_list) {
            if(merchant_id != null) {
                Merchant merchant = this.merchantService.selectMerchantById(merchant_id);
                BigInteger tmp_id = merchant.getUser_id();
                User tmp = this.userService.selectUserById(tmp_id);
                Address address = this.addressService.selectAddressByMerchantId(merchant_id);
                ArrayList<String> mc_list = this.mcService.selectMCByMerchantId(merchant_id);
                Map<String, Object> map = new HashMap<>();
                map.put("user_id", tmp.getUser_id());
                map.put("name", tmp.getName());
                map.put("avator", tmp.getAvator());
                map.put("avg_rate", merchant.getAvg_rate());
                map.put("address_code", address.getAddress_code());
                map.put("category", mc_list);
                res.add(map);
            }
        }
        return RCode.ok().put("list", res);
    }

    @Operation(summary = "获取商家页面")
    @GetMapping("/merchantList/merchantPage")
    public RCode selectMerchantByUserId(@RequestBody SelectMerchantByUserIdRequest request) {
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
                map.put("user_id", user.getUser_id());
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
                return RCode.error("Merchant Does Not Exist");
            }
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", map);
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
    public ArrayList<Integer> selectTop3CategoryFromBrowseHistory(BigInteger user_id){
        Customer customer= this.customerService.selectCustomerByUserId(user_id);
        ArrayList<Integer> list = new ArrayList<>();
        if(customer != null){
            Date time_his = this.lastMonthDate();
            System.out.println(time_his);
            list = this.browseHistoryService.selectTop3CategoryFromBrowseHistory(customer.getCustomer_id(),time_his);
            System.out.println("successful");
        }
        else{
            System.out.println("Customer Does Not Exist");
        }
        return list;
    }
    public Date lastMonthDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return m;
    }
}
