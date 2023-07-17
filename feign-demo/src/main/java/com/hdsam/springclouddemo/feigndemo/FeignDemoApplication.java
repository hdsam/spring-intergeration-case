package com.hdsam.springclouddemo.feigndemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@Slf4j
public class FeignDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignDemoApplication.class, args);
	}


	@Bean
	CommandLineRunner logger(ApplicationContext context) {
		return args -> {
			String[] beanDefinitionNames = context.getBeanDefinitionNames();
			Arrays.sort(beanDefinitionNames);
			for (String bean : beanDefinitionNames) {
				log.info("{}", bean);
			}
			log.info("加载bean的总数：{}", beanDefinitionNames.length);
		};
	}

	//	@Bean
	CommandLineRunner testGet() {
		return args -> {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("x-api-key", "PMAK-64982be76b55040031a74ec8-63d84313f24779e4bba4ffc928cb7e065b");
				HttpEntity<String> entity = new HttpEntity<>(headers);
				ResponseEntity<String> res = new RestTemplate().exchange("https://c001cea6-8d53-4024-b248-3479a2ebd93c.mock.pstmn.io/users",
						HttpMethod.GET,
						entity,
						String.class);
				log.info("{}", res.getStatusCodeValue());
			} catch (RestClientException e) {
				log.error("请求异常", e);
			}
		};
	}
}
