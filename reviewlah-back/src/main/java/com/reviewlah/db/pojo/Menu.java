package com.reviewlah.db.pojo;

import java.math.BigInteger;

public class Menu {
    private BigInteger menu_id;
    private BigInteger merchant_id;
    public Menu() {

    }

    public BigInteger getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(BigInteger menu_id) {
        this.menu_id = menu_id;
    }

    public BigInteger getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(BigInteger merchant_id) {
        this.merchant_id = merchant_id;
    }

}
