package com.reviewlah.service;

import java.math.BigInteger;

public interface MerchantService {
    void insertMerchant(BigInteger user_id);
    BigInteger selectMerchantIdByUserId(BigInteger user_id);
}
