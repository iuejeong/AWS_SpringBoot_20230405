package com.web.study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;

import lombok.Data;

@Data
class Params {
	private int age;
	private String name;
}

@RestController
public class BasicRestController {

	// GET 요청의 param을 처리하는 방법
	@GetMapping("/read")
	public ResponseEntity<? extends ResponseDto> read(Params params) {	// key값이 변수명이 되면 RequestParam을 생략할 수 있다.
		
		String userInfo = params.getName() + "(" + params.getAge() + ")";
		
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));
	}
	
}
