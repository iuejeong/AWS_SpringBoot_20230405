package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.web.study.IocAndDi.TestC;

@Configuration
public class BeanConfig {
	// 라이브러리에서 가져온 것은 입력이 안 된다. 그래서 그것을 써야 할 경우에는 Bean을 이용해서 생성해준 다음에 사용한다.
	// 우리가 직접 만들어서 사용한 것은 Bean을 쓸 필요가 없다.
	// Repository, Service 등에서는 만들 필요 없다. Configuration에다 만들어주면 된다.
	@Bean
	public TestC testC() {
		return new TestC();
	}
	
}
