package com.reviewlah.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class mainPage {
    @GetMapping("/hello")
    public String hello(){
        return "Welcome to ReviewLah! -Powered   by SpringBoot & VueJS";
    }
}
