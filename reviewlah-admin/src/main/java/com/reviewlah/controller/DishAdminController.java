package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.*;
import com.reviewlah.remote.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/admin/merchant/menu"})
public class DishAdminController {
    @Autowired
    private DishService dishService;
    @GetMapping({"/showAllDish"})
    public RCode selectAllDish() {
        return this.dishService.selectAllDish();
    }
    @PostMapping({"/dish"})
    public RCode selectDishById(@RequestBody SelectDishByIdRequest request) {
        return this.dishService.selectDishById(request);
    }
    @PostMapping({"/dishes"})
    public RCode selectDishByMenuId(@RequestBody SelectDishByMenuIdRequest request) {
        return this.dishService.selectDishByMenuId(request);
    }
    @PostMapping({"/insert_dish"})
    public RCode insertDish(@RequestBody InsertDishRequest request) {
        return this.dishService.insertDish(request);
    }
    @PostMapping({"/update_dish"})
    public RCode updateDish(@RequestBody UpdateDishRequest request) {
        return this.dishService.updateDish(request);
    }
    @PostMapping({"/delete_dish"})
    public RCode deleteDishById(@RequestBody DeleteDishByIdRequest request) {
        return this.dishService.deleteDishById(request);
    }
}
