package com.reviewlah.service.impl;

import com.reviewlah.db.dao.MerchantDao;
import com.reviewlah.db.pojo.Merchant;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.MerchantService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantDao merchantDao;
    public void insertMerchant(Merchant merchant) {
        this.merchantDao.insertMerchant(merchant);
    }
    public BigInteger selectMerchantIdByUserId(BigInteger user_id) {
        return this.merchantDao.selectMerchantIdByUserId(user_id);
    }
    public Merchant selectMerchantByUserId(BigInteger user_id) {
        return this.merchantDao.selectMerchantByUserId(user_id);
    }
    public Merchant selectMerchantById(BigInteger merchant_id) {
        return this.merchantDao.selectMerchantById(merchant_id);
    }

    public ArrayList<Merchant> selectAllMerchant() {
        return this.merchantDao.selectAllMerchant();
    }
    public void updateRateByMerchantId(@Param("merchant_id") BigInteger merchant_id, @Param("avg_rate") Double avg_rate) {
        this.merchantDao.updateRateByMerchantId(merchant_id, avg_rate);
    }
    public ArrayList<BigInteger> selectRecMerchantByCategoryId(Integer category_id) {
        return this.merchantDao.selectRecMerchantByCategoryId(category_id);
    }
    public ArrayList<BigInteger> selectMerchantByName(String keyword) {
        return this.merchantDao.selectMerchantByName(keyword);
    }
//    public ArrayList<HashMap> selectAllMerchant() {
//        return this.merchantDao.selectAllMerchant();
//    }
}
