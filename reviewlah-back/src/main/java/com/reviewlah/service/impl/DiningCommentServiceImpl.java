package com.reviewlah.service.impl;

import com.reviewlah.db.dao.DiningCommentDao;
import com.reviewlah.db.pojo.DiningComment;
import com.reviewlah.service.DiningCommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DiningCommentServiceImpl implements DiningCommentService {
    @Autowired
    private DiningCommentDao diningCommentDao;
    public ArrayList<DiningComment> selectAllDC() {
        return this.diningCommentDao.selectAllDC();
    }
    public DiningComment selectDCById(BigInteger dining_com_id) {
        return this.diningCommentDao.selectDCById(dining_com_id);
    }
    public ArrayList<HashMap> selectDCMapByMerchantId(BigInteger merchant_id) {
        return this.diningCommentDao.selectDCMapByMerchantId(merchant_id);
    }
    public ArrayList<DiningComment> selectDCByMerchantId(BigInteger merchant_id) {
        return this.diningCommentDao.selectDCByMerchantId(merchant_id);
    }
    public ArrayList<HashMap> selectDCMapByMerAndCusId(@Param("merchant_id") BigInteger merchant_id, @Param("customer_id") BigInteger customer_id) {
        return this.diningCommentDao.selectDCMapByMerAndCusId(merchant_id, customer_id);
    }
    public void insertDC(DiningComment diningComment) {
        this.diningCommentDao.insertDC(diningComment);
    }
    public void deleteDCById(BigInteger dining_com_id) {
        this.diningCommentDao.deleteDCById(dining_com_id);
    }
    public Double getAverageRateByMerchantId(BigInteger merchant_id) {return this.diningCommentDao.getAverageRateByMerchantId(merchant_id);}
}
