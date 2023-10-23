package com.reviewlah.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.BrowseHistory;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BrowseHistoryServiceTest {
    @Autowired
    private BrowseHistoryService service;

    @Test
    void test() {
        BrowseHistory browseHistory = new BrowseHistory();
        browseHistory.setCustomer_id(BigInteger.ONE);
        browseHistory.setCategory_id(1);
        Date date = new Date();
        browseHistory.setTime_his(date);
        service.insertBrowseHistory(browseHistory);
        browseHistory = service.selectBrowseHistoryByHistoryID(browseHistory.getHistory_id());
        assertNotNull(browseHistory);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -5);
        ArrayList<BrowseHistory> histories = service.selectBrowseHistoryByTimeHis(calendar.getTime());
        assertFalse(histories.isEmpty());
        histories = service.selectBrowseHistoryByCustomerId(BigInteger.ONE);
        assertFalse(histories.isEmpty());
        ArrayList<Integer> categories = service.selectTop3CategoryFromBrowseHistory(BigInteger.ONE, calendar.getTime());
        assertFalse(categories.isEmpty());
        service.deleteBrowseHistoryByHistoryID(browseHistory.getHistory_id());
    }
}
