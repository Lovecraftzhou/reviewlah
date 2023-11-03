package com.reviewlah.controller.form;

import java.math.BigInteger;

public class InsertDishRequest {
    private BigInteger user_id;
    private String dish_name;
    private double price;
    private String pic_dish;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPic_dish() {
        return pic_dish;
    }

    public void setPic_dish(String pic_dish) {
        this.pic_dish = pic_dish;
    }

}
