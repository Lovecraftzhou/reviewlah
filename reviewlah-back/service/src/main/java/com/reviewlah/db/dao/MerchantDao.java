package com.reviewlah.db.dao;

import java.math.BigInteger;

public interface MerchantDao {
    void insertMerchant(BigInteger user_id);
    BigInteger selectMerchantIdByUserId(BigInteger user_id);

}