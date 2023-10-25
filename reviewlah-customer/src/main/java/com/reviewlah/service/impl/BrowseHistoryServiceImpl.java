package com.reviewlah.service.impl;

import com.reviewlah.db.dao.BrowseHistoryDao;
import com.reviewlah.db.pojo.BrowseHistory;
import com.reviewlah.service.BrowseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
@Service
public class BrowseHistoryServiceImpl implements BrowseHistoryService {
    @Autowired
    BrowseHistoryDao browseHistoryDao;

    public ArrayList<BrowseHistory> selectAllBrowseHistory() {
        ArrayList<BrowseHistory> list = this.browseHistoryDao.selectAllBrowseHistory();
        return list;
    }
    public BrowseHistory selectBrowseHistoryByHistoryID(BigInteger history_id){
        BrowseHistory browseHistory=this.browseHistoryDao.selectBrowseHistoryByHistoryID(history_id);
        return browseHistory;
    }
    public ArrayList<Integer> selectTop3CategoryFromBrowseHistory(BigInteger customer_id, Date time_his){
        ArrayList<Integer> list=this.browseHistoryDao.selectTop3CategoryFromBrowseHistory(customer_id,time_his);
        return list;
    }

    public ArrayList<BrowseHistory>selectBrowseHistoryByTimeHis(Date time_his){
        ArrayList<BrowseHistory> list=this.browseHistoryDao.selectBrowseHistoryByTimeHis(time_his);
        return list;
    }

    public ArrayList<BrowseHistory>selectBrowseHistoryByCustomerId(BigInteger customer_id) {
        return this.browseHistoryDao.selectBrowseHistoryByCustomerId(customer_id);
    }


    public void deleteBrowseHistoryByHistoryID(BigInteger history_id) {
        this.browseHistoryDao.deleteBrowseHistoryByHistoryID(history_id);
    }


    public void insertBrowseHistory(BrowseHistory browseHistory) {
        this.browseHistoryDao.insertBrowseHistory(browseHistory);
    }


}