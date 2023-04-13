package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// security filter
	@Override
	protected void configure(HttpSecurity http) throws Exception {	// controller 들어오기 전에 무조건 한 번 거침
		http.csrf().disable();	
		http.authorizeRequests()
			.antMatchers("/auth/register/**", "/auth/login/**")
			.permitAll()	// 전부 다 권한을 줘라
			.anyRequest()	// 나머지 모든 요청들은
			.authenticated();	// 인증을 거쳐라
	}
}
