package com.hdsam.springclouddemo.discoveryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * DiscoveryClientApplication
 *
 * @author Yeo
 * @date 2023/6/30
 */
@SpringBootApplication
@EnableEurekaClient
public class DiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryClientApplication.class, args);
	}

}
