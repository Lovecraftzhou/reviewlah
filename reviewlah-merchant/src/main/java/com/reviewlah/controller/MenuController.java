package com.reviewlah.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeleteMenuByIdRequest;
import com.reviewlah.controller.form.InsertMenuRequest;
import com.reviewlah.db.pojo.Menu;
import com.reviewlah.service.MenuService;
import com.reviewlah.service.MerchantService;

@RestController
@RequestMapping({"/merchant/menu"})
public class MenuController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MenuService menuService;
    @PostMapping({"/insert"})
    public RCode insertMenu(@RequestBody InsertMenuRequest request) {
        BigInteger user_id = request.getUser_id();
        BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
        if(merchant_id != null) {
            Menu menu = new Menu();
            menu.setMerchant_id(merchant_id);
            this.menuService.insertMenu(menu);
            System.out.println("Successful");
        }
        else {
            System.out.println("Merchant Does Not Exist");
            return RCode.error("Merchant Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/delete"})
    public RCode deleteMenuById(@RequestBody DeleteMenuByIdRequest request) {
        BigInteger user_id = request.getUser_id();
        BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
        if(merchant_id != null) {
            Menu menu = this.menuService.selectMenuByMerchantId(merchant_id);
            if(menu != null) {
                this.menuService.deleteMenuById(menu.getMenu_id());
                System.out.println("Successful");
            }
            else {
                System.out.println("Menu Does Not Exist");
                return RCode.error("Menu Does Not Exist");
            }
        }
        else {
            System.out.println("Merchant Does Not Exist");
            return RCode.error("Merchant Does Not Exist");
        }
        return RCode.ok("successful");
    }
}
