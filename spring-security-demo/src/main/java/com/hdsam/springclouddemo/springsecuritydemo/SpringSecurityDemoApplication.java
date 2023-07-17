package com.hdsam.springclouddemo.springsecuritydemo;

import com.hdsam.springclouddemo.springsecuritydemo.jwt.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * SpringSecurityDemoApplication
 *
 * @author Yeo
 * @date 2023/7/2
 */
@SpringBootApplication
@Slf4j
@EnableConfigurationProperties(JwtConfig.class)
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner log(ApplicationContext context){
		return args -> {
			String[] beans = context.getBeanDefinitionNames();
			Arrays.sort(beans);
			for (String bean : beans) {
				log.info("{}", bean);
			}
			log.info("加载bean的个数为：{}", context.getBeanDefinitionCount());
		};
	}
}
