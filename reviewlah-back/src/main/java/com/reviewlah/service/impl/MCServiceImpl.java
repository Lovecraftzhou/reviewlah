package com.reviewlah.service.impl;

import com.reviewlah.db.dao.MCDao;
import com.reviewlah.db.pojo.MC;
import com.reviewlah.service.MCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class MCServiceImpl implements MCService {

    @Autowired
    private MCDao mcDao;
    public void insertMC(MC mc) {
        this.mcDao.insertMC(mc);
    }
    public void updateMC(MC mc) {
        this.mcDao.updateMC(mc);
    }
    public void deleteMCByMerchantId(BigInteger merchant_id) {
        this.mcDao.deleteMCByMerchantId(merchant_id);
    }
}
