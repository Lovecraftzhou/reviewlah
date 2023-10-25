package com.reviewlah.service.impl;

import com.reviewlah.db.dao.AnnouncementDao;
import com.reviewlah.db.pojo.Announcement;
import com.reviewlah.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementDao announcementDao;

    public void insertAnnouncement(Announcement announcement) {
        this.announcementDao.insertAnnouncement(announcement);
    }

    public void updateAnnouncement(Announcement announcements) {
        this.announcementDao.updateAnnouncement(announcements);
    }


    public void deleteAnnouncementByAnnouncementId(BigInteger announcement_id) {
        this.announcementDao.deleteAnnouncementByAnnouncementId(announcement_id);

    }

    public ArrayList<Announcement> selectAnnouncementByMerchantId(BigInteger merchant_id) {
        return this.announcementDao.selectAnnouncementByMerchantId(merchant_id);

    }

    public Announcement selectAnnouncementByAnnouncementId(BigInteger announcement_id) {
        return this.announcementDao.selectAnnouncementByAnnouncementId(announcement_id);
    }
    public ArrayList<Announcement> selectAllAnnouncement() {
        return this.announcementDao.selectAllAnnouncement();
    }
}
