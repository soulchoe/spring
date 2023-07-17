package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;
@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/boardList.do")
	public String selectBoardList(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage ,Model m) {
		List<Board> list=service.selectBoardList(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectBoardCount();
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "boardList.do"));
		// /spring/board/baordList.do로 상대경로로 써줘도 됨
		m.addAttribute("totalData","totalData");
		m.addAttribute("list",list);
		
		return "board/boardList";
	}
	
	@RequestMapping("/boardDetail.do")
	public String boardDetail(int boardNo, Model m) {
		Board b=service.boardDetailByNo(boardNo);
		m.addAttribute("b",b);
		return "board/boardDetail";//보드번호를 매개변수로 받아와야해
	}
}
