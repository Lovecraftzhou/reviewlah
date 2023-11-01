package com.reviewlah.remote;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reviewlah.common.util.RCode;

@FeignClient("comment")
public interface DiningCommentService {

    @GetMapping("/comment/merchants/{merchant_id}")
    RCode selectDCByMerchantId(@PathVariable BigInteger merchant_id);

//    Double getAverageRateByMerchantId(BigInteger merchant_id);
}
