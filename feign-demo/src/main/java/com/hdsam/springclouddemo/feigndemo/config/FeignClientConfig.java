package com.hdsam.springclouddemo.feigndemo.config;

import com.hdsam.springclouddemo.feigndemo.exception.BadRequestException;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * FeignClientConfig
 *
 * @author Yeo
 * @date 2023/6/25
 */
public class FeignClientConfig {

	@Bean
	Logger.Level logLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptorUserService(
			@Value("${user-api-client.username}") String username,
			@Value("${user-api-client.password}") String password) {
		return new BasicAuthRequestInterceptor(username, password);
	}

	@Bean
	public RequestInterceptor requestInterceptor(@Value("${user-api-client.x-api-key}") String key) {
		return requestTemplate -> {
			requestTemplate.header("x-api-key", key);
		};
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return (s, response) -> {
			if (response.status() == 400) {
				return new BadRequestException("Bad request for user service...");
			}
			return new Exception(("Generic exception..."));
		};
	}

}
