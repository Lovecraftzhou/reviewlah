package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteMenuByIdRequest;
import com.reviewlah.controller.form.InsertMenuRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;

@FeignClient("merchant")
public interface MenuService {
    @GetMapping({"/merchant/menu/showAll"})
    RCode selectAllMenu();
    @GetMapping({"/merchant/menu/{menu_id}"})
    RCode selectMenuById(@PathVariable BigInteger menu_id);
    @GetMapping("/merchant/menu/merchantMenu/{merchant_id}")
    RCode selectMenuByMerchantId(@PathVariable BigInteger merchant_id);
    @PostMapping({"/merchant/menu/insert"})
    RCode insertMenu(@RequestBody InsertMenuRequest request);
    @PostMapping({"/merchant/menu/delete"})
    RCode deleteMenuById(@RequestBody DeleteMenuByIdRequest request);
}
