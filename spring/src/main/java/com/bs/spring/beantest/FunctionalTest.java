package com.bs.spring.beantest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//pojo클래스를 생성하고, 선언부에서 빈으로 등록할 수 있음
//@Component, @Controller, @Service(), @Repository
//어노테이션을 이용해소 빈으로 등록
//@Component : 기본 String bean으로 등록할때 ㅛㅏ용
//@Controller, @Service(), @Repository : mvc패턴에 의해 저장된..어쩌구
@Component
public class FunctionalTest {
//	private String name;
	
	private String name="test";
	
//	public FunctionalTest(@Qualifier ("bbo") Animal a) {
//		
//		this.a=a;
//	}
	private Animal a;
	@Autowired
//	setter를 이용한 DI
	public void setA(@Qualifier ("dog") Animal a) {
		this.a=a;
	}
	public Animal getA() {
		return this.a;
	}
}

