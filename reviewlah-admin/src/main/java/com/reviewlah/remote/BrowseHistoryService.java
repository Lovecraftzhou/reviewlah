package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteBrowseHistoryByHistoryIDRequest;
import com.reviewlah.controller.form.InsertBrowseHistoryRequest;
import com.reviewlah.controller.form.SelectBrowseHistoryByCustomerIdRequest;
import com.reviewlah.controller.form.SelectBrowseHistoryByHistoryIDRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient("customer")
public interface BrowseHistoryService {
    @GetMapping("/customer/browseHistory/showAll")
    public RCode selectAllBrowseHistory();
    @PostMapping({"/customer/browseHistory/historyDetail"})
    RCode selectBrowseHistoryByHistoryID(@RequestBody SelectBrowseHistoryByHistoryIDRequest request);
    @PostMapping({"/customer/browseHistory/allHistoryForCustomer"})
    RCode selectBrowseHistoryByCustomerId(@RequestBody SelectBrowseHistoryByCustomerIdRequest request);
    @PostMapping({"/customer/browseHistory/insertHistory"})
    RCode insertBrowseHistory(@RequestBody InsertBrowseHistoryRequest request);
    @PostMapping({"/customer/browseHistory/deleteHistory"})
    RCode deleteBrowseHistoryByHistoryID(@RequestBody DeleteBrowseHistoryByHistoryIDRequest request);
}
