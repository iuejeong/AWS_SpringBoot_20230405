package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BasicTestDto;

import lombok.Data;

//@Data
//class Params {
//	private int age;
//	private String name;
//}

@RestController
public class BasicRestController {

	// GET 요청의 param을 처리하는 방법
	@GetMapping("/read")
	public ResponseEntity<? extends ResponseDto> read(BasicTestDto basicTestDto) {	// key값이 변수명이 되면 RequestParam을 생략할 수 있다.
		
		String userInfo = basicTestDto.getName() + "(" + basicTestDto.getAge() + ")";
		
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));
	}
	
	@GetMapping("/read2/{id}")
	public ResponseEntity<? extends ResponseDto> read2(@PathVariable int id) {
		Map<Integer, String> userMap = new HashMap<>();
		
		userMap.put(1, "김준일");
		userMap.put(2, "김준이");
		userMap.put(3, "김준삼");
		userMap.put(4, "김준사");
		userMap.put(5, "김준오");
		
		System.out.println(userMap.get(id));
		
		return ResponseEntity.ok().body(DataResponseDto.of(userMap.get(id)));
	}
	
	// form으로 받을 때는 @RequestBody를 빼고 받아줌.
	// Post요청의 데이터 처리
	@PostMapping("/create")
	public ResponseEntity<? extends ResponseDto> create(BasicTestDto basicTestDto) {		// JSON 데이터를 받으려면 RequestBody를 붙여준다.
		System.out.println("데이터 추가");
		
		// ok = 일반적으로 성공했다는 의미를 표시.
		// created = 게시글 작성 성공, 로그인 성공 등 쓰임 이러한 성공이 이루어졌을 때 그 페이지로 넘어가기 위해 created에 url을 넣어준다
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto));
	}
	
}
