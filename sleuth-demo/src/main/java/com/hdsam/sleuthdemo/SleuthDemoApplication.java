package com.hdsam.sleuthdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Sleuth demo
 *
 * @author Yeo
 */

@SpringBootApplication
@EnableEurekaClient
public class SleuthDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SleuthDemoApplication.class, args);
	}
}
