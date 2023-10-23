package com.reviewlah.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reviewlah.db.dao.MerchantDao;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantDao merchantDao;

    @Override
    public void insertMerchant(Merchant merchant) {
        this.merchantDao.insertMerchant(merchant);
    }

    @Override
    public BigInteger selectMerchantIdByUserId(BigInteger user_id) {
        return this.merchantDao.selectMerchantIdByUserId(user_id);
    }

    @Override
    public Merchant selectMerchantById(BigInteger merchant_id) {
        return this.merchantDao.selectMerchantById(merchant_id);
    }

    @Override
    public ArrayList<Merchant> selectAllMerchant() {
        return this.merchantDao.selectAllMerchant();
    }

    @Override
    public void updateRateByMerchantId(@Param("merchant_id") BigInteger merchant_id,
            @Param("avg_rate") Double avg_rate) {
        this.merchantDao.updateRateByMerchantId(merchant_id, avg_rate);
    }

    @Override
    public ArrayList<BigInteger> selectRecMerchantByCategoryId(Integer category_id) {
        return this.merchantDao.selectRecMerchantByCategoryId(category_id);
    }

    @Override
    public void updateMerchant(Merchant merchant) {
        merchantDao.updateMerchant(merchant);
    }

    @Override
    public void deleteMerchantById(BigInteger merchant_id) {
        merchantDao.deleteMerchantById(merchant_id);
    }

    @Override
    public Merchant selectMerchantByName(String name) {
        return merchantDao.selectMerchantByName(name);
    }
}
