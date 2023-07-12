package com.bs.spring.beantest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private String name;
	private int age;
	private String address;
	private int salary;
	private Department dept;
	
	public Employee(Department dept) {
		this.dept=dept;
	}
	
	public void initialMethod() {
		System.out.println(this.getClass().getName()+"클래스 생성했다~");
	}
	public void destroyMethod() {
		System.out.println(this.getClass().getName()+"객체 소멸..");
	}
}
