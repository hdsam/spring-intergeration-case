package com.hdsam.springclouddemo.springsecuritydemo.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UsernameAndPasswordAuthenticationRequest
 *
 * @author Yeo
 * @date 2023/7/14
 */
@Setter
@Getter
@NoArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {

	private String username;
	private String password;

}
