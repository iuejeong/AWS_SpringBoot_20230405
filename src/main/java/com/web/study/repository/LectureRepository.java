package com.web.study.repository;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecture;

@Mapper		// xml file로 대체
public interface LectureRepository {
	
	public int registe(Lecture lecture);

}
