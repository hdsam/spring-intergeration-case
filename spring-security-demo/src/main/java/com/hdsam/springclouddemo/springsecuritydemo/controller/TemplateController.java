package com.hdsam.springclouddemo.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TemplateController
 *
 * @author Yeo
 * @date 2023/7/13
 */
@Controller
@RequestMapping("/")
public class TemplateController {

	@GetMapping("login")
	public String getLoginView(){
		return "login";
	}

	@GetMapping("courses")
	public String getCourseView(){
		return "courses";
	}
}
