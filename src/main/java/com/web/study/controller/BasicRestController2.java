package com.web.study.controller;

import org.springframework.http.ResponseEntity;

import com.web.study.dto.ResponseDto;

import lombok.Data;

@Data
class User {
	private String username;
	private String password;
	private String name;
	private String email;
}

public class BasicRestController2 {
	
	public ResponseEntity<? extends ResponseDto> read(User params) {
		return null;
	}
	
}
