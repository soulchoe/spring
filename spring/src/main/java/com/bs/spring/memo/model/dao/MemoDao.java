package com.bs.spring.memo.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoDao {

	int insertMemo(SqlSession session, Memo m);

	List<Memo> selectAllMemo(SqlSession session);

}
