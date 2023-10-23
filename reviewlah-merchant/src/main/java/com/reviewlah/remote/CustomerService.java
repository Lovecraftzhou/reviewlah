package com.reviewlah.remote;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reviewlah.common.util.RCode;

@FeignClient("customer")
public interface CustomerService {

    @GetMapping("/customer/customers/{customer_id}")
    RCode selectCustomerById(@PathVariable BigInteger customer_id);

    @PostMapping({"/customer/browseHistory/customerHistory"})
    RCode selectTop3CategoryFromBrowseHistory(@RequestBody SelectTop3CategoryFromBrowseHistoryRequest request);
}
