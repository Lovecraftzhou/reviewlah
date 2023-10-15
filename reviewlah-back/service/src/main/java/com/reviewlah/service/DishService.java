package com.reviewlah.service;

import com.reviewlah.db.pojo.Dish;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public interface DishService {
    ArrayList<Dish> selectAllDish();
    ArrayList<Dish> selectDishByName(@Param("dish_name") String dish_name, @Param("menu_id") BigInteger menu_id);
    Dish selectDishById(BigInteger dish_id);
    ArrayList<Dish> selectDishByMenuId(BigInteger menu_id);
    ArrayList<HashMap> selectDishByMerchantId(BigInteger merchant_id);
    void insertDish(Dish dish);
    void updateDish(Dish Dish);
    void deleteDishByName(@Param("dish_name") String dish_name, @Param("menu_id") BigInteger menu_id);
    public void deleteDishById(BigInteger dish_id);
}
