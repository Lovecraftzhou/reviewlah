package com.reviewlah.controller;


import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
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
import java.util.ArrayList;
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


    @PostMapping({"/announcementDetail"})
    public RCode selectAnnouncementByAnnouncementId(@RequestBody SelectAnnouncementByAnnouncementIDRequest request){
        BigInteger announcement_id = request.getAnnouncement_id();
        Announcement announcement=this.announcementService.selectAnnouncementByAnnouncementId(announcement_id);
        if(announcement == null) {
            System.out.println("Announcement Does Not Exist");
            return RCode.error("Announcement Does Not Exist");
        }
        else {
            System.out.println("successful");
        }
        return RCode.ok().put("list", announcement);
    }
    @PostMapping({"/detail"})
    public RCode selectAnnouncementByMerchantId(@RequestBody SelectAnnouncementByMerchantIDRequest request){
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        ArrayList<Announcement> list = new ArrayList<Announcement>();
        if (user != null && user.getType() == 2) {
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if (merchant_id != null) {
                list = this.announcementService.selectAnnouncementByMerchantId(merchant_id);
                System.out.println("successful");
            }
            else{
                System.out.println("Merchant Does Not Exist");
                return RCode.error("Merchant Does Not Exist");
            }
        }
        else{
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/update"})
    public RCode updateAnnouncement(@RequestBody UpdateAnnouncementRequest request) {
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
                return RCode.error("Content Does Not Exist");
            }
        }
        else {
            System.out.println("Announcement Does Not Exist");
            return RCode.error("Announcement Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/insert_announcement"})
    public RCode insertAnnouncement(@RequestBody InsertAnnouncementRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if (user != null && user.getType() == 2){
            BigInteger merchant_id = this.merchantService.selectMerchantIdByUserId(user_id);
            if (merchant_id !=null){
                String content = request.getContent();
                if(content != null && !content.equals("")) {
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
                    return RCode.error("Content Does Not Exist");
                }
            }
            else {
                System.out.println("Merchant Does Not Exist");
                return RCode.error("Merchant Does Not Exist");
            }

        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    }

    @PostMapping({"/delete_announcement"})
    public RCode deleteAnnouncement(@RequestBody DeleteAnnouncementRequest request){
        BigInteger announcement_id = request.getAnnouncement_id();
        Announcement announcement = this.announcementService.selectAnnouncementByAnnouncementId(announcement_id);
        if(announcement !=null){
            this.announcementService.deleteAnnouncementByAnnouncementId(announcement_id);
            System.out.println("successful");
        }
        else{
            System.out.println("Announcement Does Not Exist");
            return RCode.error("Announcement Does Not Exist");
        }
        return RCode.ok("successful");
    }
}
