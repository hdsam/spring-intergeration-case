package com.hdsam.springclouddemo.springsecuritydemo.auth;

import java.util.Optional;

/**
 * ApplicationUserDao
 *
 * @author Yeo
 * @date 2023/7/13
 */
public interface ApplicationUserDao {
	/**
	 *
	 * @param username
	 * @return
	 */
	Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
