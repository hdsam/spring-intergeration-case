package com.hdsam.springclouddemo.feigndemo.controller;

import com.hdsam.springclouddemo.feigndemo.client.InfoFeignClient;
import com.hdsam.springclouddemo.feigndemo.client.UserFeignClient;
import com.hdsam.springclouddemo.feigndemo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.*;
import java.util.Collection;
import java.util.List;

/**
 * UserController
 *
 * @author Yeo
 * @date 2023/6/25
 */
@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserFeignClient userFeignClient;

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userFeignClient.getUsers();
	}

	@Autowired
	private InfoFeignClient infoFeignClient;

	@GetMapping("/info")
	public String getInfo(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String userName = getCookie(cookies, "userName");
		String userAge = getCookie(cookies, "userAge");
		if (userName == null) {
			response.addCookie(new Cookie("userName", "testUser"));
		}
		if (userAge == null) {
			response.addCookie(new Cookie("userAge", "12"));
		}
		Collection<String> headers = response.getHeaders("Set-Cookie");
		log.info("{}", headers);
		return infoFeignClient.getInfo();
	}

	String getCookie(Cookie[] cookies, String name) {
		if (cookies == null){
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
