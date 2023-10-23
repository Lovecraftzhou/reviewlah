package com.reviewlah.service;

import com.reviewlah.db.pojo.BrowseHistory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public interface BrowseHistoryService {
    BrowseHistory selectBrowseHistoryByHistoryID(BigInteger history_id);
    ArrayList<Integer> selectTop3CategoryFromBrowseHistory(BigInteger customer_id, Date time_his);
    //ArrayList<BrowseHistory>selectBrowseHistoryByCategoryID(int category_id);
    ArrayList<BrowseHistory>selectBrowseHistoryByTimeHis(Date time_his);
    ArrayList<BrowseHistory>selectBrowseHistoryByCustomerId(BigInteger customer_id);
    void deleteBrowseHistoryByHistoryID(BigInteger history_id);
    void insertBrowseHistory(BrowseHistory browseHistory);
}
