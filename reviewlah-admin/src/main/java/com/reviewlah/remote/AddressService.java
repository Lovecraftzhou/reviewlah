package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteAddressRequest;
import com.reviewlah.controller.form.InsertAddressRequest;
import com.reviewlah.controller.form.UpdateAddressRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;

@FeignClient("merchant")
public interface AddressService {
    @GetMapping({"/merchant/address/showAll"})
    RCode selectAllAddress();
    @PostMapping({"/merchant/address/{merchant_id}"})
    RCode selectAddressByMerchantId(@PathVariable BigInteger merchant_id);
    @PostMapping({"/merchant/address/update"})
    RCode updateAddress(@RequestBody UpdateAddressRequest request);
    @PostMapping({"/merchant/address/insert"})
    RCode insertAddress(@RequestBody InsertAddressRequest request);
    @PostMapping({"/merchant/address/delete"})
    public RCode deleteAddress(@RequestBody DeleteAddressRequest request);
}
