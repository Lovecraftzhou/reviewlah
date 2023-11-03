package com.reviewlah.db.pojo;

import java.math.BigInteger;
import java.util.Date;

public class DiningComment {
    private BigInteger dining_com_id;
    private BigInteger merchant_id;
    private BigInteger customer_id;
    private String content;
    private int rate;
    private Date time_dc;
    private String pic_dc;
    public DiningComment() {

    }

    public BigInteger getDining_com_id() {
        return dining_com_id;
    }

    public void setDining_com_id(BigInteger dining_com_id) {
        this.dining_com_id = dining_com_id;
    }

    public BigInteger getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(BigInteger merchant_id) {
        this.merchant_id = merchant_id;
    }

    public BigInteger getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(BigInteger customer_id) {
        this.customer_id = customer_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getTime_dc() {
        return time_dc;
    }

    public void setTime_dc(Date time_dc) {
        this.time_dc = time_dc;
    }

    public String getPic_dc() {
        return pic_dc;
    }

    public void setPic_dc(String pic_dc) {
        this.pic_dc = pic_dc;
    }

}
