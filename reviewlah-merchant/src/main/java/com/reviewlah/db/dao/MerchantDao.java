package com.reviewlah.db.dao;


import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.reviewlah.db.pojo.Merchant;

public interface MerchantDao {
    void insertMerchant(Merchant merchant);

    BigInteger selectMerchantIdByUserId(BigInteger user_id);

    Merchant selectMerchantById(BigInteger merchant_id);

    ArrayList<Merchant> selectAllMerchant();

    void updateRateByMerchantId(@Param("merchant_id") BigInteger merchant_id, @Param("avg_rate") Double avg_rate);

    ArrayList<BigInteger> selectRecMerchantByCategoryId(Integer category_id);

    //    ArrayList<HashMap> selectAllMerchant();
    void updateMerchant(Merchant user);

    void deleteMerchantById(BigInteger merchant_id);

    Merchant selectMerchantByName(String name);
}