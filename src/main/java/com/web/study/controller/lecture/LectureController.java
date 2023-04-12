package com.web.study.controller.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.service.LectureService;

import lombok.RequiredArgsConstructor;

/**
 * 학생 정보
 * id, name, birth_date
 * 
 * 수강 정보
 * id, lecture_id(강의), student_id(학생), register_date(등록일)
 * 
 * 강의 정보
 * id, lecture_name(강의명), price(가격), instructor_id(강사)
 * 
 * 강사 정보
 * id, name, birth_date
 */

@RestController
@RequiredArgsConstructor
public class LectureController {
	
	private final LectureService lectureService;

	// Create
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody LectureReqDto lectureReqDto) {
		lectureService.registeLecture(lectureReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	@GetMapping("/search/lectures")
	public ResponseEntity<? extends ResponseDto> searchLecture(int type, String searchValue) {
		System.out.println(type);
		System.out.println(searchValue);
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.searchLecture(type, searchValue)));
	}
	
	// Read
	public ResponseEntity<? extends ResponseDto> get() {
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	// Update
	public ResponseEntity<? extends ResponseDto> modify() {
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	// Delete
	public ResponseEntity<? extends ResponseDto> remove() {
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
}
