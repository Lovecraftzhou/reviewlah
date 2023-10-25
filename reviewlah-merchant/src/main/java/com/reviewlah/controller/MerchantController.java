package com.reviewlah.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteUserRequest;
import com.reviewlah.controller.form.InsertMerchantRequest;
import com.reviewlah.controller.form.LoginRequest;
import com.reviewlah.controller.form.SelectAllRecommendMerchantRequest;
import com.reviewlah.controller.form.SelectMerchantByUserIdRequest;
import com.reviewlah.controller.form.SelectUserByIdRequest;
import com.reviewlah.controller.form.UpdateUserRequest;
import com.reviewlah.db.pojo.Address;
import com.reviewlah.db.pojo.Announcement;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.db.pojo.DiningComment;
import com.reviewlah.db.pojo.MC;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.remote.CustomerService;
import com.reviewlah.remote.DiningCommentService;
import com.reviewlah.remote.SelectTop3CategoryFromBrowseHistoryRequest;
import com.reviewlah.service.AddressService;
import com.reviewlah.service.AnnouncementService;
import com.reviewlah.service.CategoryService;
import com.reviewlah.service.DishService;
import com.reviewlah.service.MCService;
import com.reviewlah.service.MenuService;
import com.reviewlah.service.MerchantService;

@RestController
@RequestMapping({"/merchant"})
public class MerchantController {
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
    @Autowired
    private CustomerService customerService;
    private final ObjectMapper mapper = new ObjectMapper();

    private ArrayList<DiningComment> selectDCByMerchantId(@PathVariable BigInteger merchant_id) {
        RCode rCode = diningCommentService.selectDCByMerchantId(merchant_id);
        if (rCode.get("comments") == null) {
            return new ArrayList<>();
        }
        return mapper.convertValue(rCode.get("comments"), new TypeReference<>() {
        });
    }

    private Customer selectCustomerById(BigInteger customer_id) {
        RCode rCode = customerService.selectCustomerById(customer_id);
        if (rCode.get("customer") == null) {
            return null;
        }
        return mapper.convertValue(rCode.get("customer"), Customer.class);
    }

    private List<Integer> selectTop3CategoryFromBrowseHistory(BigInteger customer_id) {
        SelectTop3CategoryFromBrowseHistoryRequest request = new SelectTop3CategoryFromBrowseHistoryRequest();
        request.setUser_id(customer_id);
        request.setTime_his(new Date());
        RCode rCode = customerService.selectTop3CategoryFromBrowseHistory(request);
        if (rCode.get("list") == null) {
            return null;
        }
        return mapper.convertValue(rCode.get("list"), new TypeReference<>() {
        });
    }

    @PostMapping("/avg-rate/{merchant_id}")
    public RCode updateAvgRateByMerchantId(@PathVariable BigInteger merchant_id, @RequestParam double avg_rate) {
        Merchant merchant = merchantService.selectMerchantById(merchant_id);
        if (merchant != null) {
            merchant.setAvg_rate(avg_rate);
            merchantService.updateMerchant(merchant);
        }
        return RCode.ok();
    }

    @GetMapping("/merchants/{merchant_id}")
    public RCode selectMerchantById(@PathVariable BigInteger merchant_id) {
        Merchant merchant = merchantService.selectMerchantById(merchant_id);
        return RCode.ok().put("merchant", merchant);
    }

