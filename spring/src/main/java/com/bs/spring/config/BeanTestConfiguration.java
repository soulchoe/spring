package com.bs.spring.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Department;
import com.bs.spring.beantest.Employee;

//클래스 방식으로 빈 등록해서 사용하기
//pojo클래스를 configuration으로 사용할 수 있음 ->@Configuration어노테이션 이용

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.bs.spring",//기본위치 설정가능
				includeFilters= {
						@ComponentScan.Filter(
								type=FilterType.REGEX,
								pattern= {"com.bs.spring.include.*"})},//어노테이션 표시가 없더라도 빈으로 등록할 내용
				excludeFilters= {})
//@Import() 다른 config를 가져와서 처리
public class BeanTestConfiguration {
	//springbeanconfiguration.xml과 동일한 기능
	//spring에서 사용할 bean을 자바코드로 등록할 수 있다 -> @Bean어노테이션이용
	//메소드 선언 방식으로 등록함
	@Bean
	@Order(1)//bean의 우선순위를 정할수 있으
	public Animal ani() {
		return Animal.builder().name("킼킼").age(5).height(52.3).build();
	}
	@Bean
	//등록된 빈에 특정 아이디값 부여하기
	@Qualifier("sol")
	public Employee getEmployee(@Qualifier("sal")Department d) {
		//매개변수로 넣으면 오토와이어드 되어 같은 타입을 찾아 넣어줌(4이상)->자동으로 안되면 매개변수 괄호안에 @Autowired넣어주면 됨
		//이때 Qualifier를 사용해서 지정해줄 수 있음
		return Employee.builder()
				.name("최솔")
				.age(27)
				.address("경기도 안양시")
				.salary(200)
				.dept(d)
				.build();
	}
	
	@Bean
	public Department sal() {
		return Department.builder()
				.deptCode(2L)
				.deptTitle("영업부")
				.deptLocation("서울")
				.build();
	}
	
	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource source=new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("spring");
		source.setPassword("spring");
		return source;
	}
	
//	@Bean
//	public Gson getGson() {
//		return new Gson;
//	}
}
