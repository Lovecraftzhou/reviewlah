package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("merchant")
public interface AnnouncementService {
    @GetMapping({"/merchant/announcement/showAll"})
    RCode selectAllAnnouncement();
    @PostMapping({"/merchant/announcement/announcementDetail"})
    RCode selectAnnouncementByAnnouncementId(@RequestBody SelectAnnouncementByAnnouncementIDRequest request);
    @PostMapping({"/merchant/announcement/detail"})
    RCode selectAnnouncementByMerchantId(@RequestBody SelectAnnouncementByMerchantIDRequest request);
    @PostMapping({"/merchant/announcement/update"})
    RCode updateAnnouncement(@RequestBody UpdateAnnouncementRequest request);

    @PostMapping({"/merchant/announcement/insert_announcement"})
    RCode insertAnnouncement(@RequestBody InsertAnnouncementRequest request);
    @PostMapping({"/merchant/announcement/delete_announcement"})
    RCode deleteAnnouncement(@RequestBody DeleteAnnouncementRequest request);
}
