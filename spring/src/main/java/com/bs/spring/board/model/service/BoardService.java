package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

public interface BoardService {

	List<Board> selectBoardList(Map<String,Object> param);

	Board boardDetailByNo(int boardNo);
	
	int insertBoard(Board b);
	
	int selectBoardCount();
	

}
