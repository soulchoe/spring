package com.bs.spring.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.DemoController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//반환형이 boolean으로, true반환하면 매핑메소드가 실행되고, false면 매핑메소드를 실행하지 않는다
		//이를 이용해 특정 요청을 차단할 수 있다
		log.debug("------인터셉터 프리핸들 실행-----");
		log.debug(request.getRequestURI());
		Map params=request.getParameterMap();
		for(Object key:params.keySet()) {
			System.out.println(key);
		}
		log.debug("-------------------------");
		//response.sendRedirect(request.getContextPath());
		//(2)메인으로 보내버리기 (인터셉터를 /demo/로 시작하는 모든 주소에 적용해 둔 상태 : servlet-context.xml내부)
		
		//handler : 실행되는 controller클래스, 실행되는 메소드 확인
		HandlerMethod hm=(HandlerMethod)handler;
		log.debug("{}",hm.getBean());
		DemoController demo=(DemoController)hm.getBean();
		
		log.debug("{}",hm.getMethod());
		Method m=hm.getMethod();
		//m.invoke(m,null);
		return true;
		
		//return false;
		//(1)차단후
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//메소드가 끝나면 바로 실행
		log.debug("-----인터셉터 포스트 핸들 실행-----");
		log.debug("{}",modelAndView.getViewName());
		Map modelData=modelAndView.getModel();
		log.debug("{}",modelData);
		log.debug("---------------------------");
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception{
		log.debug("---- 응답 후 인터셉터 실행 ----");
		log.debug("요청주소{} ",request.getRequestURI());
		log.debug("에러 메세지{} ",ex!=null?ex.getMessage():"응답성공");
		log.debug("---------------------------");
	}
	
}
