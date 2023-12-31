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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
@Tag(name = "游览记录模块")
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

    @Operation(summary = "获取游览记录")
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
    @Operation(summary = "获取游览最多的餐厅种类")
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

    @Operation(summary = "获取用户所有游览记录")
    @PostMapping({"/allHistoryForCustomer"})
    public RCode selectBrowseHistoryByCustomerId(@RequestBody SelectBrowseHistoryByCustomerIdRequest request){
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        ArrayList<BrowseHistory> list = new ArrayList<BrowseHistory>();
        if (user != null && user.getType() == 1) {
            Customer customer = this.customerService.selectCustomerByUserId(user_id);
            if (customer !=null) {
                list = this.browseHistoryService.selectBrowseHistoryByCustomerId(customer.getCustomer_id());
                System.out.println("successful");
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
        return RCode.ok().put("list", list);
    }
    @Operation(summary = "获取某时间之前的游览记录")
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
    @Operation(summary = "新游览记录")
    @PostMapping({"/insertHistory"})
    public RCode insertBrowseHistory(@RequestBody InsertBrowseHistoryRequest request){
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        String category_name=request.getCategory_name();
        int category_id = this.categoryService.selectCategoryByName(category_name).getCategory_id();
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
    @Operation(summary = "删除游览记录")
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