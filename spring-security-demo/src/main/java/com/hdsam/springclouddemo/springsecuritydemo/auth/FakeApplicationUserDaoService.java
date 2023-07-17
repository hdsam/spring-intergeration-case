package com.hdsam.springclouddemo.springsecuritydemo.auth;

import com.google.common.collect.Lists;
import com.hdsam.springclouddemo.springsecuritydemo.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * FakeApplicationUserDaoService
 *
 * @author Yeo
 * @date 2023/7/13
 */
@Service("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers().stream()
				.filter(user -> username.equals(user.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		List<ApplicationUser> users = Lists.newArrayList(
				new ApplicationUser(
						"annasmith",
						passwordEncoder.encode("password"),
						ApplicationUserRole.STUDENT.getGrantedAuthorities(),
						true,
						true,
						true,
						true
				),
				new ApplicationUser(
						"linda",
						passwordEncoder.encode("password"),
						ApplicationUserRole.ADMIN.getGrantedAuthorities(),
						true,
						true,
						true,
						true
				),
				new ApplicationUser(
						"tom",
						passwordEncoder.encode("password"),
						ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities(),
						true,
						true,
						true,
						true
				)
		);
		return users;
	}
}
