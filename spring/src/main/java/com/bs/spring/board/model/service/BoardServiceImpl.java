package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;
@Service
public class BoardServiceImpl implements BoardService {

	//@Autowired
	private BoardDao dao;
	//@Autowired
	private SqlSession session;
	
	public BoardServiceImpl(BoardDao dao,SqlSession session) {
		this.dao=dao;
		this.session=session;
	}


	@Override
	public Board boardDetailByNo(int boardNo) {
		return dao.boardDetailByNo(session,boardNo);
	}

	@Override
	public List<Board> selectBoardList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dao.selectBoardList(session, param);
	}

	@Override
	public int insertBoard(Board b) {
		
		return dao.insertBoard(session, b);
	}

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount(session);
	}

}
