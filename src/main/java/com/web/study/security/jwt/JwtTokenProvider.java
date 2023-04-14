package com.web.study.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.web.study.dto.response.auth.JwtTokenRespDto;
import com.web.study.exception.CustomException;
import com.web.study.security.PrincipalUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
	
	// secretKey
	private final Key key;
	
	// Key(암호화) 만드는 공식
	public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {	// @Component일 때 사용 가능. 아까 설정해둔 yml의 key값이 매개변수로 들어간다
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public JwtTokenRespDto createToken(Authentication authentication) {
		StringBuilder authoritiesBuilder = new StringBuilder();
		
		authentication.getAuthorities().forEach(grantedAuthority -> {
			authoritiesBuilder.append(grantedAuthority.getAuthority());		// role이 하나씩 나온다.
			authoritiesBuilder.append(",");
		});
		
		authoritiesBuilder.delete(authoritiesBuilder.length() - 1, authoritiesBuilder.length()); 	// ~부터 ~까지 지워라
		
		String authorities = authoritiesBuilder.toString();		// role 권한들을 문자열로 바꾸기 위해서
		
		long now = (new Date()).getTime();		// 초 단위까지 들고 오면 밀리세컨드까지 가서 숫자가 되게 큼. 그래서 long type
		
		// 1000 == 1초
		Date tokenExpiresDate = new Date(now + (1000 * 60 * 30));	// 토큰 만료 시간 (현재로부터 30분 뒤)
		
		PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
		
		String accessToken = Jwts.builder()					// 토큰 생성 단계
				.setSubject(authentication.getName())		// 제목(PrincipalDetails의 username)
				.claim("userId", userDetails.getUserId())	// DB에서 매 번 가져오지 않고 JWT에 등록하기 위해 
				.claim("auth", authorities)					// DB에서 매 번 가져오지 않고 JWT에 등록하기 위해
				.setExpiration(tokenExpiresDate)			// 토큰 만료 시간
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return JwtTokenRespDto.builder()
				.grantType("Bearer")
				.accessToken(accessToken)
				.build(); 	// 이후 controller가 받는다.
		
	}
	// 토큰을 풀어서 검사한다
	public boolean validateToken(String token) {
		try {
			// Jwt 형태를 Json이 아닌 java 형태로 쓰겠다
			Jwts.parserBuilder()
			// token이 만들어진 key로 되어있는지 확인
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			
			return true;
		} catch (SecurityException | MalformedJwtException e) {
			// Security 라이브러리에 오류가 있거나, JSON의 format이 잘못된 형식의 JWT가 들어왔을 때 예외
			// SignatureException이 포함되어 있음
			log.info("Invalid JWT Token", e);
		} catch (ExpiredJwtException e) {
			// 토큰의 유효기간이 만료된 경우의 예외
			log.info("Expired JWT Token", e);
		} catch (UnsupportedJwtException e) {
			// JWT의 형식을 지키지 않은 경우 (Header.Payload.Signature)
			log.info("Unsupported JWT Token", e);
		} catch (IllegalArgumentException e) {
			//	JWT토큰이 없을 때
			log.info("IllegalArgument JWT Token", e);
		} catch (Exception e) {
			log.info("JWT Token Error", e);
		}
		
		// 예외가 일어나면 인증되지 않았다, 쓸 수 없는 토큰이다
		return false;
		
	}
	
	public Authentication getAuthentication(String accessToken) {
		Claims claims = parseClaims(accessToken);	// Claims 통째로 들고오기(Object은 이유는 Claims 안에 숫자로 들어갈 수도 있기 때문에)
		Object roles = claims.get("auth");
		
		if(roles == null) {		// 회원가입 때부터 잘못됨.(ROLE_USER 등 아무것도 안 들어감)
			throw new CustomException("권한 정보가 없는 토큰입니다.");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		String[] rolesArray = roles.toString().split(",");
		
		Arrays.asList(rolesArray).forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));	// 권한이 있는 것들을 authorities에 담기
		});
		
		// Spring에서 기본적으로 지원해주는 UserDedatils임
		// new User(username, password, 권한) == PrincipalDetails
		// password는 공개되면 안 되기 때문에 공백이다
		UserDetails userDetails = new User(claims.getSubject(), "", authorities);
		// new UsernamePasswordAuthenticationToken(User객체, 자격 증명 객체(SSL 등 회사든 공공에서든 인증서가 필요할 때. https에서 증명할 때 쓰임), 권한)
		// return하면 업캐스팅이 돼서 Authentication 객체가 된다. UsernamePasswordAuthenticationToken를 타고 들어가면 Authentication을 implement 하고 있다.
		// 임시의 Authentication 객체가 생성
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}
	
	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(accessToken)
					.getBody();		// payLoad
		} catch (ExpiredJwtException e) {
			return e.getClaims();	// 형식상 넣어준 것. 어차피 그 전에 예외가 걸리면 안 넘어간다
		}
	}
	
}
