package com.reviewlah.service;

import com.reviewlah.db.pojo.Merchant;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

public interface MerchantService {
    void insertMerchant(BigInteger user_id);
    BigInteger selectMerchantIdByUserId(BigInteger user_id);
    Merchant selectMerchantByUserId(BigInteger user_id);
}
