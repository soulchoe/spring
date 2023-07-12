package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//spring에서 mvc중 Controller역할을 하는 클래스에 적용한다
//springbean으로 등록된다
public class MainController {
	//@Controller로 등록된 클래스는 클라이언트가 요펑한 서비스를 진행하는 메소드(매핑매소드)를 선언한다
	//요청URL주소와 연결되는 메서드를 말한다
	
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
		
		
		
		return "index";
	}
}
