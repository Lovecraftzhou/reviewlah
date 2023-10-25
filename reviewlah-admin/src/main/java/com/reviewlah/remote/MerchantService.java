package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteMerchantrRequest;
import com.reviewlah.controller.form.InsertMerchantRequest;
import com.reviewlah.controller.form.UpdateMerchantRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;

@FeignClient("merchant")
public interface MerchantService {
    @GetMapping("/merchant/merchantList")
    RCode selectAllMerchant();
    @GetMapping("/merchant/merchants/{merchant_id}")
    RCode selectMerchantById(@PathVariable BigInteger merchant_id);
    @PostMapping({"/merchant/insert"})
    RCode insertUser(@RequestBody InsertMerchantRequest request);
    @PostMapping({"/merchant/personalInfo/update"})
    RCode updateUser(@RequestBody UpdateMerchantRequest request);
    @PostMapping({"/merchant/delete"})
    RCode deleteUserById(@RequestBody DeleteMerchantrRequest request);
}
