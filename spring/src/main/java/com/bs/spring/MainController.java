package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//spring에서 mvc중 Controller역할을 하는 클래스에 적용한다
//springbean으로 등록된다
public class MainController {
	//@Controller로 등록된 클래스는 클라이언트가 요펑한 서비스를 진행하는 메소드(매핑매소드)를 선언한다
	//요청URL주소와 연결되는 메서드를 말한다
	
	//log를 출력하기위한 Logger가져오기(대개 필드로 선언을 한다)
	private static final Logger logger=LoggerFactory.getLogger(MainController.class);
	
	
	//@RequestMapping어노테이션을 매핑메소드 선언부에 선언을 한다
	//Controller에 선언된 메소드는 일반적으로 스트링값을 반환한다(view선택해서 출력시킬때)
	@RequestMapping("/")
	public String main(HttpServletRequest req, HttpSession session, HttpServletResponse res) {
		//반환하는 값은 viewResolver bean이 받아서 처리함
		//등록된 InternalResourceResolver bean은 반환된 문자열에
		//자신에게 등록된 prefix & suffix를 붙여서 내부에서 화면을 찾는다
		// /WEB-INF/views/리턴값.jsp
		//RequestDispatcher( "/WEB-INF/views/리턴값.jsp" ).forward
		
		Cookie c=new Cookie("testData","cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		
		session.setAttribute("sessionId", "admin");
		
		//log4j를 이용해서 로그를 출력하기
		//slf4j에서 제공하는 Logger인터페이스를 구현한 클래스를 이용함
		//LoggerFactory클래스에 static메소드인 getLogger(logger를 가져오는 클래스 지정);
		
		//로크를 출력할 때는 Logger가 제공하는 메소드를 이용한다
		//	1.debug(): 개발시 사용하는 로그를 출력할 때 사용
		//	2.info(): 프로그램 실행하는 정보를 출력할 때 사용
		//	3.warm(): 잘못된 사용을 했을 때 출력해 줌.(프로그램이 멈출정도의 오류는 아님)
		//	4.error(): 예외가 발생하거나, 실행 불가능한 기능에 대한 로그를 출력할 때 사용
		//*메소드의 매개변수는 기본적으로 String만 가능, 객체나 다른 데이터를 출력하려면 ("{}",출력변수)
		
		//level
		//debug -> info -> warn -> error -> fatal
		//출력될 로그를 최소한의 단계를 저장해 줌으로서 그 단계 이상의 로그를 받아볼 수 있음
		
		//로그 출력하기
		logger.debug("debug내용 출력하기");
		logger.info("info내용 출력하기");
		logger.warn("warn내용 출력하기");
		logger.error("error내용 출력하기");
		//log4j.xml에서 표시할 로그의 단계를 설정할 수 있음
		
		return "index";
	}
}
