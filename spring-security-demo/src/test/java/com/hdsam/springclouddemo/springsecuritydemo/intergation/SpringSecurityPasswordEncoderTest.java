package com.hdsam.springclouddemo.springsecuritydemo.intergation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yeo
 * @date 2023/7/2
 */
@Slf4j
public class SpringSecurityPasswordEncoderTest {

	@Test
	public void passwordFormatTest() {
		User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		UserDetails user = userBuilder
				.username("user")
				.password("password")
				.roles("user")
				.build();

		log.info("{}", user.getPassword());
	}


	@Test
	public void bcryptPasswordEncoderTest() {
		long start = System.currentTimeMillis();
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
//		String result = encoder.encode("myPassword");

		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
		String result = encoder.encode("myPassword");
		assertTrue(encoder.matches("myPassword", result));


		log.info("{}", result);
		log.info("elapsed ms: {}", System.currentTimeMillis() - start);
		assertTrue(encoder.matches("myPassword", result));
	}

}