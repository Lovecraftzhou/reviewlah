package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.DiningComment;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.ArrayList;

public interface DiningCommentDao {
    DiningComment selectDCById(BigInteger dining_com_id);
    ArrayList<DiningComment> selectDCByMerchantId(BigInteger merchant_id);
    ArrayList<DiningComment> selectDCByMerAndCusId(@Param("merchant_id") BigInteger merchant_id, @Param("customer_id") BigInteger customer_id);
    void insertDC(DiningComment diningComment);
    void deleteDCById(BigInteger dining_com_id);
}
