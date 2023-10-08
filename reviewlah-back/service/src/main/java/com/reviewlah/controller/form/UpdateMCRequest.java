package com.reviewlah.controller.form;

import java.math.BigInteger;
import java.util.ArrayList;

public class UpdateMCRequest {
    private BigInteger user_id;
    private ArrayList<String> category;

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }




}
