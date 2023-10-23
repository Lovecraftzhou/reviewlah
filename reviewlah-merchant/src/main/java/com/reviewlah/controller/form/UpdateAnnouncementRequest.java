package com.reviewlah.controller.form;

import javax.xml.crypto.Data;
import java.math.BigInteger;

public class UpdateAnnouncementRequest {
    private BigInteger announcement_id;
    private String content;

    public BigInteger getAnnouncement_id(){return announcement_id;}
    public void setAnnouncement_id(){this.announcement_id = announcement_id;}

    public String getContent(){ return content;}

    public void setContent(){this.content = content;}

}
