package com.reviewlah.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.DiningComment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class DiningCommentServiceTest {
    @Autowired
    private DiningCommentService service;

    @Test
    void test() {
        DiningComment comment = new DiningComment();
        comment.setCustomer_id(BigInteger.ONE);
        comment.setMerchant_id(BigInteger.ONE);
        comment.setRate(5);
        comment.setContent("Very good");
        comment.setPic_dc("https://www.google.com");
        comment.setTime_dc(new Date());
        service.insertDC(comment);
        comment = service.selectDCById(comment.getDining_com_id());
        assertNotNull(comment);
        ArrayList<DiningComment> comments = service.selectAllDC();
        assertFalse(comments.isEmpty());
        comments = service.selectDCByMerchantId(BigInteger.ONE);
        assertFalse(comments.isEmpty());
        ArrayList<HashMap> maps = service.selectDCMapByMerAndCusId(BigInteger.ONE, BigInteger.ONE);
        assertFalse(maps.isEmpty());
        maps = service.selectDCMapByMerchantId(BigInteger.ONE);
        assertFalse(maps.isEmpty());
        Double rate = service.getAverageRateByMerchantId(BigInteger.ONE);
        assertNotNull(rate);
        service.deleteDCById(comment.getDining_com_id());
    }
}