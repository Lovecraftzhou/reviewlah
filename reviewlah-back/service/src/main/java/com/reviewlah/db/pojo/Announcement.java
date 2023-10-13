package com.reviewlah.db.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

public class Announcement {
    private BigInteger announcement_id;
    private BigInteger merchant_id;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time_anc;
    public Announcement() {

    }
    public BigInteger getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(BigInteger announcement_id) {
        this.announcement_id = announcement_id;
    }

    public BigInteger getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(BigInteger merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime_anc() {
        return time_anc;
    }

    public void setTime_anc(Date time_anc) {
        this.time_anc = time_anc;
    }

}
