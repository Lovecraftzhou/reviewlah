package com.reviewlah.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Category;
import com.reviewlah.db.pojo.MC;
import com.reviewlah.db.pojo.Merchant;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Transactional
@SpringBootTest
class MCServiceTest {
    @Autowired
    private MCService service;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private CategoryService categoryService;

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
        categoryService.insertCategory("Test Category");
        Category category = categoryService.selectCategoryByName("Test Category");
        MC mc = new MC();
        mc.setCategory_id(category.getCategory_id());
        mc.setMerchant_id(merchant.getMerchant_id());
        service.insertMC(mc);
        service.updateMC(mc);
        ArrayList<String> mcs = service.selectMCByMerchantId(merchant.getMerchant_id());
        assertFalse(mcs.isEmpty());
        service.deleteMCByMerchantId(merchant.getMerchant_id());
    }
}