package com.reviewlah.db.pojo;

import java.math.BigInteger;

public class Dish {
    private BigInteger dish_id;
    private String dish_name;
    private double price;
    private String pic_dish;
    private BigInteger menu_id;

    public Dish() {

    }
    public BigInteger getDish_id() {
        return dish_id;
    }

    public void setDish_id(BigInteger dish_id) {
        this.dish_id = dish_id;
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

    public BigInteger getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(BigInteger menu_id) {
        this.menu_id = menu_id;
    }

}
