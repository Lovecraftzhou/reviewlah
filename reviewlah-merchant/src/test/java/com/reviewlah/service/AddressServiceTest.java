package com.reviewlah.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Address;
import com.reviewlah.db.pojo.Merchant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class AddressServiceTest {
    @Autowired
    private AddressService service;
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
        Address address = new Address();
        address.setAddress_code("123456");
        address.setAddress_detail("Address 1");
        address.setUnitnum("2");
        address.setMerchant_id(merchant.getMerchant_id());
        service.insertAddress(address);
        address = service.selectAddressByMerchantId(merchant.getMerchant_id());
        assertNotNull(address);
        address.setAddress_detail("Updated Detail");
        service.updateAddress(address);
        address = service.selectAddressByMerchantId(merchant.getMerchant_id());
        assertEquals("Updated Detail", address.getAddress_detail());
    }
}
