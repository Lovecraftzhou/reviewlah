package com.example;

import com.reviewlah.ReviewlahApplication;
import com.reviewlah.db.pojo.BrowseHistory;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.db.pojo.DiningComment;
import com.reviewlah.db.pojo.Post;
import com.reviewlah.service.BrowseHistoryService;
import com.reviewlah.service.CustomerService;
import com.reviewlah.service.DiningCommentService;
import com.reviewlah.service.PostService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = ReviewlahApplication.class)
class ReviewlahApplicationTests {
    @Autowired
    @Resource
    private DiningCommentService DiningCommentService;
    @Autowired
    @Resource
    private BrowseHistoryService BrowseHistoryService;
    @Autowired
    @Resource
    private PostService PostService;

    @Test
    void DiningCommentTest() {
        DiningComment comment = new DiningComment();
        comment.setCustomer_id(BigInteger.ONE);
        comment.setMerchant_id(BigInteger.ONE);
        comment.setRate(5);
        comment.setContent("Very good");
        comment.setPic_dc("https://www.google.com");
        comment.setTime_dc(new Date());
        DiningCommentService.insertDC(comment);
        ArrayList<DiningComment> comments = DiningCommentService.selectAllDC();
        assertFalse(comments.isEmpty());
        comments = DiningCommentService.selectDCByMerchantId(BigInteger.ONE);
        assertFalse(comments.isEmpty());
        ArrayList<HashMap> maps = DiningCommentService.selectDCMapByMerAndCusId(BigInteger.ONE, BigInteger.ONE);
        assertFalse(maps.isEmpty());
        maps = DiningCommentService.selectDCMapByMerchantId(BigInteger.ONE);
        assertFalse(maps.isEmpty());
        Double rate = DiningCommentService.getAverageRateByMerchantId(BigInteger.ONE);
        assertNotNull(rate);
        DiningCommentService.deleteDCById(comment.getDining_com_id());
    }

    @Test
    void BrowseHistoryServiceTest() {
        BrowseHistory browseHistory = new BrowseHistory();
        browseHistory.setCustomer_id(BigInteger.ONE);
        browseHistory.setCategory_id(1);
        Date date = new Date();
        browseHistory.setTime_his(date);
        BrowseHistoryService.insertBrowseHistory(browseHistory);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -5);
        ArrayList<BrowseHistory> histories = BrowseHistoryService.selectBrowseHistoryByTimeHis(calendar.getTime());
        assertFalse(histories.isEmpty());
        histories = BrowseHistoryService.selectBrowseHistoryByCustomerId(BigInteger.ONE);
        assertFalse(histories.isEmpty());
        ArrayList<Integer> categories = BrowseHistoryService.selectTop3CategoryFromBrowseHistory(BigInteger.ONE, calendar.getTime());
        assertFalse(categories.isEmpty());
        BrowseHistoryService.deleteBrowseHistoryByHistoryID(browseHistory.getHistory_id());
    }

    @Test
    void PostService() {
        Post post1 = new Post();
        post1.setCustomer_id(BigInteger.ONE);
        post1.setTitle("post 1");
        post1.setContent("post content");
        post1.setTime_post(new Date());
        post1.setPic_post("https://www.googole.com");
        PostService.insertPost(post1);
        assertNotNull(post1);
        ArrayList<HashMap> maps = PostService.selectPostByCustomerId(BigInteger.ONE);
        assertFalse(maps.isEmpty());
        Post post2 = new Post();
        post2.setCustomer_id(BigInteger.ONE);
        post2.setTitle("post 2");
        post2.setContent("post content 2");
        post2.setTime_post(new Date());
        post2.setPic_post("https://www.googole.com");
        PostService.insertPost(post2);
        maps = PostService.selectAllPostExceptMine(BigInteger.ONE);
        assertFalse(maps.isEmpty());
    }

}
