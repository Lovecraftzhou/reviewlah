package com.reviewlah.service;

import com.reviewlah.db.pojo.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
@Service
public interface UserService {
    void insertUser(User user);
    void updateUser(User user);
    void deleteUserById(BigInteger user_id);
    User selectUserByName(String name);
    User selectUserById(BigInteger user_id);
}
