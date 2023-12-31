package com.reviewlah.db.dao;
import com.reviewlah.db.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;

//@Mapper
public interface UserDao {
    void insertUser(User user);
    void updateUser(User user);
    void deleteUserById(BigInteger user_id);
    User selectUserByName(String name);
    User selectUserById(BigInteger user_id);
    ArrayList<User> selectUserByType(int type);
}
