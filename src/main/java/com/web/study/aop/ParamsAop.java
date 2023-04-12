package com.web.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ParamsAop {
	
	@Pointcut("@annotation(com.web.study.aop.annotation.ParamsAspect)")
	private void pointCut() {}
	
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StringBuilder builder = new StringBuilder();	// 문자열을 만들어줌
		
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		String[] parameternames = codeSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();		// 매개 변수로 전달된 "값"들

		for(int i = 0; i < parameternames.length; i++) {
			if(i != 0) {
				builder.append(", ");
			}
			builder.append(parameternames[i] + ": " + args[i]);
		}
		
		log.info("[ Params ] >>> {}", builder.toString());
		
		return joinPoint.proceed();		// 전처리, 후처리 둘 다 가능해야 하는 경우에는 위에 빼줌.
	}
	
}
