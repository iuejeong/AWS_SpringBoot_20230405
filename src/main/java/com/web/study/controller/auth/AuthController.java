package com.web.study.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.auth.LoginReqDto;
import com.web.study.dto.auth.RegisteUserReqDto;
import com.web.study.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	// 회원가입
	@PostMapping("/auth/register")
	public ResponseEntity<? extends ResponseDto> registe(@RequestBody RegisteUserReqDto registeUserReqDto) {
		authService.duplicatedUsername(registeUserReqDto);
		authService.registeUser(registeUserReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	// 로그인
	@PostMapping("/auth/login")
	public ResponseEntity<? extends ResponseDto> login(@RequestBody LoginReqDto loginReqDto) {
		
		return ResponseEntity.ok().body(DataResponseDto.of(authService.login(loginReqDto)));	// 토큰 만든 것을 응답
	}
	
}
