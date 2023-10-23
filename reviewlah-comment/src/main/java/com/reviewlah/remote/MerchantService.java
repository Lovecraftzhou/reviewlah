package com.reviewlah.remote;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reviewlah.common.util.RCode;

@FeignClient("merchant")
public interface MerchantService {

    @GetMapping("/merchant/merchants/{merchant_id}")
    RCode selectMerchantById(@PathVariable BigInteger merchant_id);

    @PostMapping("/merchant/avg-rate/{merchant_id}")
    RCode updateAvgRateByMerchantId(@PathVariable BigInteger merchant_id, @RequestParam double avg_rate);
}
