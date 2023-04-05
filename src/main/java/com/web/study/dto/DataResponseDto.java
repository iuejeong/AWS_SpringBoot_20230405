package com.web.study.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data		// JackSon한테 넘겨주기 위해 필요하다. Get만 있어도 됨.
public class DataResponseDto<T> extends ResponseDto {

	private final T data;
	
	public DataResponseDto(T data) {
		super(true, HttpStatus.OK.value(), "Successfully");
		this.data = data;
	}
	
	public DataResponseDto(T data, String message) {
		super(true, HttpStatus.OK.value(), message);
		this.data = data;
	}
	
	public static <T> DataResponseDto<T> of(T data) {
		return new DataResponseDto<T>(data);
	}
	
	public static <T> DataResponseDto<T> of(T data, String message) {
		return new DataResponseDto<T>(data, message);
	}
	
	public static <T> DataResponseDto<T> empty() {
		return new DataResponseDto<T>(null);
	}
	
}
