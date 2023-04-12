package com.web.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.web.study.aop.annotation.CheckNameAspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TimerAop {
	
//	private final Logger logger = LogManager.getLogger(TimerAop.class);		어노테이션을 달아줘서 필요가 없어짐
	
	
	// 접근지정자 public은 생략 가능!
	// 첫 번째 * = return 반환 type, 그 뒤에는 쭉 경로임
	// ..을 찍어버리면 하위의 모든 것을 의미함. 아래에서는 com.web.study 안에 있는 모든 클래스와 모든 메서드를 실행하겠다는 의미
	@Pointcut("execution(* com.web.study..*.*(int))")
	private void pointCut() {}
	
	@Pointcut("@annotation(com.web.study.aop.annotation.TimerAspect)")
	private void annotationPointCut() {}
	
	// around가 advice라고 생각하면 됨
	@Around("annotationPointCut()&&pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
	
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// 전처리
		Object logic = joinPoint.proceed();		// proceed = 메소드 호출, pointCut을 이용해서 메소드를 정할 것임.
//		System.out.println(logic);				// return으로 넘겨진 값들을 다 보여줌, 이렇게 데이터 확인을 위해 ToString을 해주는 것이 중요함!
		// 후처리
		
		stopWatch.stop();
		log.info("[ Time ] >>> {}.{}: {}초",
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(),
				stopWatch.getTotalTimeSeconds());
//		System.out.println(joinPoint.getSignature().getDeclaringTypeName());	// 클래스명
//		System.out.println(joinPoint.getSignature().getName());					// 메소드명
//		System.out.println("메소드 실행 시간: " + stopWatch.getTotalTimeSeconds() + "초");
		return logic;
	}
}
