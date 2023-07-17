package com.hdsam.springclouddemo.springsecuritydemo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ApplicationUserService
 *
 * @author Yeo
 * @date 2023/7/13
 */
@Service
public class ApplicationUserService implements UserDetailsService {


	@Autowired
	@Qualifier("fake")
	private ApplicationUserDao applicationUserDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser user = applicationUserDao.selectApplicationUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("username not found", username)));
		return user;
	}
}
