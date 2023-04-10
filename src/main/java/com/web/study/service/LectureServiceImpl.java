package com.web.study.service;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService{

	// final => 상수: 초기화가 무조건 일어나야 함.
	// 생성자를 만들어줌으로써 값을 무조건 넣어야하는 강제성이 생긴다.
	private final LectureRepository lectureRepository;
	
	@Override
	public void registeLecture(LectureReqDto lectureReqDto) {
		// Client와 Controller 사이: Dto
		// Service와 DB 사이: Entity
		// DTO -> ENTITY 변환
		Lecture lecture = lectureReqDto.toEntity();
		System.out.println("변환: " + lecture);
		lectureRepository.registe(lecture);		// xml에 매개 변수 전달
	}

}
