package com.web.study.IocAndDi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IocTest2 {
	
	@Qualifier("testC")
	@Autowired
	private Test test;
	
	// 외부(TestA, B, C)에서 의존성을 주입한다.
//	public IocTest(Test test) {
//		this.test = test;
//	}
	
	public void run() {
		test.printTest();
		System.out.println("IocTest2 출력!");
	}
	
}
