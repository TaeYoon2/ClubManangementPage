package action;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.MemberBean;
import services.MemberService;

@Controller
public class MemberAction {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/JoinForm.cl")
	public String joinForm() throws Exception {
		System.out.println("MemberAction:joinForm");
		return "member/JoinForm";
	}
	
	@RequestMapping(value="/idCheck.cl")
	public void idCheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		System.out.println("MemberAction:idCheck");
		memberService.idCheck(id, response);
	}//아이디 중복 확인
	
	@RequestMapping(value="/join.cl", method = RequestMethod.POST)
	public String join(@ModelAttribute MemberBean mbean, 
					@RequestParam("birth1") String birthStr1,
					@RequestParam("birth2") String birthStr2,
					@RequestParam("birth3") String birthStr3,
					HttpServletRequest request,
					HttpServletResponse response) throws Exception {
		System.out.println("MemberAction:join");
		
		String birthStr = birthStr1.trim() +"-"+ birthStr2.trim() +"-"+ birthStr3.trim();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = null;
		try {
			 birth = new Date(dateformat.parse(birthStr).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mbean.setBirth(birth);
		String grade = MemberBean.GRADE.NEW;
		if(mbean.getId().equals("master")) grade = MemberBean.GRADE.MANEGER;
//		관리자 계정
//		등급
		mbean.setGrade(grade);
		memberService.join(mbean, response,request);
		return "index";
	}
	
	@RequestMapping(value="/dropoutForm.cl")
	public String dropoutForm() throws Exception {
		System.out.println("MemberAction:dropoutForm");
		return "member/Dropout";
	}
	
	@RequestMapping(value="/dropout.cl")
	public String drop(@RequestParam("AuthUser") String AuthUser,
					   @RequestParam("passwd") String passwd,
					   HttpServletResponse response,
					   HttpServletRequest request) throws Exception{
		memberService.dropout(AuthUser, passwd, request, response);
		return "index";
	}
	
	@RequestMapping(value="/login.cl")
	public String login(@RequestParam("id") String id,
						@RequestParam("passwd") String passwd,
						HttpServletRequest request,
						HttpServletResponse response) throws Exception{
		System.out.println("MemberAction:login");
		memberService.login(id, passwd, request, response);
		return "index";
	}
	@RequestMapping(value="/logout.cl")
	public String logout(HttpServletRequest request) {
		System.out.println("MemberAction:logout");
		HttpSession session =  request.getSession();
		session.invalidate();
//		로그아웃
		return "index";
	}
	
	@RequestMapping(value="/memberList.cl")
	public String memberList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, Object> memberlist = memberService.member_list(request, response);
		
		model.addAllAttributes(memberlist);
		return "member/MemberList";
	}
	
	@RequestMapping(value="/myInfo.cl")
	public String myInfo(Model model, @RequestParam("AuthUser") String AuthUser) throws Exception{
		model.addAttribute("bean",memberService.getInfo(AuthUser));
		return "member/MyInfo";
	}
}
