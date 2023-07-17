package com.hdsam.springclouddemo.feigndemo.client;

import com.hdsam.springclouddemo.feigndemo.config.FeignClientConfig;
import com.hdsam.springclouddemo.feigndemo.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * UserFeignClient
 *
 * @author Yeo
 * @date 2023/6/25
 */
@FeignClient(url = "${user-api-client.url}",
		name = "${user-api-client.name}",
		configuration = FeignClientConfig.class,
		fallback = UserFeignFallback.class
)
public interface UserFeignClient {

	/**
	 * @return userList
	 */
	@GetMapping("/users")
	List<User> getUsers();

}
