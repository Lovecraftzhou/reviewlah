package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import com.reviewlah.db.pojo.Dish;
import com.reviewlah.db.pojo.Menu;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.MenuService;
import com.reviewlah.service.MerchantService;
import com.reviewlah.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@Tag(name = "菜单模块")
@RestController
@RequestMapping({"/menu"})
public class MenuController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MenuService menuService;

    @Operation(summary = "添加菜单")
    @PostMapping({"/insert"})
    public RCode insertMenu(@RequestBody InsertMenuRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
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
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    }

    @Operation(summary = "删除菜单")
    @PostMapping({"/delete"})
    public RCode deleteMenuById(@RequestBody DeleteMenuByIdRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
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
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    }
}
