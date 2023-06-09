package com.web.study.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.UserAdditionDto;

class UserStore {
	public static Map<Integer, UserAdditionDto> userMap = new HashMap<>();
}

@RestController
public class UserRestController {
	
	@PostMapping("/api/user/addition")
	public ResponseEntity<? extends ResponseDto> addUser(@RequestBody UserAdditionDto userAdditionDto) {
		Map<Integer, UserAdditionDto> userMap = UserStore.userMap;
		int maxKey = 0;
		if(!userMap.keySet().isEmpty()) {
			maxKey = Collections.max(userMap.keySet());		// collections에서 max 값을 꺼내줌
		}
		userMap.put(maxKey + 1 , userAdditionDto);

		return ResponseEntity.ok().body(DataResponseDto.of(userAdditionDto));	
	}
	
	@GetMapping("/api/user/{id}")
	public ResponseEntity<? extends ResponseDto> getUser(@PathVariable int id) {
		
		if(UserStore.userMap.get(id) == null) {
			try {
				throw new RuntimeException("존재하지 않는 id입니다.");
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, e));
			}
		}
		return ResponseEntity.ok().body(DataResponseDto.of(UserStore.userMap.get(id)));
	}
	
	@GetMapping("/api/users")
	public ResponseEntity<? extends ResponseDto> getUsers() {
		
		return ResponseEntity.ok().body(DataResponseDto.of(UserStore.userMap.values()));
	}
	
}
