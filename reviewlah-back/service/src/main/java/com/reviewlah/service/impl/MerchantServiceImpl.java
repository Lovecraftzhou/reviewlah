package com.reviewlah.service.impl;

import com.reviewlah.db.dao.MerchantDao;
import com.reviewlah.service.MerchantService;

import java.math.BigInteger;

public class MerchantServiceImpl implements MerchantService {
    private MerchantDao merchantDao;
    public void insertMerchant(BigInteger user_id) {
        this.merchantDao.insertMerchant(user_id);
    }
}
