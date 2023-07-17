package com.hdsam.springclouddemo.springsecuritydemo.jwt;

import io.jsonwebtoken.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JwtTokenVerifier
 *
 * @author Yeo
 * @date 2023/7/14
 */
public class JwtTokenVerifier extends OncePerRequestFilter {

	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;

	public JwtTokenVerifier(JwtConfig jwtConfig, SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authorization = request.getHeader(jwtConfig.getAuthorizationHeader());
		if (StringUtils.isBlank(authorization) || !StringUtils.startsWith(authorization, jwtConfig.getTokenPrefix())) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = StringUtils.removeStart(authorization, jwtConfig.getTokenPrefix());
		try {
			Jwt<JwsHeader, Claims> jwt = Jwts.parserBuilder()
					.setSigningKey(secretKey)
					.build()
					.parseClaimsJws(token);
			Claims body = jwt.getBody();
			String username = body.getSubject();
			List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities", List.class);
			if (CollectionUtils.isNotEmpty(authorities)) {
				Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
						.map(obj -> obj.get("authority"))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toSet());
				Authentication authentication = new UsernamePasswordAuthenticationToken(
						username,
						null,
						simpleGrantedAuthorities
				);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (JwtException e) {
			throw new IllegalStateException(String.format("token %s cannot be trusted", token));
		}

		filterChain.doFilter(request, response);
	}
}
