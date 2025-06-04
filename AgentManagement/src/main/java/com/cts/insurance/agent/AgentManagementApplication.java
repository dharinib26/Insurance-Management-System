package com.cts.insurance.agent;


import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients // Enables Feign clients in the application for remote service 
@SpringBootApplication // Marks this class as a Spring Boot application
public class AgentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentManagementApplication.class, args);
	}

}
