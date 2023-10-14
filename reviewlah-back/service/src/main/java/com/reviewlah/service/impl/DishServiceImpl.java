package com.reviewlah.service.impl;

import com.reviewlah.db.dao.DishDao;
import com.reviewlah.db.pojo.Dish;
import com.reviewlah.service.DishService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishDao dishDao;
    public ArrayList<Dish> selectAllDish() {
        return this.dishDao.selectAllDish();
    }
    public ArrayList<Dish> selectDishByName(@Param("dish_name") String dish_name, @Param("menu_id") BigInteger menu_id) {
        return this.dishDao.selectDishByName(dish_name, menu_id);
    }
    public Dish selectDishById(BigInteger dish_id) {
        return this.dishDao.selectDishById(dish_id);
    }
    public ArrayList<Dish> selectDishByMenuId(BigInteger menu_id) {
        return this.dishDao.selectDishByMenuId(menu_id);
    }
    public void insertDish(Dish dish) {
        this.dishDao.insertDish(dish);
    }
    public void updateDish(Dish dish) {
        this.dishDao.updateDish(dish);
    }
    public void deleteDishByName(@Param("dish_name") String dish_name, @Param("menu_id") BigInteger menu_id) {
        this.dishDao.deleteDishByName(dish_name, menu_id);
    }
    public void deleteDishById(BigInteger dish_id) {
        this.dishDao.deleteDishById(dish_id);
    }
}
