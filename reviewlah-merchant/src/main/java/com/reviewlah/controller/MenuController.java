package com.reviewlah.controller;

import java.math.BigInteger;
import java.util.ArrayList;

import com.reviewlah.db.pojo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping({"showAll"})
    public RCode selectAllMenu() {
        ArrayList<Menu> list = this.menuService.selectAllMenu();
        return RCode.ok().put("list", list);
    }
    @GetMapping({"/{menu_id}"})
    public RCode selectMenuById(@PathVariable BigInteger menu_id) {
        Menu menu = this.menuService.selectMenuById(menu_id);
        if(menu != null) {
            return RCode.ok().put("list", menu);
        }
        else {
            System.out.println("Menu Does Not Exist");
            return RCode.error("Menu Does Not Exist");
        }
    }
    @GetMapping("/merchantMenu/{merchant_id}")
    public RCode selectMenuByMerchantId(@PathVariable BigInteger merchant_id) {
        Merchant merchant = this.merchantService.selectMerchantById(merchant_id);
        if(merchant == null) {
            System.out.println("Merchant Does Not Exist");
            return RCode.error("Merchant Does Not Exist");
        }
        Menu menu = this.menuService.selectMenuByMerchantId(merchant_id);
        if(menu != null) {
            return RCode.ok().put("list", menu);
        }
        else {
            System.out.println("Menu Does Not Exist");
            return RCode.error("Menu Does Not Exist");
        }
    }
    @PostMapping({"/insert"})
    public RCode insertMenu(@RequestBody InsertMenuRequest request) {
        BigInteger user_id = request.getUser_id();
        Merchant merchant = this.merchantService.selectMerchantById(user_id);
        if(merchant != null) {
            Menu tmp = this.menuService.selectMenuByMerchantId(user_id);
            if(tmp != null) {
                System.out.println("Menu Does Already Exist");
                return RCode.error("Menu Does Already Exist");
            }
            Menu menu = new Menu();
            menu.setMerchant_id(user_id);
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
