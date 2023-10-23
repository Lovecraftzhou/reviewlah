package com.reviewlah.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteBrowseHistoryByHistoryIDRequest;
import com.reviewlah.controller.form.InsertBrowseHistoryRequest;
import com.reviewlah.controller.form.SelectBrowseHistoryByCustomerIdRequest;
import com.reviewlah.controller.form.SelectBrowseHistoryByHistoryIDRequest;
import com.reviewlah.controller.form.SelectBrowseHistoryByTimeHisRequest;
import com.reviewlah.controller.form.SelectTop3CategoryFromBrowseHistoryRequest;
import com.reviewlah.db.pojo.BrowseHistory;
import com.reviewlah.db.pojo.Category;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.remote.CategoryService;
import com.reviewlah.service.BrowseHistoryService;
import com.reviewlah.service.CustomerService;

@RestController
@RequestMapping({"/customer/browseHistory"})
public class BrowseHistoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BrowseHistoryService browseHistoryService;
    private final ObjectMapper mapper = new ObjectMapper();

    private Category selectCategoryByName(String category_name) {
        RCode rCode = categoryService.selectCategoryByName(category_name);
        if (rCode.get("category") == null) {
            return null;
        }
        return mapper.convertValue(rCode.get("category"), Category.class);
    }

    @PostMapping({"/historyDetail"})
    public RCode selectBrowseHistoryByHistoryID(@RequestBody SelectBrowseHistoryByHistoryIDRequest request){
        BigInteger history_id=request.getHistory_id();
        BrowseHistory browseHistory=this.browseHistoryService.selectBrowseHistoryByHistoryID(history_id);
        if (browseHistory==null){
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
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        ArrayList<Integer> list = new ArrayList<>();
        if(customer!=null){
            Date time_his=request.getTime();
            if(time_his == null) time_his = this.lastMonthDate();
            System.out.println(time_his);
            list=this.browseHistoryService.selectTop3CategoryFromBrowseHistory(customer.getCustomer_id(), time_his);
            System.out.println("successful");
        }
        else{
            System.out.println("Customer Does Not Exist");
            return RCode.error("Customer Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }

    @PostMapping({"/allHistoryForCustomer"})
    public RCode selectBrowseHistoryByCustomerId(@RequestBody SelectBrowseHistoryByCustomerIdRequest request){
        BigInteger user_id = request.getUser_id();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        ArrayList<BrowseHistory> list = new ArrayList<BrowseHistory>();
        if (customer != null) {
            list = this.browseHistoryService.selectBrowseHistoryByCustomerId(customer.getCustomer_id());
            System.out.println("successful");
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
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        String category_name=request.getCategory_name();
        Category category = selectCategoryByName(category_name);
        if (category == null) {
            System.out.println("Category is valid");
            return RCode.error("Category Does Not Exist");
        }
        int category_id = category.getCategory_id();
        if (customer != null){
            if(category_id!=0){
                Date time_his = new Date();
                BrowseHistory browseHistory=new BrowseHistory();
                browseHistory.setCategory_id(category_id);
                browseHistory.setTime_his(time_his);
                browseHistory.setCustomer_id(customer.getCustomer_id());
                this.browseHistoryService.insertBrowseHistory(browseHistory);
                System.out.println("successful");
            }
            else{
                System.out.println("Category is valid");
                return RCode.error("Category Does Not Exist");
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