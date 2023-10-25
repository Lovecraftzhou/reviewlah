package com.reviewlah.service.impl;

import com.reviewlah.db.dao.MenuDao;
import com.reviewlah.db.pojo.Menu;
import com.reviewlah.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;
    public ArrayList<Menu> selectAllMenu() {
        return this.menuDao.selectAllMenu();
    }
    public Menu selectMenuById(BigInteger menu_id) {
        return this.menuDao.selectMenuById(menu_id);
    }
    public Menu selectMenuByMerchantId(BigInteger merchant_id) {
        return this.menuDao.selectMenuByMerchantId(merchant_id);
    }
    public void insertMenu(Menu menu) {
        this.menuDao.insertMenu(menu);
    }
    public void updateMenu(Menu menu) {
        this.menuDao.updateMenu(menu);
    }
    public void deleteMenuById(BigInteger menu_id) {
        this.menuDao.deleteMenuById(menu_id);
    }
}
