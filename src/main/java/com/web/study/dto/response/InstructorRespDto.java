package com.web.study.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter		// Getter가 있어야 Jacson이 가져올 수 있다.
public class InstructorRespDto {
	private int id;
	private String name;
	private LocalDate birthDate;
}
