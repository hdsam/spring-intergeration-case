package com.hdsam.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 * @author Yeo
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientDemoApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConfigClientDemoApplication.class, args);
    }
}
