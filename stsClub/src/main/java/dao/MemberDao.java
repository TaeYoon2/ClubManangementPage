package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MemberBean;


@Repository
public class MemberDao {
	@Autowired
	private SqlSession sqlSession;//mybatis 쿼리문 실행 변수

	public int checkId(String id) {
		int result = 0;
		
			System.out.println("Check Id");
			System.out.println("target ID: "+id);
			
			result = (Integer)sqlSession.selectOne("checkId", id);
			
			System.out.println("ID check : "+result);
		
		return result;
	}
//	ID 중복 검사
	
	public int join(MemberBean bean) {
		int result = 0;
		
			result = sqlSession.insert("member.Join", bean);
		
		return result;
	}
	// 회원가입
	
	public String getPassById(String id) {
		// TODO Auto-generated method stub
	 String passwd = null;
			passwd =  (String) sqlSession.selectOne("getPass",id);
			
			System.out.println("db Passwd: "+ passwd);
		
		return passwd;
	}
//	아이디로 비밀번호 얻기

	public String getGrade(String id) {
		// TODO Auto-generated method stub
		String grade = null;
			grade = (String) sqlSession.selectOne("member.grade",id);
		
		return grade;
	}
//	등급얻기

	public void deleteMember(String authUser) {
		// TODO Auto-generated method stub
		int result = 0;
			result = sqlSession.delete("member.delete",authUser);
	}

	public int getMemberCount() {
		// TODO Auto-generated method stub
		int result = 0;
			result = (int) sqlSession.selectOne("member.count");
		return result;
	}

	public List<MemberBean> getMemberList(Map rows) {
		// TODO Auto-generated method stub
		List<MemberBean> list = new ArrayList<>();
			list = sqlSession.selectList("member.MemberList", rows);
		return list;

	}

	public MemberBean getMember(String user) {
		// TODO Auto-generated method stub
		System.out.println("MemberDao:getMember");
		MemberBean bean = null;
		bean = (MemberBean)sqlSession.selectOne("member.content", user );
		return bean;
	}
}
