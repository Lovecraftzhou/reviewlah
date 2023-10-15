package com.reviewlah.service.impl;

import com.reviewlah.db.dao.UserDao;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
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
    public User selectUserByName(String name) {
        return this.userDao.selectUserByName(name);
    }
    public User selectUserById(BigInteger user_id) {
        return this.userDao.selectUserById(user_id);
    }
    public ArrayList<User> selectUserByType(int type) {
        return this.userDao.selectUserByType(type);
    }
}
