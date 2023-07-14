package com.bs.spring.memo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.model.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {
//	@Autowired
//	private MemoService service;
	private MemoService service;//4?5?버전이후로는 이렇게
	
	public MemoController(MemoService service) {//4?5?버전이후로는 이렇게
		this.service=service;
	}

	@RequestMapping("/selectMemoAll.do")
	public String memoPage(Model model) {
		List<Memo> memos=service.selectAllMemo();
		model.addAttribute("memos",memos);
		return "/memo/memoList";
	}
	//@RequestMapping(value="/insertMemo.do", method=RequestMethod.POST)
	@PostMapping("/insertMemo.do")
	public String insertMemo(Memo m) {
		int result=service.insertMemo(m);
		if(result==0) {
			
			
			return "common/msg";
			
		}
		return "redirect:/memo/selectMemoAll.do";
	}
	
}
