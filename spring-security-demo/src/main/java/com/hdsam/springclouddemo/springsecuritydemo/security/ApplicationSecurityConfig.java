package com.hdsam.springclouddemo.springsecuritydemo.security;

import com.hdsam.springclouddemo.springsecuritydemo.auth.ApplicationUserService;
import com.hdsam.springclouddemo.springsecuritydemo.jwt.JwtConfig;
import com.hdsam.springclouddemo.springsecuritydemo.jwt.JwtTokenVerifier;
import com.hdsam.springclouddemo.springsecuritydemo.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

import static com.hdsam.springclouddemo.springsecuritydemo.security.ApplicationUserRole.STUDENT;

/**
 * UserLoginConfig
 *
 * @author Yeo
 * @date 2023/7/10
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationUserService applicationUserService;

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	private JwtConfig jwtConfig;

	@Autowired
	private SecretKey secretKey;


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
//				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
				.addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
				.antMatchers("/api/**").hasRole(STUDENT.name())
//				.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTEAINEE.name())
				.anyRequest()
				.authenticated()
//				.and()
////				.httpBasic()
//				.formLogin()
//					.loginPage("/login").permitAll()
//					.defaultSuccessUrl("/courses", true)
//					.usernameParameter("username")
//					.passwordParameter("password")
//				.and()
//				.rememberMe()  // default to 2 weeks
//					.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//					.key("somethingverysecured")
//					.rememberMeParameter("remember-me")
//				.and()
//				.logout()
//					.logoutUrl("/logout")
//					.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//					.clearAuthentication(true)
//					.invalidateHttpSession(true)
//					.deleteCookies("JSESSIONID", "remember-me")
//					.logoutSuccessUrl("/login")
				.and()
				.authenticationProvider(daoAuthenticationProvider())
		;
		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails annaSmithUser = User.builder()
//				.username("annasmith")
//				.password(passwordEncoder.encode("password"))
////				.roles(STUDENT.name())
//				.authorities(STUDENT.getGrantedAuthorities())
//				.build();
//
//		UserDetails lindaUser = User.builder()
//				.username("linda")
//				.password(passwordEncoder.encode("password123"))
////				.roles(ADMIN.name())
//				.authorities(ADMIN.getGrantedAuthorities())
//				.build();
//
//		UserDetails tomUser = User.builder()
//				.username("tom")
//				.password(passwordEncoder.encode("password123"))
////				.roles(ADMINTRAINEE.name())
//				.authorities(ADMINTRAINEE.getGrantedAuthorities())
//				.build();
//
//		return new InMemoryUserDetailsManager(
//				annaSmithUser,
//				lindaUser,
//				tomUser
//		);
//	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}


}
