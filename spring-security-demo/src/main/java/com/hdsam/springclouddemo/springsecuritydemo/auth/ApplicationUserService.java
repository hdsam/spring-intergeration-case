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
	private final ApplicationUserDao applicationUserDao;

	public ApplicationUserService(ApplicationUserDao applicationUserDao) {
		this.applicationUserDao = applicationUserDao;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return applicationUserDao.selectApplicationUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found", username)));
	}
}
