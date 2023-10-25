package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("merchant")
public interface DishService {
    @GetMapping({"/merchant/menu/showAllDish"})
    public RCode selectAllDish();
    @GetMapping({"/merchant/menu/dish"})
    public RCode selectDishById(@RequestBody SelectDishByIdRequest request);
    @PostMapping({"/merchant/menu/dishes"})
    public RCode selectDishByMenuId(@RequestBody SelectDishByMenuIdRequest request);
    @PostMapping({"/merchant/menu/insert_dish"})
    public RCode insertDish(@RequestBody InsertDishRequest request);
    @PostMapping({"/merchant/menu/update_dish"})
    public RCode updateDish(@RequestBody UpdateDishRequest request);
    @PostMapping({"/merchant/menu/delete_dish"})
    public RCode deleteDishById(@RequestBody DeleteDishByIdRequest request);
}
