package com.reviewlah.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
@Service
public interface MerchantService {
    void insertMerchant(BigInteger user_id);
    BigInteger selectMerchantIdByUserId(BigInteger user_id);
}
