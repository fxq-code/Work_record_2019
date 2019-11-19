package com.echarts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.echarts.springboot.dao.mapper")
public class EchartsDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(EchartsDemoApplication.class, args);
	}

}
