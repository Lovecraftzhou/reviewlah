package com.reviewlah.service.impl;

import com.reviewlah.db.dao.MerchantDao;
import com.reviewlah.service.MerchantService;

import java.math.BigInteger;

public class MerchantServiceImpl implements MerchantService {
    private MerchantDao merchantDao;
    public void insertMerchant(BigInteger user_id) {
        this.merchantDao.insertMerchant(user_id);
    }
    public BigInteger selectMerchantIdByUserId(BigInteger user_id) {
        BigInteger merchant_id = this.merchantDao.selectMerchantIdByUserId(user_id);
        return merchant_id;
    }
}
