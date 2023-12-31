package com.reviewlah.service;

import com.reviewlah.db.pojo.MC;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public interface MCService {
    void insertMC(MC mc);
    void updateMC(MC mc);
    void deleteMCByMerchantId(BigInteger merchant_id);
    ArrayList<String> selectMCByMerchantId(BigInteger merchant_id);

}
