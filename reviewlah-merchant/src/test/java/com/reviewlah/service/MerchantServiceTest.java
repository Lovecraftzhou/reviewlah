package com.reviewlah.service;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Merchant;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MerchantServiceTest {
    @Autowired
    private MerchantService service;

    @Test
    void test() {
        Merchant merchant = new Merchant();
        merchant.setAvg_rate(0);
        merchant.setPhone_number("12345678");
        merchant.setPassword("merchant");
        merchant.setName("Test Merchant");
        merchant.setEmail("merchat@test.com");
        merchant.setAvator("avator");
        service.insertMerchant(merchant);
        merchant = service.selectMerchantById(merchant.getMerchant_id());
        assertNotNull(merchant);
        merchant = service.selectMerchantByName("Test Merchant");
        assertNotNull(merchant);
        BigInteger merchantId = service.selectMerchantIdByUserId(merchant.getMerchant_id());
        assertNotNull(merchantId);
        ArrayList<Merchant> merchants = service.selectAllMerchant();
        assertFalse(merchants.isEmpty());
        service.updateRateByMerchantId(merchantId, 4.5);
        merchant = service.selectMerchantById(merchant.getMerchant_id());
        assertEquals(4.5, merchant.getAvg_rate());
        merchant.setEmail("newmerchant@test.com");
        service.updateMerchant(merchant);
        merchant = service.selectMerchantById(merchant.getMerchant_id());
        assertEquals("newmerchant@test.com", merchant.getEmail());
        service.deleteMerchantById(merchant.getMerchant_id());

    }
}