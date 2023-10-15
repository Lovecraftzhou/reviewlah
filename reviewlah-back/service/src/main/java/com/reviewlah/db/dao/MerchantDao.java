package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Merchant;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public interface MerchantDao {
    void insertMerchant(BigInteger user_id);
    BigInteger selectMerchantIdByUserId(BigInteger user_id);
    Merchant selectMerchantByUserId(BigInteger user_id);
    ArrayList<Merchant> selectAllMerchant();
    void updateRateByMerchantId(@Param("merchant_id") BigInteger merchant_id, @Param("avg_rate") Double avg_rate);
//    ArrayList<HashMap> selectAllMerchant();
}
