package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Announcement;

import java.math.BigInteger;
import java.util.ArrayList;

public interface AnnouncementDao {
    void insertAnnouncement(Announcement announcement);
    void updateAnnouncement(Announcement announcement);
    void deleteAnnouncementByAnnouncementId(BigInteger announcement_id);
    ArrayList<Announcement> selectAnnouncementByMerchantId(BigInteger merchant_id);
    Announcement selectAnnouncementByAnnouncementId(BigInteger announcement_id);
}
