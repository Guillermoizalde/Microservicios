package com.guillermo.springcloud.msv.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvItemsApplication.class, args);
	}

}
