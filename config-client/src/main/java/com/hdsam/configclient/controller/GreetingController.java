package com.hdsam.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GreetingController
 *
 * @author Yeo
 * @date 2023/6/26
 */
@RestController
@RefreshScope
public class GreetingController {

	@Value("${greeting.message}")
	private String greetingMessage;

	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping("hello")
	public String getGreetingMessage() {
		return "get from config :" + applicationName + "." + greetingMessage;
	}
}
