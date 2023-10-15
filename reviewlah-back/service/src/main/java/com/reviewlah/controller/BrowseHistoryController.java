package com.reviewlah.controller;

import com.reviewlah.controller.form.*;
import com.reviewlah.db.pojo.Announcement;
import com.reviewlah.db.pojo.BrowseHistory;
import com.reviewlah.db.pojo.Category;
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
    public BrowseHistory selectBrowseHistoryByHistoryID(@RequestBody SelectBrowseHistoryByHistoryIDRequest request){
        BigInteger history_id=request.getHistory_id();
        BrowseHistory browseHistory=this.browseHistoryService.selectBrowseHistoryByHistoryID(history_id);
        if (history_id==null){
            System.out.println("History Does Not Exist");
        }
        else{
            System.out.println("successful");
            return browseHistory;
        }
        return null;

    }
    @PostMapping({"/customerHistory"})
    public int selectBrowseHistoryByCustomerIDAndCategory(@RequestBody SelectBrowseHistoryByCustomerIDAndCategoryRequest request){
        BigInteger user_id=request.getUser_id();
        String category_name=request.getCatgoryName();
        User user = this.userService.selectUserById(user_id);
        if (user != null && user.getType() == 1) {
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_id);
            if (customer_id !=null) {
                int category_id=this.categoryService.selectCategoryIdByName(category_name);
                if (category_id!=0){
                    int time;
                    time=this.browseHistoryService.selectBrowseHistoryByCustomerIDAndCategory(customer_id,category_id);
                    System.out.println("successful");
                    return time;
                }
                else{
                    System.out.println("Category Does Not Exist");
                }
            }
            else{
                System.out.println("Customer Does Not Exist");
            }
        }
        else{
            System.out.println("User Does Not Exist");
        }
        return 0;
    }

    @PostMapping({"/historyMerchant"})
    public ArrayList<BrowseHistory> selectBrowseHistoryByMerchantID(@RequestBody SelectBrowseHistoryByMerchantIDRequest request){
        BigInteger user_id=request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if (user != null && user.getType() == 2) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if (merchant_id !=null) {
                ArrayList<BrowseHistory> list = new ArrayList<BrowseHistory>();
                list =this.browseHistoryService.selectBrowseHistoryByMerchantID(merchant_id);
                System.out.println("successful");
                return list;
            }
            else{
                System.out.println("Merchant Does Not Exist");
            }
        }
        else{
            System.out.println("User Does Not Exist");
        }
        return null;
    }
    @PostMapping({"/historyBeforeTimeStamp"})
    public ArrayList<BrowseHistory> selectBrowseHistoryByTimeHis(@RequestBody SelectBrowseHistoryByTimeHisRequest request){
        Date time_his=request.getTime_his();
        if(time_his!=null){
            ArrayList<BrowseHistory> list = new ArrayList<BrowseHistory>();
            list=this.browseHistoryService.selectBrowseHistoryByTimeHis(time_his);
            System.out.println("successful");
            return list;
        }
        else{
            System.out.println("time is valid");
        }
        return null;
    }
    @PostMapping({"/insertHistory"})
    public void insertBrowseHistory(@RequestBody InsertBrowseHistoryRequest request){
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if (user != null && user.getType() == 1){
            BigInteger customer_id=this.customerService.selectCustomerIdByUserId(user_id);
            if(customer_id!=null){
                int category_id=request.getCategory_id();
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
                }
            }
            else{
                System.out.println("Customer Does Not Exist");
            }
        }
        else{
            System.out.println("User Does Not Exist");
        }
    }
    @PostMapping({"/deleteHistory"})
    public void deleteBrowseHistoryByHistoryID(@RequestBody DeleteBrowseHistoryByHistoryIDRequest request){
        BigInteger history_id=request.getHistory_id();
        BrowseHistory browseHistory=this.browseHistoryService.selectBrowseHistoryByHistoryID(history_id);
        if(browseHistory!=null){
            this.browseHistoryService.deleteBrowseHistoryByHistoryID(history_id);
            System.out.println("successful");
        }
        else{
            System.out.println("History Does Not Exist");
        }

    }



}
