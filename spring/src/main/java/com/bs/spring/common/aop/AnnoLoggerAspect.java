package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component//기본 빈으로 등록
@Aspect//aspect로서의 역할 부여 ->pointcut, advisor설정 필요
public class AnnoLoggerAspect {
	//pointcut 설정
	@Pointcut("within(com.bs.spring.member..*)")
	public void loggerTest() {}
	
	//advisor 설정
	@Before("loggerTest()")
	public void loggerBefore(JoinPoint jp) {
		log.debug("======annotation aop======");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("==========================");
	}
	
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void memoLogger() {}
	
	@After("memoLogger()")
	public void afterLogger(JoinPoint jp) {
		log.debug("======annotation aop after======");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("=============after=============");
	}
	
	//메소드 실행 전후에 특정 로직을 실행할때 :Around
	//전에 값을 저장해서 후에서 사용하는등의 유지, 연동에 유리
	@Around("execution(* com.bs.spring..*DaoImpl(..))")
	public Object daoLogger(ProceedingJoinPoint join) throws Throwable{
		//전후 로직은 한번에 설정할 수 있다는 장점
		//전과 후를 구분하는 구문은 ProceedingJoinPoint클래스가 제공하는 proceed()메소드 이용
		//
		//proceed()메소드는 Object를 반환한다
		//메소드 실행시간 체크하기
		StopWatch stop=new StopWatch();
		stop.start();
		log.debug("==== around logger dao before =====");
		log.debug("----------전처리 내용 구현-------------");
		log.debug("================================");
		Signature sig=join.getSignature();
		String classMethod=sig.getDeclaringType().getName()+sig.getClass();
		Object obj=join.proceed();
		stop.stop();
		log.debug("==== around logger dao after =====");
		log.debug("----------후처리 내용 구현-------------");
		log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
		log.debug("================================");
		return obj;
	}
	
	@AfterThrowing(pointcut = "loggerTest()", throwing ="e")
	public void afterThrowingLogger(JoinPoint jp, Throwable e) {
		log.debug("에러발생!!!");
		Signature sig=jp.getSignature();
		log.debug("{}",sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("{}",e.getMessage());
		StackTraceElement[] stacktrace=e.getStackTrace();
		for(StackTraceElement element:stacktrace) {
			log.debug("{}",element);
		}
	}
	
}
