package com.hdsam.feign.discoveryclient;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yeo
 */
public interface InfoFeignService {

	@GetMapping("info")
	String getInfo();
}