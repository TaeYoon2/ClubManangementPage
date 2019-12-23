package services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;

public interface MemberService {
//	있으면 1, 없으면 0
	public void idCheck(String id, HttpServletResponse response) throws Exception;//id 중복검사
	public void join(MemberBean bean,HttpServletResponse response, HttpServletRequest request) throws Exception;//회원가입
	public void dropout(String AuthUser, String passwd, HttpServletRequest request, HttpServletResponse response) throws Exception;//회원탈퇴
	public void login(String id, String pass, HttpServletRequest request, HttpServletResponse response) throws Exception;//로그인
//	로그아웃은 DB접근 없으므로 컨트롤러에서 처리하자!
	public Map<String, Object> member_list(HttpServletRequest request,
			HttpServletResponse response) throws Exception;//회원목록
	public MemberBean getInfo(String user) throws Exception;//개인정보
	 
}

