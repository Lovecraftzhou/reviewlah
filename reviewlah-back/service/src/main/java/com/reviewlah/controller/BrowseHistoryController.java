package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import com.reviewlah.db.pojo.BrowseHistory;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.BrowseHistoryService;
import com.reviewlah.service.MerchantService;
import com.reviewlah.service.UserService;
import com.reviewlah.service.CategoryService;
import com.reviewlah.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping({"/user/browseHistory"})
public class BrowseHistoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private BrowseHistoryService browseHistoryService;

    @PostMapping({"/historyDetail"})
    public RCode selectBrowseHistoryByHistoryID(@RequestBody SelectBrowseHistoryByHistoryIDRequest request){
        BigInteger history_id=request.getHistory_id();
        BrowseHistory browseHistory=this.browseHistoryService.selectBrowseHistoryByHistoryID(history_id);
        if (history_id==null){
            System.out.println("History Does Not Exist");
            return RCode.error("History Does Not Exist");
        }
        else{
            System.out.println("successful");
        }
        return RCode.ok().put("list", browseHistory);

    }
    @PostMapping({"/customerHistory"})
    public RCode selectTop3CategoryFromBrowseHistory(@RequestBody SelectTop3CategoryFromBrowseHistoryRequest request){
        BigInteger user_id=request.getUser_id();
        //BigInteger customer_id=request.getCustomer_id();
        BigInteger customer_id=this.customerService.selectCustomerIdByUserId(user_id);
        ArrayList<Integer> list = new ArrayList<>();
        if(customer_id!=null){
            Date time_his=request.getTime();
            if(time_his == null) time_his = this.lastMonthDate();
            System.out.println(time_his);
            list=this.browseHistoryService.selectTop3CategoryFromBrowseHistory(customer_id,time_his);
            System.out.println("successful");
        }
        else{
            System.out.println("Customer Does Not Exist");
            return RCode.error("Customer Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }

    @PostMapping({"/historyMerchant"})
    public RCode selectBrowseHistoryByMerchantID(@RequestBody SelectBrowseHistoryByMerchantIDRequest request){
        BigInteger user_id=request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        ArrayList<BrowseHistory> list = new ArrayList<BrowseHistory>();
        if (user != null && user.getType() == 2) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if (merchant_id !=null) {
                list =this.browseHistoryService.selectBrowseHistoryByMerchantID(merchant_id);
                System.out.println("successful");
            }
            else{
                System.out.println("Merchant Does Not Exist");
                return RCode.error("Merchant Does Not Exist");
            }
        }
        else{
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/historyBeforeTimeStamp"})
    public RCode selectBrowseHistoryByTimeHis(@RequestBody SelectBrowseHistoryByTimeHisRequest request){
        Date time_his=request.getTime_his();
        ArrayList<BrowseHistory> list = new ArrayList<BrowseHistory>();
        if(time_his!=null){
            list=this.browseHistoryService.selectBrowseHistoryByTimeHis(time_his);
            System.out.println("successful");
        }
        else{
            System.out.println("Time is Valid");
            return RCode.error("Time is Valid");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/insertHistory"})
    public RCode insertBrowseHistory(@RequestBody InsertBrowseHistoryRequest request){
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        String category_name=request.getCategory_name();
        int category_id=this.categoryService.selectCategoryIdByName(category_name);
        if (user != null && user.getType() == 1){
            BigInteger customer_id=this.customerService.selectCustomerIdByUserId(user_id);
            if(customer_id!=null){
                if(category_id!=0){
                    Date time_his = new Date();
                    BrowseHistory browseHistory=new BrowseHistory();
                    browseHistory.setCategory_id(category_id);
                    browseHistory.setTime_his(time_his);
                    browseHistory.setCustomer_id(customer_id);
                    this.browseHistoryService.insertBrowseHistory(browseHistory);
                    System.out.println("successful");
                }
                else{
                    System.out.println("Category is valid");
                    return RCode.error("Category Does Not Exist");
                }
            }
            else{
                System.out.println("Customer Does Not Exist");
                return RCode.error("Customer Does Not Exist");
            }
        }
        else{
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/deleteHistory"})
    public RCode deleteBrowseHistoryByHistoryID(@RequestBody DeleteBrowseHistoryByHistoryIDRequest request){
        BigInteger history_id=request.getHistory_id();
        BrowseHistory browseHistory=this.browseHistoryService.selectBrowseHistoryByHistoryID(history_id);
        if(browseHistory!=null){
            this.browseHistoryService.deleteBrowseHistoryByHistoryID(history_id);
            System.out.println("successful");
        }
        else{
            System.out.println("History Does Not Exist");
            return RCode.error("History Does Not Exist");
        }
        return RCode.ok("successful");
    }

    public Date lastMonthDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return m;
    }


}