package com.reviewlah.db.pojo;

import java.math.BigInteger;

public class User {
    private BigInteger user_id;
    private String name;
    private String phone_number;
    private String email;
    private String password;
    private int type;
    private String avator;
    public User() {
    }
    public User(String name, String phone_number, String email, String password, int type, String avator) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.type = type;
        this.avator = avator;
    }
    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", avator='" + avator + '\'' +
                '}';
    }

}
