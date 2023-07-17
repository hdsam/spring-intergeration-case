package com.hdsam.springclouddemo.springsecuritydemo.intergation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.jackson2.SecurityJackson2Modules;

import java.util.List;

/**
 * SpringSecurityConcurrencyApiTest
 *
 * @author Yeo
 * @date 2023/7/6
 */
@SpringBootTest
public class SpringSecurityConcurrencyApiTest {

	@Test
	public void runnableTest(){
		Runnable runnable = () -> {
			System.out.println("Starting Spring Security");
		};

//		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//		DelegatingSecurityContextRunnable delegatingSecurityContextRunnable = new DelegatingSecurityContextRunnable(runnable, securityContext);

		DelegatingSecurityContextRunnable delegatingSecurityContextRunnable = new DelegatingSecurityContextRunnable(runnable);
		new Thread(delegatingSecurityContextRunnable).start();

	}

	@Test
	public void executorsTest() {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		Authentication authentication = UsernamePasswordAuthenticationToken
				.authenticated("user", "doesnotmatter", AuthorityUtils.createAuthorityList("ROLE_USER"));
		securityContext.setAuthentication(authentication);

		SimpleAsyncTaskExecutor delegate = new SimpleAsyncTaskExecutor();
		DelegatingSecurityContextExecutor executor = new DelegatingSecurityContextExecutor(delegate, securityContext);

		executor.execute(() -> {
			System.out.println("running in security context...");
		});
	}

	@Test
	public void jacksonJsonTest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Module> modules = SecurityJackson2Modules.getModules(getClass().getClassLoader());
		objectMapper.registerModules(modules);

		SecurityContext securityContext = new SecurityContextImpl();

		String json = objectMapper.writeValueAsString(securityContext);
		System.out.println("security context json: " + json);

	}
}
