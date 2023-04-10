package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.student.StudentReqDto;
import com.web.study.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {
	
	// StudentServiceImpl도 가능하지만 나중에 계속해서 수정 작업이 생길 경우 다 뜯어고쳐야 하는 문제가 생긴다.
	// 하지만 interface를 쓰면 그 부분만 고치면 되기 때문에 작업에 수월하다.
	private final StudentService studentService;

	// @RequestBody => 클라이언트 -> 서버 데이터 전송(JSON)
	// JSON의 형태 -> 객체
	// 클라이언트에서 서버로 데이터 전송을 할 때 JSON 객체를 생성을 하고 매개변수로 받는다.
	@PostMapping("/student")
	public ResponseEntity<? extends ResponseDto> registeStudent(@RequestBody StudentReqDto studentReqDto) {
		studentService.registeStudent(studentReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	@GetMapping("/students")
	public ResponseEntity<? extends ResponseDto> getStudents() {
		
		return ResponseEntity.ok().body(DataResponseDto.of(studentService.getStudentAll()));
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<? extends ResponseDto> getStudents(@PathVariable int id) {
		
		return ResponseEntity.ok().body(DataResponseDto.of(studentService.findStudentById(id)));
	}
	
}
