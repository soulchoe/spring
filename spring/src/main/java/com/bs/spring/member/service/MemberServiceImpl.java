package com.bs.spring.member.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return dao.insertMember(session, m);
	}

	@Override
	public Member selectMemberById(Map param) {
		// TODO Auto-generated method stub
		return dao.selectMemberById(session,param);
	}

//	@Override
//	public int login(Map param) {
//		// TODO Auto-generated method stub
//		return dao.login(session,param);
//	}

}
