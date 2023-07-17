package com.hdsam.springclouddemo.feigndemo.client;

import com.hdsam.springclouddemo.feigndemo.pojo.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * UserFeignFallback
 *
 * @author Yeo
 * @date 2023/6/25
 */
@Component
public class UserFeignFallback implements UserFeignClient{
	@Override
	public List<User> getUsers() {
		return Arrays.asList(new User());
	}
}
