package com.sanjay.experience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//@LoadBalancerClient(name = "experience")
public class ExperienceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExperienceApplication.class, args);
	}

}
