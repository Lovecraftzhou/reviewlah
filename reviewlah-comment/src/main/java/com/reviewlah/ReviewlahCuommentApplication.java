package com.reviewlah;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@MapperScan("com.reviewlah.db.dao")
@SpringBootApplication
@EnableKnife4j
public class ReviewlahCuommentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewlahCuommentApplication.class, args);
	}

}
