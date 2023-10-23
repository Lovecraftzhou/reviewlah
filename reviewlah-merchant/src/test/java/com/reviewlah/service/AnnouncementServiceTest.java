package com.reviewlah.service;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Announcement;
import com.reviewlah.db.pojo.Merchant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class AnnouncementServiceTest {
    @Autowired
    private AnnouncementService service;
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
        Announcement announcement = new Announcement();
        announcement.setContent("Test Announcement");
        announcement.setMerchant_id(merchant.getMerchant_id());
        announcement.setTime_anc(new Date());
        service.insertAnnouncement(announcement);
        announcement = service.selectAnnouncementByAnnouncementId(
                announcement.getAnnouncement_id());
        assertNotNull(announcement);
        ArrayList<Announcement> announcements = service.selectAnnouncementByMerchantId(
                merchant.getMerchant_id());
        assertFalse(announcements.isEmpty());
        announcement.setContent("Updated Announcement");
        service.updateAnnouncement(announcement);
        announcement = service.selectAnnouncementByAnnouncementId(
                announcement.getAnnouncement_id());
        assertEquals("Updated Announcement", announcement.getContent());
        service.deleteAnnouncementByAnnouncementId(announcement.getAnnouncement_id());
    }
}