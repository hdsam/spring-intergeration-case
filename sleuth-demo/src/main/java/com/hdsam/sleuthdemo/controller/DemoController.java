package com.hdsam.sleuthdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * DemoController
 *
 * @author Yeo
 * @date 2023/6/26
 */
@RestController
@Slf4j
public class DemoController {


	@RequestMapping("/")
	public String home() {
		log.info("Handling home");
		return "Hello World";
	}

	@RequestMapping(value = "/other")
	@ResponseBody
	public String other() {
		log.info("Handling other");
		String object = new RestTemplate().getForObject("https://www.example.com", String.class);
		log.info("{}", object);
		return object;
	}
}
