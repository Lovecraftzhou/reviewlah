package com.reviewlah;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@MapperScan("com.reviewlah.db.dao")
@SpringBootApplication
public class ReviewlahMerchantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewlahMerchantApplication.class, args);
	}

}