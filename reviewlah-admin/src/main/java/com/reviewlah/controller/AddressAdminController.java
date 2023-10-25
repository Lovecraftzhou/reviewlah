package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteAddressRequest;
import com.reviewlah.controller.form.InsertAddressRequest;
import com.reviewlah.controller.form.UpdateAddressRequest;
import com.reviewlah.remote.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping({"/admin/merchant/address"})
public class AddressAdminController {
    @Autowired
    private AddressService addressService;
    @GetMapping({"/showAll"})
    public RCode selectAllAddress() {
        return this.addressService.selectAllAddress();
    }
    @PostMapping({"/{user_id}"})
    public RCode selectAddressByMerchantId(@PathVariable BigInteger user_id) {
        return this.addressService.selectAddressByMerchantId(user_id);
    }
    @PostMapping({"/update"})
    public RCode updateAddress(@RequestBody UpdateAddressRequest request) {
        return this.addressService.updateAddress(request);
    }
    @PostMapping({"/insert"})
    public RCode insertAddress(@RequestBody InsertAddressRequest request) {
        return this.addressService.insertAddress(request);
    }
    @PostMapping({"/delete"})
    public RCode deleteAddress(@RequestBody DeleteAddressRequest request) {
        return this.addressService.deleteAddress(request);
    }
}
