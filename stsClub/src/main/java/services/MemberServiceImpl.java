package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import model.MemberBean;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberdao;
	private ServiceUtil serviceutil = new ServiceUtilImpl();//잘못된 접근
	

	@Override
	public void idCheck(String id, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberService:idCheck");
		int prob = memberdao.checkId(id);
		PrintWriter out = response.getWriter();
		out.println(prob);
		
	}

	@Override
	public void join(MemberBean bean, HttpServletResponse response, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
		result = memberdao.join(bean);
		} catch(Exception e) {
			serviceutil.AccessDenial(response, "가입실패");
			e.printStackTrace();
		}
		PrintWriter out =  response.getWriter();
		if(result!=1) {
			
			out.println("<script>");
			out.println("alert('가입 실패');");
			out.println("history.go(-1);");
			out.println("</script>");
		} else if(result==1) {
			out.println("<script>");
			out.println("alert('가입 성공');");
			out.println("location.assign("+"location.hostname+location.port"+request.getContextPath()+");");
			out.println("</script>");
		}
	}

	@Override
	public void dropout(String AuthUser, String passwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String pass = memberdao.getPassById(AuthUser);
		
		if(pass.equals(passwd)) {
			memberdao.deleteMember(AuthUser);
			request.getSession().invalidate();
		} else {
			serviceutil.AccessDenial(response, "비밀번호가 일치하지 않습니다.");
		}
	}//회원탈퇴

	@Override
	public void login(String id, String passwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if(memberdao.checkId(id)==0) {
			serviceutil.AccessDenial(response, "아이디가 존재하지 않습니다.");
//		아이디 존재 x
		} else {
		String pass = memberdao.getPassById(id); //아이디 세션 공유
		String grade = memberdao.getGrade(id); //등급 세션 공유
		if(passwd.equals(pass)) {
			HttpSession session =  request.getSession();
			session.setAttribute("AuthUser", id);
			session.setAttribute("grade", grade);
//			로그인 세션 
			
		} else {
			serviceutil.AccessDenial(response, "사용자 정보가 올바르지 않습니다.");
			
		}
		}//아이디 존재
	}//로그인

	@Override
	public Map<String, Object> member_list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberList");
		
		if(!request.getSession().getAttribute("grade").equals("G")) {
			serviceutil.AccessDenial(response, "잘못된 접근입니다.");
		}
/******************************list 인수 계산 ****************************************************************************/			
		int page = 1;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		int PPV = 5; //한 view 당  page수
		int APP = 5; //한 page 당 Article 수
		
		int total = memberdao.getMemberCount();//Mybatis
		 
		int PC = (total % APP==0) ? (total / APP) :  (total / APP) + 1;
//		총 페이지수
		int VC = (PC % PPV == 0) ? (PC / PPV) : (PC / PPV) +1;
//		총 view 수
		int startRow = APP * (page -1) + 1;
//		1, 6, 11, ...
		int endRow = startRow + (APP-1);
//		5, 10, 15, ....
		int view = (page -1)/ PPV + 1;
//		현재 view
		
		int startPage = PPV * (view - 1) + 1;
//		1, 2, 3, 4,
		int endPage = startPage + (PPV -1);
		if(endPage > PC) endPage = PC;
		
		int topnum = total -(page -1) * APP;
//				n, n -APP, n- 2APP, ...
/******************************list 인수 계산 ****************************************************************************/			
		Map rows = new HashMap<>();
		rows.put("start", startRow);
		rows.put("end", endRow);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<MemberBean> list = memberdao.getMemberList(rows);//Mybatis
		System.out.println(rows);
		System.out.println(list);
		resultMap.put("total", total);
		resultMap.put("topnum", topnum);
		resultMap.put("page", page);
		resultMap.put("view", view);
		resultMap.put("VC", VC);
		resultMap.put("PPV", PPV);
		resultMap.put("memberlist", list);
		resultMap.put("startPage", startPage);
		resultMap.put("endPage", endPage);
		
		
		return resultMap;
	}

	@Override
	public MemberBean getInfo(String user) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberService:getInfo");
		MemberBean bean = null;
		bean = memberdao.getMember(user);
		return bean;
	}

}
