package com.bs.spring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")//매핑주소의 공통적인 요소들은 위에 쓸수있다 
@SessionAttributes({"loginMember"})
@Slf4j//롬복에서 제공하는 기능. 자동으로 로거만들어줌
public class MemberController {
	
	@Autowired
	private MemberService service;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/enrollMember.do")
	public String enrollform() {
		
		return "/member/enrollMember";
	}
	
//	@RequestMapping(value="/member/insertMember.do",method=RequestMethod.POST)
	@PostMapping("/insertMember.do")
	public String insertMember(Member m, Model model) {
		
		//패스워드를 암호화해서 처리하자
		String oriPassword=m.getPassword();
		//System.out.println(oriPassword);
		log.debug(oriPassword);
		String encodePassword=passwordEncoder.encode(oriPassword);
		log.debug(encodePassword);
		//System.out.println(encodePassword);
		m.setPassword(encodePassword);
		
		int result=service.insertMember(m);
		String msg,loc;
		if(result>0) {
			msg="회원가입 성공";
			loc="/";
		}else {
			msg="회원가입 실패";
			loc="/member/enrollMember";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		return "common/msg";
		
	}
	@RequestMapping("/login.do")
	public String login(@RequestParam Map param, Model model) {

		Member m=service.selectMemberById(param);

		//암호화된 비밀번호 값을 비교하기 위해서는 BCryptPasswordEncoder가 제공하는 메소드를 이용해야한다
		//passwordEncoder.matches(원래 비번, 암호화된 비번)
		if(m!=null&&passwordEncoder.matches((String)param.get("password"), m.getPassword())) {
		//&&m.getPassword().equals(param.get("password"))) {
			//로그인 성공
			//session.setAttribute("loginMember", m);
			model.addAttribute("loginMember",m);
		}else {
			model.addAttribute("msg","아이디와 비밀번호를 확인해주세요");
			model.addAttribute("loc","/");
			return "common/msg";
		}
		return "redirect:/";
	}
//	@RequestMapping("/logout.do")
//	//public String logout(HttpSession session) {
//		if(session!=null) session.invalidate();
////		session.removeAttribute("loginMember");
//		return "redirect:/";
//	}
	
	//@SessionAttributes로 등록된 내용 삭제하기
	// : SessionStatus객체를 이용해서 삭제
	@RequestMapping("/logout.do")
	public String logout(SessionStatus status) {
		if(!status.isComplete()) status.setComplete();
		return "redirect:/";
	}
	
	@RequestMapping("/mypage.do")
	public String mypage(String userId, Model m) {
		m.addAttribute("member",service.selectMemberById(Map.of("userId",userId)));
		return "/member/mypage";
	}
}
