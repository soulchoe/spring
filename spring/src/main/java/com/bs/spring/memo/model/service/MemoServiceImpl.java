package com.bs.spring.memo.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	//@Autowired
	private MemoDao dao;
	//@Autowired
	private SqlSession session;
	public MemoServiceImpl(MemoDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session, m);
	}
	@Override
	public List<Memo> selectAllMemo(){
		return dao.selectAllMemo(session);
	}
}
