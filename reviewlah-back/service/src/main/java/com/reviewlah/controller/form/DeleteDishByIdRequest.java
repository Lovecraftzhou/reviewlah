package com.reviewlah.controller.form;

import java.math.BigInteger;

public class DeleteDishByIdRequest {
    private BigInteger dish_id;
    public BigInteger getDish_id() {
        return dish_id;
    }

    public void setDish_id(BigInteger dish_id) {
        this.dish_id = dish_id;
    }
}
