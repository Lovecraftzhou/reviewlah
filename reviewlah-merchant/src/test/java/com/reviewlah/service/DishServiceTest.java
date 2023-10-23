package com.reviewlah.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Dish;
import com.reviewlah.db.pojo.Menu;
import com.reviewlah.db.pojo.Merchant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class DishServiceTest {
    @Autowired
    private DishService service;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MenuService menuService;

    @Test
    void test() {
        Merchant merchant = new Merchant();
        merchant.setAvg_rate(0);
        merchant.setPhone_number("12345678");
        merchant.setPassword("merchant");
        merchant.setName("Test Merchant");
        merchant.setEmail("merchat@test.com");
        merchant.setAvator("avator");
        merchantService.insertMerchant(merchant);
        Menu menu = new Menu();
        menu.setMerchant_id(merchant.getMerchant_id());
        menuService.insertMenu(menu);
        Dish dish = new Dish();
        dish.setDish_name("Test Dish");
        dish.setPic_dish("https://www.google.com");
        dish.setPrice(11.11);
        dish.setMenu_id(menu.getMenu_id());
        service.insertDish(dish);
        dish = service.selectDishById(dish.getDish_id());
        assertNotNull(dish);
        ArrayList<Dish> dishes = service.selectAllDish();
        assertFalse(dishes.isEmpty());
        dishes = service.selectDishByName("Test Dish", menu.getMenu_id());
        assertFalse(dishes.isEmpty());
        dishes = service.selectDishByMenuId(menu.getMenu_id());
        assertFalse(dishes.isEmpty());
        ArrayList<HashMap> maps = service.selectDishByMerchantId(merchant.getMerchant_id());
        assertFalse(maps.isEmpty());
        dish.setDish_name("Updated Dish");
        service.updateDish(dish);
        dish = service.selectDishById(dish.getDish_id());
        assertEquals("Updated Dish", dish.getDish_name());
        service.deleteDishById(dish.getDish_id());
    }
}