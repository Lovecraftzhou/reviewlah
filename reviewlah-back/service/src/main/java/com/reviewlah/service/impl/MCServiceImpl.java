package com.reviewlah.service.impl;

import com.reviewlah.db.dao.MCDao;
import com.reviewlah.db.pojo.MC;
import com.reviewlah.service.MCService;

public class MCServiceImpl implements MCService {
    private MCDao mcDao;
    public void insertMC(MC mc) {
        this.mcDao.insertMC(mc);
    }
    public void updateMC(MC mc) {
        this.mcDao.updateMC(mc);
    }
}
