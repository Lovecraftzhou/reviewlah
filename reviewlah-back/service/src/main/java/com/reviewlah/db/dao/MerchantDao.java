package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Merchant;

import java.math.BigInteger;

public interface MerchantDao {
    void insertMerchant(BigInteger user_id);
    BigInteger selectMerchantIdByUserId(BigInteger user_id);
    Merchant selectMerchantByUserId(BigInteger user_id);
}
