package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Board;

public interface BoardService {

	List<Board> selectBoardList(Map<String,Object> param);

	Board boardDetailByNo(int boardNo);
	
	int insertBoard(Board b);
	
	int selectBoardCount();

}
