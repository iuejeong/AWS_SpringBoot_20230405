package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.study.security.jwt.JwtAuthenticationEntryPoint;
import com.web.study.security.jwt.JwtAuthenticationFilter;
import com.web.study.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	// DB에는 비밀번호가 암호화가 되어있지만 BCrypt가 IoC에 등록이 되어있지 않기 때문에
	// 토큰을 주기 위해 비교할 수 없다. 그래서 BCrypt를 IoC에 등록을 해준다.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// security filter
	// 여기서 인증이 이루어져야 함
	@Override
	protected void configure(HttpSecurity http) throws Exception {	// controller 들어오기 전에 무조건 한 번 거침
		http.csrf().disable();
		http.httpBasic().disable();		// httpBasic: 웹 기본 인증 방식(alert창 띄워서 인증하는 방식)
		http.formLogin().disable();		// formLogin: formTag를 통한 로그인
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	// 세션 비활성화(무상태성(맥도날드 이야기))
		http.authorizeRequests()
			.antMatchers("/auth/register/**", "/auth/login/**")		// 이 주소를 인증받지 못하면 403
			.permitAll()	// 전부 다 권한을 줘라
			.antMatchers("/courses")
			.hasRole("ADMIN")	// 권한에 ADMIN이 없으면 403 있으면 인증 단계로 간다.
			.anyRequest()	// 나머지 모든 요청들은
			.authenticated()	// 인증을 거쳐라(SecurityContextHolder에서)
			.and()
			// Security 인증 이전에 filter를 넣어주겠다. 여기서 filter 처리를 한다
			// UsernamePasswordAuthenticationFilter.class는 고정이다.
			// UsernamePasswordAuthenticationFilter.class는 SecurityContextHolder를 꺼내서 쓸 수 있는지 없는지 확인. 쓸 수 없으면 다음 에러로 넘어간다.
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
			// Authentication의 에러가 나면 실행된다.
			.authenticationEntryPoint(jwtAuthenticationEntryPoint);		// filter -> 로그인이 안 되면 얘가 받아서 처리해줌 (401: 검증이 되지 않았다, 403: 권한이 없다)
	}
}
