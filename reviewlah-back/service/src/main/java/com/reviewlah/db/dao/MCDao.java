package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.MC;

import java.math.BigInteger;
import java.util.ArrayList;

public interface MCDao {
//    ArrayList<BigInteger> selectMerchantIdByCategoryId(int category_id);
//    ArrayList<Integer> selectCategoryIdByMerchantId(BigInteger merchant_id);
    void insertMC(MC mc);
    void updateMC(MC mc);
}
