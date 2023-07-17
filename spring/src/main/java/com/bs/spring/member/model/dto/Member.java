package com.bs.spring.member.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@NotEmpty(message = "아이디는 반드시 입력해야합니다")
	@Size(min=4,message = "아이디는 네글자 이상이어야 합니다")
	private String userId;
	@Pattern(regexp="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()]{8,}", message = "대소문자와 특수문자(~!@#$%^&*())로 8자이상 입력하세요")
	private String password;

	private String userName;
	
	private String gender;
	@Min(14)@Max(150)
	private int age;
	@Email(message = "이메일 형식을 확인해주세요")
	private String email;
	@NotEmpty(message = "전화번호는 필수입력값입니다.")
	private String phone;
	
	private String address;
	
	private String[] hobby;
	
	private Date enrollDate;
}
