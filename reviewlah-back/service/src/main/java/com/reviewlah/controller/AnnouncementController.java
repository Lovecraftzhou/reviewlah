package com.reviewlah.controller;


import com.reviewlah.controller.form.DeleteAnnouncementRequest;
import com.reviewlah.controller.form.InsertAnnouncementRequest;
import com.reviewlah.controller.form.SelectAnnouncementByAnnouncementIDRequest;
import com.reviewlah.controller.form.UpdateAnnouncementRequest;
import com.reviewlah.db.pojo.Announcement;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.AnnouncementService;
import com.reviewlah.service.MerchantService;
import com.reviewlah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;

@RestController
@RequestMapping({"/merchant/announcement"})
public class AnnouncementController {
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private AnnouncementService announcementService;


    @PostMapping({"/detail"})
    public Announcement selectAnnouncementByAnnouncementId(@RequestBody SelectAnnouncementByAnnouncementIDRequest request){
        BigInteger announcement_id = request.getAnnouncement_id();
        Announcement announcement=this.announcementService.selectAnnouncementByAnnouncementId(announcement_id);
        if(announcement == null) {
            System.out.println("Announcement Does Not Exist");
        }
        else {
            System.out.println("successful");
        }
        return announcement;
    }
    @PostMapping({"/update"})
    public void updateAnnouncement(@RequestBody UpdateAnnouncementRequest request) {
        BigInteger announcement_id = request.getAnnouncement_id();
        Announcement announcement = this.announcementService.selectAnnouncementByAnnouncementId(announcement_id);
        if (announcement != null) {
            String content = request.getContent();
            Date time_anc = new Date();
            if (content != null && !content.isEmpty()) {
                announcement.setAnnouncement_id(announcement_id);
                announcement.setContent(content);
                announcement.setTime_anc(time_anc);
                this.announcementService.updateAnnouncement(announcement);
                System.out.println("successful");
            } else {
                System.out.println("Content Cannot be Empty");
            }
        }
        else {
            System.out.println("Announcement Does Not Exist");
        }

    }
    @PostMapping({"/insert_announcement"})
    public void insertAnnouncement(@RequestBody InsertAnnouncementRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if (user != null && user.getType() == 2){
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if (merchant_id !=null){
                String content = request.getContent();
                if(content != null && content != "") {
                    Date time_anc = new Date();
                    Announcement announcement = new Announcement();
                    announcement.setContent(content);
                    announcement.setTime_anc(time_anc);
                    announcement.setMerchant_id(merchant_id);
                    this.announcementService.insertAnnouncement(announcement);
                    System.out.println("successful");
                }
                else {
                    System.out.println("Content Cannot Be Empty");
                }
            }
            else {
                System.out.println("Merchant Does Not Exist");
            }

        }
        else {
            System.out.println("User Does Not Exist");
        }
    }

    @PostMapping({"/delete_announcement"})
    public void deleteAnnouncement(@RequestBody DeleteAnnouncementRequest request){
        BigInteger announcement_id = request.getAnnouncement_id();
        Announcement announcement = this.announcementService.selectAnnouncementByAnnouncementId(announcement_id);
        if(announcement !=null){
            this.announcementService.deleteAnnouncementByAnnouncementId(announcement_id);
            System.out.println("successful");
        }
        else{
            System.out.println("Announcement Does Not Exist");
        }

    }
}
