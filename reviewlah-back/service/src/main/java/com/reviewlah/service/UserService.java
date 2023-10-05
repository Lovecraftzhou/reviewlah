package com.reviewlah.service;

import com.reviewlah.db.vo.User;

import java.math.BigInteger;

public interface UserService {
    void insertUser(User user);
    void updateUser(User user);
    void deleteUserById(BigInteger user_id);
}
