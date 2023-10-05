package com.reviewlah.db.dao;
import com.reviewlah.db.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface UserDao {
    void insertUser(User user);
    void updateUser(User user);
    void deleteUserById(BigInteger user_id);
}
