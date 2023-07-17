package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.dto.Board;

public interface BoardDao {

	List<Board> selectBoardList(SqlSession session, Map<String,Object> param);

	Board boardDetailByNo(SqlSession session, int boardNo);

	int insertBoard(SqlSession session, Board b);
	
	int selectBoardCount(SqlSession session);

}
