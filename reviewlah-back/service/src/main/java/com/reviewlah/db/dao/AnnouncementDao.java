package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Announcement;

import java.math.BigInteger;
import java.util.ArrayList;

public interface AnnouncementDao {
    void insertAnnouncements (String content);
    void updateAnnouncements(Announcement Announcement);
    void deleteAnnouncementsByAnnouncementsId(BigInteger announcement_id);
    ArrayList<Announcement> selectAnnouncementByMerchantId(BigInteger merchant_id);
}
