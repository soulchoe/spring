package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;
@Slf4j
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
	//@Transactional //어노테이션 방식으로 트랜젝션 처리하기
	public int insertBoard(Board b) {
		//2개의 insert문을 실행 ->board/attatchment
		log.info("실행전 : {}",b.getBoardNo());
		int result=dao.insertBoard(session, b);
		log.info("실행후 : {}",b.getBoardNo());
		if(result>0) {
			if(b.getFile().size()>0) {
				for(Attachment a:b.getFile()) {
					a.setBoardNo(b.getBoardNo());
					result+=dao.insertAttachment(session,a);//첨부파일의 갯수=result 이 되도록
//					if(result!=1) throw new RuntimeException("첨부파일의 내용이 이상합니다"); 개별 첨부파일 업로드 확인
				}
			}
			
		}
		
//		//rollback처리를 원한다면 RuntimeException을 발생시키면 됨
		if(result!=b.getFile().size()+1) throw new RuntimeException("첨부파일의 내용이 이상합니다"); //전체 첨부파일+보드 저장여부 확인
		//if(result!=0) throw new RuntimeException("xml방식에서 발생시키는 에러");
		return result;
	}

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount(session);
	}

}
