package com.reviewlah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReviewlahAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewlahAdminApplication.class, args);
	}

}
