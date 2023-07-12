package com.bs.spring.demo.model.dto;

import java.sql.Date;
//프론트에서 넘어오는 string타입의 데이터를 date로 자동 변환해주려면 sql.date를 임포트 해야한다!

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Demo {
	private int devNo;
	private String devName;
	private int devAge;
	private String devGender;
	private String devEmail;
	private String[] devLang;
//	private Date bDay;
}
