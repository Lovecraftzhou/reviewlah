package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteMerchantrRequest;
import com.reviewlah.controller.form.InsertMerchantRequest;
import com.reviewlah.controller.form.UpdateMerchantRequest;
import com.reviewlah.remote.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping({"/admin/merchant"})
public class MerchantAdminController {
    @Autowired
    private MerchantService merchantService;
    @GetMapping("/merchantList")
    public RCode selectAllMerchant() {
        return this.merchantService.selectAllMerchant();
    }
    @GetMapping("/merchants/{user_id}")
    public RCode selectMerchantById(@PathVariable BigInteger user_id) {
        return this.merchantService.selectMerchantById(user_id);
    }
    @PostMapping({"/insert"})
    public RCode insertUser(@RequestBody InsertMerchantRequest request) {
        return this.merchantService.insertUser(request);
    }
    @PostMapping({"/personalInfo/update"})
    public RCode updateUser(@RequestBody UpdateMerchantRequest request) {
        return this.merchantService.updateUser(request);
    }
    @PostMapping({"/delete"})
    public RCode deleteUserById(@RequestBody DeleteMerchantrRequest request) {
        return this.merchantService.deleteUserById(request);
    }
}
