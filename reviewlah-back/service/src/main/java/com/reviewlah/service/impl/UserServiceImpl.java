package com.reviewlah.service.impl;

import com.reviewlah.db.dao.UserDao;
import com.reviewlah.db.vo.User;
import com.reviewlah.service.UserService;

import java.math.BigInteger;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void insertUser(User user) {
        this.userDao.insertUser(user);
    };
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    };
    public void deleteUserById(BigInteger user_id) {
        this.userDao.deleteUserById(user_id);
    };
}
