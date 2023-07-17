package com.hdsam.springclouddemo.feigndemo.client;

import com.hdsam.feign.discoveryclient.InfoFeignService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * InfoFeignClient
 *
 * @author Yeo
 * @date 2023/6/30
 */

@FeignClient("discovery-client")
public interface InfoFeignClient extends InfoFeignService {
}
