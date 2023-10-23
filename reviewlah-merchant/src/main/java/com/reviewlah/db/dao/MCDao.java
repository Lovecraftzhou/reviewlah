package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.MC;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public interface MCDao {
//    ArrayList<BigInteger> selectMerchantIdByCategoryId(int category_id);
//    ArrayList<Integer> selectCategoryIdByMerchantId(BigInteger merchant_id);
    void insertMC(MC mc);
    void updateMC(MC mc);
    void deleteMCByMerchantId(BigInteger merchant_id);
    ArrayList<String> selectMCByMerchantId(BigInteger merchant_id);
}
