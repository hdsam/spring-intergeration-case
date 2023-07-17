package com.hdsam.springclouddemo.springsecuritydemo.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JwtConfig
 *
 * @author Yeo
 * @date 2023/7/14
 */
//@Configuration
@ConfigurationProperties("application.jwt")
@Setter
@Getter
public class JwtConfig {
	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpireAfterDays;


	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}
}
