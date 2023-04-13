package com.web.study.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.web.study.dto.response.auth.JwtTokenRespDto;
import com.web.study.security.PrincipalUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
	private final Key key;
	
	// Key 만드는 공식
	public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {	// @Component일 때 사용 가능. 아까 설정해둔 yml의 key값이 매개변수로 들어간다
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public JwtTokenRespDto createToken(Authentication authentication) {
		StringBuilder authoritiesBuilder = new StringBuilder();
		
		authentication.getAuthorities().forEach(grantedAuthority -> {
			authoritiesBuilder.append(grantedAuthority.getAuthority());
			authoritiesBuilder.append(",");
		});
		
		authoritiesBuilder.delete(authoritiesBuilder.length() - 1, authoritiesBuilder.length()); 	// ~부터 ~까지 지워라
		
		String authorities = authoritiesBuilder.toString();
		
		long now = (new Date()).getTime();		// 초 단위까지 들고 오면 밀리세컨드까지 가서 숫자가 되게 큼. 그래서 long type
		
		// 1000 == 1초
		Date tokenExpiresDate = new Date(now + (1000 * 60 * 30));	// 토큰 만료 시간
		
		PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
		
		String accessToken = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("userId", userDetails.getUserId())
				.claim("auth", authorities)
				.setExpiration(tokenExpiresDate)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return JwtTokenRespDto.builder()
				.grantType("Bearer")
				.accessToken(accessToken)
				.build(); 
		
	}
	
	
}
