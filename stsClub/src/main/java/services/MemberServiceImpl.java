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
	private ServiceUtil serviceutil = new ServiceUtilImpl();//�߸��� ����
	

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
			serviceutil.AccessDenial(response, "���Խ���");
			e.printStackTrace();
		}
		PrintWriter out =  response.getWriter();
		if(result!=1) {
			
			out.println("<script>");
			out.println("alert('���� ����');");
			out.println("history.go(-1);");
			out.println("</script>");
		} else if(result==1) {
			out.println("<script>");
			out.println("alert('���� ����');");
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
			serviceutil.AccessDenial(response, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
	}//ȸ��Ż��

	@Override
	public void login(String id, String passwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if(memberdao.checkId(id)==0) {
			serviceutil.AccessDenial(response, "���̵� �������� �ʽ��ϴ�.");
//		���̵� ���� x
		} else {
		String pass = memberdao.getPassById(id); //���̵� ���� ����
		String grade = memberdao.getGrade(id); //��� ���� ����
		if(passwd.equals(pass)) {
			HttpSession session =  request.getSession();
			session.setAttribute("AuthUser", id);
			session.setAttribute("grade", grade);
//			�α��� ���� 
			
		} else {
			serviceutil.AccessDenial(response, "����� ������ �ùٸ��� �ʽ��ϴ�.");
			
		}
		}//���̵� ����
	}//�α���

	@Override
	public Map<String, Object> member_list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberList");
		
		if(!request.getSession().getAttribute("grade").equals("G")) {
			serviceutil.AccessDenial(response, "�߸��� �����Դϴ�.");
		}
/******************************list �μ� ��� ****************************************************************************/			
		int page = 1;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		int PPV = 5; //�� view ��  page��
		int APP = 5; //�� page �� Article ��
		
		int total = memberdao.getMemberCount();//Mybatis
		 
		int PC = (total % APP==0) ? (total / APP) :  (total / APP) + 1;
//		�� ��������
		int VC = (PC % PPV == 0) ? (PC / PPV) : (PC / PPV) +1;
//		�� view ��
		int startRow = APP * (page -1) + 1;
//		1, 6, 11, ...
		int endRow = startRow + (APP-1);
//		5, 10, 15, ....
		int view = (page -1)/ PPV + 1;
//		���� view
		
		int startPage = PPV * (view - 1) + 1;
//		1, 2, 3, 4,
		int endPage = startPage + (PPV -1);
		if(endPage > PC) endPage = PC;
		
		int topnum = total -(page -1) * APP;
//				n, n -APP, n- 2APP, ...
/******************************list �μ� ��� ****************************************************************************/			
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
