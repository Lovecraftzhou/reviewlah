package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.BrowseHistory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public interface BrowseHistoryDao {
    BrowseHistory selectBrowseHistoryByHistoryID(BigInteger history_id);
    int selectBrowseHistoryByCustomerIDAndCategory(BigInteger customer_id,int category_id);
    //ArrayList<BrowseHistory>selectBrowseHistoryByCategoryID(int category_id);
    ArrayList<BrowseHistory>selectBrowseHistoryByTimeHis(Date time_his);
    ArrayList<BrowseHistory>selectBrowseHistoryByMerchantID(BigInteger merchant_id);
    void deleteBrowseHistoryByHistoryID(BigInteger history_id);
    void insertBrowseHistory(BrowseHistory browseHistory);

}
