package com.reviewlah.controller.form;

import java.math.BigInteger;

public class SelectDishIdByNameRequest {

    private BigInteger user_id;
    private String dish_name;
    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }
}
