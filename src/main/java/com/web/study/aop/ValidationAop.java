package com.web.study.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

import com.web.study.exception.CustomException;

@Aspect
@Component
public class ValidationAop {

	@Pointcut("@annotation(com.web.study.aop.annotation.ValidAspect)")
	private void pointCut() {}
	
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		BeanPropertyBindingResult bindingResult = null;
		
		for(Object obj : joinPoint.getArgs()) {
			if(obj.getClass() == BeanPropertyBindingResult.class) {
				bindingResult = (BeanPropertyBindingResult) obj;
			}
		}
		
		if(bindingResult.hasErrors()) {		// bindingResult에 에러들을 정리해서 자동으로 들어감.
			Map<String, String> errorMap = new HashMap<>();
			
			bindingResult.getFieldErrors().forEach(error -> {		// 오류들을 하나씩 꺼냄. 그래서 List
				errorMap.put(error.getField(), error.getDefaultMessage());
			});
			throw new CustomException("유효성 검사 실패", errorMap);
		}
		
		return joinPoint.proceed();
	}
}
