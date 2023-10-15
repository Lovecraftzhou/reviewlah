package com.reviewlah.service;

import com.reviewlah.db.pojo.DiningComment;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public interface DiningCommentService {
    ArrayList<DiningComment> selectAllDC();
    DiningComment selectDCById(BigInteger dining_com_id);
    ArrayList<HashMap> selectDCMapByMerchantId(BigInteger merchant_id);
    ArrayList<DiningComment> selectDCByMerchantId(BigInteger merchant_id);
    ArrayList<HashMap> selectDCMapByMerAndCusId(@Param("merchant_id") BigInteger merchant_id, @Param("customer_id") BigInteger customer_id);
    void insertDC(DiningComment diningComment);
    void deleteDCById(BigInteger dining_com_id);
    Double getAverageRateByMerchantId(BigInteger merchant_id);
}
