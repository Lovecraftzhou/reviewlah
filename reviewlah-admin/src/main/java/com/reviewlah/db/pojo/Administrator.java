package com.reviewlah.db.pojo;

import java.math.BigInteger;

public class Administrator {
    private BigInteger admin_id;
    private String name;
    private String phone_number;
    private String email;
    private String password;
    private String avator;

    public BigInteger getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(BigInteger admin_id) {
        this.admin_id = admin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }
}
