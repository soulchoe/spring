package com.bs.spring.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;
@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(SqlSessionTemplate session, Member m) {
		// TODO Auto-generated method stub
		return session.insert("member.insertMember",m);
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, Map param) {
		// TODO Auto-generated method stub
		return session.selectOne("member.selectById",param);
	}

//	@Override
//	public int login(SqlSessionTemplate session, Map param) {
//		// TODO Auto-generated method stub
//		return session.selectOne("member.login", param);
//	}

}
