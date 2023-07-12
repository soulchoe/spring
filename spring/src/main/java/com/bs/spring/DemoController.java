package com.bs.spring;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.service.DemoService;

@Controller
public class DemoController {
	
	@Autowired
	private DemoService service;
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		return "demo/demo";
	}
	
	//메소드 선언하기
	//리턴값과 매개변수 알아보기
	//1.반환형
	//	1)String : ViewResolver에 의해 view화면을 출력해줌(기본적으로 가장 많이 사용>레스트방식에서는 아님)
	//	2)void : HttpServletResponse객체로 직접 응답메세지를 작성할 때 사용(doGet메소드와 완전 일치해짐)
	//	3)ModelAndView : 화면에 전달할 데이터와 view내용을 동시에 저장하는 객체. spring이 제공해줌. model은 request와 생명주기가 완전히 일치
	//	4)클래스타입 : 내가 만든 일반 객체. json으로 데이터를 반환할 때 많이 사용, Restful하게 서버를 구성했을 때 많이 씀
	//		*ResponseEntity<클래스>형식이 json으로 변환되어 들어가는것
	
	//2.매개변수
	//	1)HttpServletRequest : 서블릿처럼 쓸 수 있다
	//	2)HttpServeltResponse :역시 서블릿처럼 쓸 수 있다
	//	3)HttpSession : 역시.. session의 값을 가져와서 대입해줌
	//	4)java.util.Locale : 서버의 로케일 정보를 저장한 객체
	//	5)InputStream/Reader : 파일을 읽어올 때 사용하는 stream(클라이언트와 연결된)
	//	6)OutputStream/Writer : 파일을 보낼 때 사용하는 stream
	//	7)기본자료형 변수 : 클라이언트가 보낸 parameter데이터와 선언한 변수 이름이 동일하면 자동으로 매핑해준다
	//		->선언한 이름이 다를 경우 @RequestParam어노테이션으로 연결해줄 수도 있다
	//		@RequestParam은 매핑뿐 아니라 기본값설정, 필수값여부설정까지 가능하다
	//	8)클래스변수 : Command라고 함, parameter데이터를 필드에 넣어서 객체를 전달
	//		*파라미터의 이름과 필드명이 같은 데이터를 대입해 줌.
	//	9)java.util.Map : @RequestParam어노테이션과 같이 사용. parameter값을 map방식으로 저장 *단, 단일값에 대해서만 가능
	
	//	10)@RequestHeader와 기본자료형을 작성하면 header정보를 받을 수 있음
	//	11)@CookieValue(name값)와 기본자료형을 작성하면 Cookie에 저장된 값을 받을 수 있음
	
	//	12)Model : request와 비슷하게 데이터를 key-value 형식으로 저장할 수 있는 객체
	//	13)ModelAndView : model과 view를 동시에 저장하는 객체
	
	//메소드 어노테이션
	//@RresponseBody -> Rest방식으로 클래스를 json으로 전송할 때 
	//@RequestBody -> Json방식으로 전송된 파라미터를 클래스(객체)로 받을 때 사용
	//@GetMapping, @PostMapping, @DeleteMapping...
	
	//서블릿방식으로 매핑메소드 이용하기
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println(req);
		System.out.println(res);
		String devname=req.getParameter("devName");
		int devAge=Integer.parseInt(req.getParameter("devAge"));
		String devGender=req.getParameter("devGender");
		String devEmail=req.getParameter("devEmail");
		String[] devLang=req.getParameterValues("devLang");
		System.out.println(devname+devAge+devGender+devEmail);
		for(String l:devLang) {
			System.out.println(l);
		}
		
		Demo d=Demo.builder().devName(devname).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build();
		req.setAttribute("demo", d);
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out=res.getWriter();
//		out.print("<h2>"+devname+devAge+devGender+devEmail+"</h2>");
	}
	
	//1:1로 매칭해서 받기
	//매핑메소드의 매개변수에 파라미터로 전송되는 name과 동일환 이름의 변수를 선언
	//매개변수의 타입은 사용할 타입으로 설정 *변경이 가능해야 함
	//선언된 기본형(int, double, boolean,..) 매개변수는 모두 값을 가지고있어야함!
	//	:기본형으로 선언했다먄 프론트에서 반드시 값을 받아오도록 required처리 해줘야겠지?
	//매개변수가 클래스 참조형이고 값이 없으면 null을 넣어서 처리해줄수있는데 기본형에는 불가능하지
	@RequestMapping("demo/demo2.do")
	public String demo2(String devName, int devAge, String devGender, String devEmail, String[] devLang, String weight, Model model) {
		System.out.println(devName+devAge+devGender+devEmail+Arrays.toString(devLang));
		//페이지에 생성한 데이터를 전송하려면
		//Spring에서 데이터전송(저장)하는 객체가 있음 -> Model
		//Model에 데이터를 저장하기 ->model.addAttribute("key",value)
		Demo d=Demo.builder().devName(devName).devAge(devAge).devGender(devGender).devEmail(devEmail).devLang(devLang).build();
		model.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
	//기본 자료형 파라미터 데이터를 받을 때 @RequestParam어노테이션을 이용해서 옵션을 설정할 수 있다
	//defaultValue로 값이 들어오지 않을 때 대신 넣어줄 값을 설정할 수 있다
	//required설정으로 필수값을 지정할 수 있다 -> false설정이면 값이 안들어올때 걍 무시해버림
	@RequestMapping("/demo/demo3.do")
	public String requestParamUse(
			@RequestParam(value="devName", defaultValue="아무개")String name, 
			@RequestParam(value="devAge", defaultValue="10")int age, 
			@RequestParam(value="devGender")String gender, 
			@RequestParam(value="devEmail",required=false)String devEmail, 
			String[] devLang, Model model) {
		Demo d=Demo.builder().devName(name).devAge(age).devGender(gender).devEmail(devEmail).devLang(devLang).build();
		model.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
	
	//DTO/VO객체로 직접 파라미터 값 받기
	//매개변수로 전달되는 parameter이름과 동일한 필드를 갖는 객체를 선언함
	//*단, 클래스타입에 주의. date 받을 때는 sql.date로 하자
	@RequestMapping("/demo/demo4.do")
	public String commandMapping(Demo demo, Model m) {
		System.out.println(demo);
		m.addAttribute("demo", demo);
		return "demo/demoResult";
	}
	
	//Map으로 parameter데이터 받아오기
	//@RequestParam어노테이션 설정 Map
	//*단, Map은 다중값에 대해 처리 못하므로 따로 받아줘야함
	@RequestMapping("demo/demo5.do")
	public String mapMapping(@RequestParam Map param, String[] devLang, Model m) {
		System.out.println(param);
		param.put("devLang", devLang);
		m.addAttribute("demo",param);
		return "demo/demoResult";
	}
	
	//추가데이터 받아오기
	//Cookie, Header, Session
	//Cookie : @CookieValue(value="key") String data
	//	required=false이면 값이 안들어와도 에러나지 않도록 할수 있음, 반대도 가능
	//Header : @RequestHeader(value="헤더이름") String header
	//Session : @SessionAttribute(value="세션key값") String id
	@RequestMapping("/demo/demo6.do")
	public String extraData(@CookieValue(value="testData", required=false, defaultValue="break-time" )String data, 
			@RequestHeader(value="User-agent")String userAgent,
			@SessionAttribute(value="sessionId") String sessionId,
			@RequestHeader(value="Referer") String referer) {
		System.out.println("쿠키 : "+data);
		System.out.println("헤더 : "+userAgent);
		System.out.println("세션 : "+sessionId);
		System.out.println("이전페이지 : "+referer);
		return "index";
	}
	
	//ModelAndView객체를 이용해서 반환하기
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewReturn(Demo d, ModelAndView mv) {
		//ModelAndView는 view설정과 Model설정을 같이할 수 있는 객체
		//view : setViewName()메소드를 이용해서 저장
		//data : addObject("key",value)메소드 이용해서 저장
		mv.addObject("demo",d);
		mv.setViewName("demo/demoResult");
		return mv;
	}
	
	//자료형에 대해 반환하기 -> 데이터만 보낼때 사용 -> jackson라이브러리를 이용해서 처리
	//메소드 선언부에 @ResponseBody어노테이션 사용
	//Restful메소드를 구현했을 때 사용
	@RequestMapping("/demo/demo8.do")
	@ResponseBody
	public String dataReturn() {
		return "유병승 최주영 조장흠 최솔 조윤진";
	}
//	public List<String> dataReturn(){
//		return List.of("유병승","최주영","조장흠","최솔","조윤진");
//	}
	
	//Request요청 메소드(Get, Post)를 필터링하기
	//@RequestMapping에 속성값으로 메소드를 지정할 수 있다
	//@RequestMapping(value="url", method=RequestMethod.GET||RequestMethod.POST)
	@RequestMapping(value="/demo/demo9.do", method=RequestMethod.POST)
	public String methodCheck(Demo d, Model m) {
		m.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
//	//간편하게 사용할 수 있게 Mapping어노테이션을 지원
//	@GetMapping
//	@PostMapping
//	@DeleteMapping
//	@PutMapping
	@GetMapping("/demo/{no}")
	public String searchDemo(@PathVariable(value = "no") int no) {
	    System.out.println(no);
	    return "demo/demoResult";
	}
	
	
	@RequestMapping(value="/demo/insertDemo.do",method=RequestMethod.POST)
	public String insertDemo(Demo demo,Model m) {
		int result=service.insertDemo(demo);
		System.out.println(result);
		m.addAttribute("msg",result>0?"저장 성공":"저장 실패");
		m.addAttribute("loc","/demo/demo.do");
		//sendRedirect로 변경하는 방법!!!!!
		// : 주소를 변경하면서 이동해야할 때.
		//prefix redirect:요청할 주소(매핑주소)
		//*jsp직접호출은 안됨, 화면이 전부 WEB-INF아래 들어있자나
		//return "redirect:/";
		//		return "/demo/demo"; 이렇게 쓰면 새로고칠때 마다 계속 저장이 되니까
		return "common/msg";
	}
	
	@RequestMapping("/demo/selectDemoAll.do")
	public String selectDemoAll(Model m) {
		
		List<Demo> devs=service.selectDemoAll();
		m.addAttribute("devs",devs);
		return "demo/demoList";
	}
	
}
