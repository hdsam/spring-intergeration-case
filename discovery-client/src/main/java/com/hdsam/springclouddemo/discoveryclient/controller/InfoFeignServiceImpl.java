package com.hdsam.springclouddemo.discoveryclient.controller;

import com.hdsam.feign.discoveryclient.InfoFeignService;
import org.springframework.web.bind.annotation.RestController;

/**
 * InfoService
 *
 * @author Yeo
 * @date 2023/6/30
 */
@RestController
public class InfoFeignServiceImpl implements InfoFeignService {

	@Override
	public String getInfo() {
		return "service";
	}
}