    @GetMapping("/merchantList")
    public RCode selectAllMerchant() {
        ArrayList<Merchant> merchant_list = this.merchantService.selectAllMerchant();
        ArrayList<Map<String, Object>> res = new ArrayList<>();
        for(Merchant merchant : merchant_list) {
            if(merchant != null) {
                BigInteger merchant_id = merchant.getMerchant_id();
                Address address = this.addressService.selectAddressByMerchantId(merchant_id);
                ArrayList<String> mc_list = this.mcService.selectMCByMerchantId(merchant_id);
                Map<String, Object> map = new HashMap<>();
                map.put("merchant_id", merchant.getMerchant_id());
                map.put("name", merchant.getName());
                map.put("avator", merchant.getAvator());
                map.put("avg_rate", merchant.getAvg_rate());
                map.put("address_code", address.getAddress_code());
                map.put("category", mc_list);
                res.add(map);
            }
        }
        return RCode.ok().put("list", res);
    }
    @PostMapping("/merchantRecommendList")
    public RCode selectAllRecommendMerchant(@RequestBody SelectAllRecommendMerchantRequest request) {
        BigInteger user_id = request.getUser_id();
        Customer customer = selectCustomerById(user_id);
        if(customer == null) {
            System.out.println("Customer Does Not Exist");
            return RCode.error("Customer Does Not Exist");
        }
        List<Integer> cate_list = this.selectTop3CategoryFromBrowseHistory(user_id);
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
                Address address = this.addressService.selectAddressByMerchantId(merchant_id);
                ArrayList<String> mc_list = this.mcService.selectMCByMerchantId(merchant_id);
                Map<String, Object> map = new HashMap<>();
                map.put("user_id", merchant.getMerchant_id());
                map.put("name", merchant.getName());
                map.put("avator", merchant.getAvator());
                map.put("avg_rate", merchant.getAvg_rate());
                map.put("address_code", address.getAddress_code());
                map.put("category", mc_list);
                res.add(map);
            }
        }
        return RCode.ok().put("list", res);
    }

    @PostMapping("/merchantList/merchantPage")
    public RCode selectMerchantByUserId(@RequestBody SelectMerchantByUserIdRequest request) {
        BigInteger user_id = request.getUser_id();
        Map<String, Object> map = new HashMap<>();
        Merchant merchant = this.merchantService.selectMerchantById(user_id);
        if(merchant != null) {
            BigInteger merchant_id = merchant.getMerchant_id();
            Address address = this.addressService.selectAddressByMerchantId(merchant_id);
            ArrayList<String> mc_list = this.mcService.selectMCByMerchantId(merchant_id);
            ArrayList<HashMap> dish_list = this.dishService.selectDishByMerchantId(merchant_id);
            ArrayList<Announcement> announcements = this.announcementService.selectAnnouncementByMerchantId(merchant_id);
            ArrayList<DiningComment> diningComments = selectDCByMerchantId(merchant_id);
            map.put("merchant_id", merchant.getMerchant_id());
            map.put("name", merchant.getName());
            map.put("phone_number", merchant.getPhone_number());
            map.put("avator", merchant.getAvator());
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
        return RCode.ok().put("list", map);
    }
    public Date lastMonthDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return m;
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/get")
    public RCode selectUserById(@RequestBody SelectUserByIdRequest request){
        BigInteger user_id = request.getUser_id();
        Merchant merchant = this.merchantService.selectMerchantById(user_id);
        if(merchant == null) {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", merchant);
    }
    @PostMapping({"/insert"})
    public RCode insertUser(@RequestBody InsertMerchantRequest request) {
        String name = request.getName();
        String phone_number = request.getPhone_number();
        String email = request.getEmail();
        String password = request.getPassword();
        String avator = request.getAvator();
        Merchant user = this.merchantService.selectMerchantByName(name);
        if(user == null) {
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
            Merchant merchant = new Merchant();
            merchant.setName(name);
            merchant.setPhone_number(phone_number);
            merchant.setEmail(email);
            merchant.setPassword(password);
            merchant.setAvator(avator);
            merchant.setAvg_rate(0);
            this.merchantService.insertMerchant(merchant);
            //insert address
            BigInteger merchant_id = merchant.getMerchant_id();
            Address address = new Address(address_code, merchant_id,address_detail,unitnum);
            this.addressService.insertAddress(address);
            //insert MC
            ArrayList<String> category = request.getCategory();
            if (category != null) {
                for (String category_name : category) {
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
        Merchant merchant = this.merchantService.selectMerchantById(user_id);
        Merchant tmp = this.merchantService.selectMerchantByName(name);
        if(merchant != null) {
            if(tmp != null && tmp != merchant) {
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
            merchant.setName(name);
            merchant.setPhone_number(phone_number);
            merchant.setEmail(email);
            merchant.setPassword(password);
            merchant.setAvator(avator);
            this.merchantService.updateMerchant(merchant);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/delete"})
    public RCode deleteUserById(@RequestBody DeleteUserRequest request) {
        BigInteger user_id = request.getUser_id();
        Merchant user = this.merchantService.selectMerchantById(user_id);
        if(user != null) {
            this.merchantService.deleteMerchantById(user_id);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/login"})
    public RCode login(@RequestBody LoginRequest request) {
        String name = request.getName();
        String password = request.getPassword();
        Merchant merchant = this.merchantService.selectMerchantByName(name);
        if(merchant != null) {
            if(password.equals(merchant.getPassword())) {
                System.out.println("Login Successful");
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
        return RCode.ok("Login Successful");
    }
}
