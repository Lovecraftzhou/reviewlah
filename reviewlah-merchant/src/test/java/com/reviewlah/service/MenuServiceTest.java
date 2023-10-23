package com.reviewlah.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Menu;
import com.reviewlah.db.pojo.Merchant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class MenuServiceTest {
    @Autowired
    private MenuService service;
    @Autowired
    private MerchantService merchantService;

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
        service.insertMenu(menu);
        menu = service.selectMenuByMerchantId(merchant.getMerchant_id());
        assertNotNull(menu);
        ArrayList<Menu> menus = service.selectAllMenu();
        assertFalse(menus.isEmpty());
        service.deleteMenuById(menu.getMenu_id());
    }
}