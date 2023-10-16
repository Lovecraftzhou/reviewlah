package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import com.reviewlah.db.pojo.Dish;
import com.reviewlah.db.pojo.Menu;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.DishService;
import com.reviewlah.service.MenuService;
import com.reviewlah.service.MerchantService;
import com.reviewlah.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;

@RestController
@RequestMapping({"/menu"})
public class DishController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DishService dishService;

    @PostMapping({"/dishes"})
    public RCode selectDishByMenuId(@RequestBody SelectDishByMenuIdRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        ArrayList<Dish> list = new ArrayList<>();
        if(user != null) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if(merchant_id != null) {
                Menu menu = this.menuService.selectMenuByMerchantId(merchant_id);
                if(menu != null) {
                    list = this.dishService.selectDishByMenuId(menu.getMenu_id());
                    System.out.println("successful");
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
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/select_dish"})
    public RCode selectDishIdByName(@RequestBody SelectDishIdByNameRequest request) {
        BigInteger user_id = request.getUser_id();
        String dish_name = request.getDish_name();
        User user = this.userService.selectUserById(user_id);
        ArrayList<Dish> list = new ArrayList<>();
        if(user != null) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if(merchant_id != null) {
                Menu menu = this.menuService.selectMenuByMerchantId(merchant_id);
                if(menu != null) {
                    list = this.dishService.selectDishByName(dish_name, menu.getMenu_id());
                    System.out.println("successful");
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
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/insert_dish"})
    public RCode insertDish(@RequestBody InsertDishRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if(merchant_id != null) {
                Menu menu = this.menuService.selectMenuByMerchantId(merchant_id);
                if(menu != null) {
                    String dish_name = request.getDish_name();
                    Double price = request.getPrice();
                    String pic_dish = request.getPic_dish();
                    BigInteger menu_id = menu.getMenu_id();
                    if(dish_name == null || dish_name.isEmpty()) {
                        System.out.println("Dish Name Cannot Be Empty");
                        return RCode.error("Dish Name Cannot Be Empty");
                    }
                    if(price <= 0) {
                        System.out.println("Dish Price Cannot Be Empty");
                        return RCode.error("Dish Price Cannot Be Empty");
                    }
//                    if(pic_dish == null || pic_dish.isEmpty()) {
//                        pic_dish = "";
//                    }
                    Dish dish = new Dish();
                    dish.setDish_name(dish_name);
                    dish.setPrice(price);
                    dish.setPic_dish(pic_dish);
                    dish.setMenu_id(menu_id);
                    this.dishService.insertDish(dish);
                    System.out.println("successful");
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
    @PostMapping({"/update_dish"})
    public RCode updateDish(@RequestBody UpdateDishRequest request) {
        BigInteger dish_id = request.getDish_id();
        Dish dish = this.dishService.selectDishById(dish_id);
        if(dish != null) {
            String dish_name = request.getDish_name();
            Double price = request.getPrice();
            if(price <= 0) price = dish.getPrice();
            String pic_dish = request.getPic_dish();
            BigInteger menu_id = request.getMenu_id();
            dish.setDish_name(dish_name);
            dish.setPrice(price);
            dish.setPic_dish(pic_dish);
            dish.setMenu_id(menu_id);
            this.dishService.updateDish(dish);
            System.out.println("successful");
        }
        else {
            System.out.println("Dish Does Not Exist");
            return RCode.error("Dish Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/delete_dish"})
    public RCode deleteDishById(@RequestBody DeleteDishByIdRequest request) {
        BigInteger dish_id = request.getDish_id();
        Dish dish = this.dishService.selectDishById(dish_id);
        if(dish != null) {
            this.dishService.deleteDishById(dish_id);
            System.out.println("successful");
        }
        else {
            System.out.println("Dish Does Not Exist");
            return RCode.error("Dish Does Not Exist");
        }
        return RCode.ok("successful");
    }

}
