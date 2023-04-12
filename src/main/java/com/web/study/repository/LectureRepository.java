package com.web.study.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecture;

@Mapper		// xml file로 대체
public interface LectureRepository {
	
	// 성공에 대한 건수이기 때문에 int다.
	public int registe(Lecture lecture);
	public List<Lecture> searchLecture(Map<String, Object> parameterMap);
}
