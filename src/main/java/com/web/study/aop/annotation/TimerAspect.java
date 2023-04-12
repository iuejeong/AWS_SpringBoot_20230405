package com.web.study.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)	// 해당 메소드가 실행이 되면 어노테이션을 적용시킴.
@Target({ElementType.METHOD})	// 메소드에 다는 어노테이션. class에다 하고 싶으면 ElementType을 TYPE으로, 변수에다 하고 싶으면 Field 등
public @interface TimerAspect {

}
