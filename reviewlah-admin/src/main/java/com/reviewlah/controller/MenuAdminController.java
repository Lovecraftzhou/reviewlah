package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteMenuByIdRequest;
import com.reviewlah.controller.form.InsertMenuRequest;
import com.reviewlah.remote.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
@RestController
@RequestMapping({"/admin/merchant/menu"})
public class MenuAdminController {
    @Autowired
    private MenuService menuService;
    @GetMapping({"showAll"})
    public RCode selectAllMenu() {
        return this.menuService.selectAllMenu();
    }
    @GetMapping({"/{menu_id}"})
    public RCode selectMenuById(@PathVariable BigInteger menu_id) {
        return this.menuService.selectMenuById(menu_id);
    }
    @GetMapping("/merchantMenu/{merchant_id}")
    public RCode selectMenuByMerchantId(@PathVariable BigInteger merchant_id) {
        return this.menuService.selectMenuByMerchantId(merchant_id);
    }
    @PostMapping({"/insert"})
    public RCode insertMenu(@RequestBody InsertMenuRequest request) {
        return this.menuService.insertMenu(request);
    }
    @PostMapping({"/delete"})
    public RCode deleteMenuById(@RequestBody DeleteMenuByIdRequest request) {
        return this.menuService.deleteMenuById(request);
    }
}
