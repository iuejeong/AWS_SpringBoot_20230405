package com.web.study.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{

	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token = getToken(request);
		
		boolean validationFlag = jwtTokenProvider.validateToken(token);
		
		if(validationFlag) {
			Authentication authentication = jwtTokenProvider.getAuthentication(token);	// Authentication 객체 들고오기
			SecurityContextHolder.getContext().setAuthentication(authentication);	// 이게 돼야지만 인증이 된 것
		}
		
		chain.doFilter(request, response);	// client -> controller에게 넘어갈 때 filter하는 과정
	}
	
	private String getToken(ServletRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String type = "Bearer";
		String token = httpServletRequest.getHeader("Authorization");
		
		// hasText: 문자열이 Null or 공백이 아닌지 확인
		if(StringUtils.hasText(token) && token.startsWith(type)) {
			// substring: 지정한 시작 지점부터 지정한 끝 지점까지 잘라라. 만약 끝자리를 정하지 않았다면 그 길이의 맨 끝까지 자르게 된다.
			return token.substring(type.length() + 1);
		}
		return null;
	}

}
