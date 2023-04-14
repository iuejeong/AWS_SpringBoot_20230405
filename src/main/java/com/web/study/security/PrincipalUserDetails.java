package com.web.study.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PrincipalUserDetails implements UserDetails {

	private static final long serialVersionUID = -1913596722176708482L;

	private int userId;
	private String username;
	private String password;
	private List<String> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));		// 권한 객체를 생성
		});
		
		return authorities;		// 권한 목록을 반환, List가 Collection으로 업캐스팅됨
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	// 사용기간 만료 = false
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	// 계정을 잠궈버림 = false
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	// 비밀번호 5회 틀렸을 때 잠궈버림 = false
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	// 계정 비활성 상태(이메일 인증을 완료해야 하거나 또는 전화번호 인증을 하지 않았을 때) = false
	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
