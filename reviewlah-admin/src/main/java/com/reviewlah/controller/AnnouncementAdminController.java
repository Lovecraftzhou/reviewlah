package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import com.reviewlah.remote.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/admin/merchant/announcement"})
public class AnnouncementAdminController {
    @Autowired
    private AnnouncementService announcementService;
    @GetMapping({"/showAll"})
    public RCode selectAllAnnouncement() {
        return this.announcementService.selectAllAnnouncement();
    }
    @PostMapping({"/announcementDetail"})
    public RCode selectAnnouncementByAnnouncementId(@RequestBody SelectAnnouncementByAnnouncementIDRequest request) {
        return this.announcementService.selectAnnouncementByAnnouncementId(request);
    }
    @PostMapping({"/detail"})
    public RCode selectAnnouncementByMerchantId(@RequestBody SelectAnnouncementByMerchantIDRequest request) {
        return this.announcementService.selectAnnouncementByMerchantId(request);
    }
    @PostMapping({"/update"})
    public RCode updateAnnouncement(@RequestBody UpdateAnnouncementRequest request) {
        return this.announcementService.updateAnnouncement(request);
    }

    @PostMapping({"/insert_announcement"})
    public RCode insertAnnouncement(@RequestBody InsertAnnouncementRequest request) {
        return this.announcementService.insertAnnouncement(request);
    }
    @PostMapping({"/delete_announcement"})
    public RCode deleteAnnouncement(@RequestBody DeleteAnnouncementRequest request) {
        return this.announcementService.deleteAnnouncement(request);
    }
}
