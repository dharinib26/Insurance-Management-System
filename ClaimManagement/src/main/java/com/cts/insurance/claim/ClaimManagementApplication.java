package com.cts.insurance.claim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.cts.insurance.claim.feign")
public class ClaimManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimManagementApplication.class, args);
	}

}
