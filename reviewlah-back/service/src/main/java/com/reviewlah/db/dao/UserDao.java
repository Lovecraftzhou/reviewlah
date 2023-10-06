package com.reviewlah.db.dao;
import com.reviewlah.db.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface UserDao {
    void insertUser(User user);
    void updateUser(User user);
    void deleteUserById(BigInteger user_id);
    BigInteger selectUserIdByName(String name);
}
