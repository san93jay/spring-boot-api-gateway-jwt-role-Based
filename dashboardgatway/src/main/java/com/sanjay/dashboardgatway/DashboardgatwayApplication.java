package com.sanjay.dashboardgatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
public class DashboardgatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardgatwayApplication.class, args);
	}

}
