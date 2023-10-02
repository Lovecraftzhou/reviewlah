package com.reviewlah.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @GetMapping("/book/{id}")
    public String getById(@PathVariable Integer id){
        System.out.println("id == >" + id);
        return "id = " + id;
    }
}
