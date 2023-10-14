package com.reviewlah.service.impl;

import com.reviewlah.db.dao.MerchantDao;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantDao merchantDao;
    public void insertMerchant(BigInteger user_id) {
        this.merchantDao.insertMerchant(user_id);
    }
    public BigInteger selectMerchantIdByUserId(BigInteger user_id) {
        return this.merchantDao.selectMerchantIdByUserId(user_id);
    }
    public Merchant selectMerchantByUserId(BigInteger user_id) {
        return this.merchantDao.selectMerchantByUserId(user_id);
    }
}
