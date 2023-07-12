package com.bs.spring;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Employee;
import com.bs.spring.beantest.FunctionalTest;
import com.bs.spring.include.TargetComponent;

@Controller
public class HomeController {
	
	//springbean으로 등록된 객체는  필드로 가져와 사용할 수 있음
	@Autowired
	@Qualifier("dog")
	private Animal a;
	@Autowired
	@Qualifier("bbo")
	private Animal b;
//	필드명과 빈즈의 아이디가 일치하면 한 클래스에 대해 여러개를 사용할 수 있다
//	중복된 타입이 있는 경우 @Qualifier어노테이션을 추가적으로 이용해서 특정 빈을 선택할 수 있다
	
	//springbean으로 등록되지 않은 객체를 autowired하면..? -> 에러발생
	//=>(required=false)옵션을 줘서 있으면 넣고 없으면 넣지마
	@Autowired(required=false)
	private Employee emp;
	
	@Autowired
	private Employee emp2;
	
	//자바로 등록한 빈 가져오기
	@Autowired
	@Qualifier("ani")
	private Animal c;
	
	@Autowired
	@Qualifier("sol")
	private Employee sol;
	
	@Autowired
	List<Animal> animals;
	//해당 클래스로 등록된 객체들을 리스트 타입으로 만들어줄 수도 있음
	
	@Autowired
	private TargetComponent tc;
	
	//어노테이션으로 빈 등록
	@Autowired
	private FunctionalTest ft;
	//basepacjage외부에 있는 컴포넌트
	@Autowired
	private Test test;
	
	@RequestMapping("/test")//@WebServlet("연결주소")와 같은 역할
	public String home() {//doGet메소드 역할
		System.out.println(a);
		System.out.println(b);
		System.out.println(emp);
		System.out.println(emp2);
		System.out.println(c);
		System.out.println(sol);
		animals.forEach(System.out::println);
		System.out.println(tc);
		System.out.println(test);
		
		//spring에서 파일을 불러올 수 있는 Resource객체를 제공
		Resource resource=new ClassPathResource("mydata.properties");
		try {
			Properties prop=PropertiesLoaderUtils.loadProperties(resource);
			System.out.println(prop);
			resource=new FileSystemResource("C:\\Users\\GDJ\\Desktop\\goodee\\spring\\work\\spring\\src\\main\\resources\\test.txt");
			Files.lines(Paths.get(resource.getURI()),Charset.forName("UTF-8")).forEach(System.out::println);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		return "index";
	}
}
