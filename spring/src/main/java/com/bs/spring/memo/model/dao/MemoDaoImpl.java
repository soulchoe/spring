package com.bs.spring.memo.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.dto.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public int insertMemo(SqlSession session, Memo m) {
		return session.insert("memo.insertMemo", m);
	}

	@Override
	public List<Memo> selectAllMemo(SqlSession session) {
		return session.selectList("memo.selectAllMemo");
	}

}
