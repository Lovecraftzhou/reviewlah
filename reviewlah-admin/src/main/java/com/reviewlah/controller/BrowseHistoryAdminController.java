package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteBrowseHistoryByHistoryIDRequest;
import com.reviewlah.controller.form.InsertBrowseHistoryRequest;
import com.reviewlah.controller.form.SelectBrowseHistoryByCustomerIdRequest;
import com.reviewlah.controller.form.SelectBrowseHistoryByHistoryIDRequest;
import com.reviewlah.remote.BrowseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/admin/customer/browseHistory"})
public class BrowseHistoryAdminController {
    @Autowired
    private BrowseHistoryService browseHistoryService;

    @GetMapping("/showAll")
    public RCode selectAllBrowseHistory() {
        return this.browseHistoryService.selectAllBrowseHistory();
    }
    @PostMapping({"/historyDetail"})
    public RCode selectBrowseHistoryByHistoryID(@RequestBody SelectBrowseHistoryByHistoryIDRequest request) {
        return this.browseHistoryService.selectBrowseHistoryByHistoryID(request);
    }
    @PostMapping({"/allHistoryForCustomer"})
    public RCode selectBrowseHistoryByCustomerId(@RequestBody SelectBrowseHistoryByCustomerIdRequest request) {
        return this.browseHistoryService.selectBrowseHistoryByCustomerId(request);
    }
    @PostMapping({"/insertHistory"})
    public RCode insertBrowseHistory(@RequestBody InsertBrowseHistoryRequest request) {
        return this.browseHistoryService.insertBrowseHistory(request);
    }
    @PostMapping({"/deleteHistory"})
    public RCode deleteBrowseHistoryByHistoryID(@RequestBody DeleteBrowseHistoryByHistoryIDRequest request) {
        return this.browseHistoryService.deleteBrowseHistoryByHistoryID(request);
    }
}
