package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Menu;

import java.math.BigInteger;
import java.util.ArrayList;

public interface MenuDao {
    ArrayList<Menu> selectAllMenu();
    Menu selectMenuByMerchantId(BigInteger merchant_id);
    void insertMenu(Menu menu);
    void updateMenu(Menu menu);
    void deleteMenuById(BigInteger menu_id);
}
