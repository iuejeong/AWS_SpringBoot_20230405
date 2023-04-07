package com.web.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
//		iocAndDiTest();
	}

	// Di 설명을 위해 사용한 것
//	public static void iocAndDiTest() {
//		// Inversion Of Control	(개발을 개발자가 안 하고 Spring이 함. 프로그램이 우리가 만든 것을 제어한다는 의미)
//		IocTest iocTest = new IocTest(new TestC());
//		iocTest.run();
//	}	
	
}
