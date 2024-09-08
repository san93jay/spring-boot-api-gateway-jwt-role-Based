package com.sanjay.dashboardserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DashboardserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardserverApplication.class, args);
	}

}
