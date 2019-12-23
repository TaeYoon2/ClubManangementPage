package action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.NoticeBean;
import services.NoticeService;

@Controller
public class NoticeAction {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="/noticeForm.cl")
	public String noticeForm() {
		return "notice/NoticeForm";
	}
	
	@RequestMapping(value="/noticeList.cl")
	public String noticeList(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> map = noticeService.notice_list(request,response);
		model.addAllAttributes(map);
		return "notice/NoticeList";
	}
	
	@RequestMapping(value="/noticeWrite.cl")
	public String noticeWrite(HttpServletRequest request,
							HttpServletResponse response) throws Exception {
		System.out.println("NoticeAction:noticeWrite");
		noticeService.insertNotice(request, response);
		
		return "redirect:/noticeList.cl";
	}
	
	@RequestMapping(value="/noticeUpdateForm.cl")
	public String noticeUpdateForm() {
		return "notice/NoticeUpdateForm";
	}
	
	@RequestMapping(value="/noticeUpdate.cl")
	public String noticeUpdate(@ModelAttribute NoticeBean bean,
								HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		
		noticeService.updateNotice(bean,  request, response);
		
		return "redirect:/noticeList.cl";
	}
	
	@RequestMapping(value="/noticeContent.cl")
	public String noticeContent(Model model,
			@RequestParam("page") int page,
			@RequestParam("no") int no) throws Exception {
		NoticeBean bean = noticeService.getCont(no);
		
		model.addAttribute("bean",bean);
		model.addAttribute("page",page);
		return "notice/NoticeContent";
	}
	
	@RequestMapping(value="/noticeDelete.cl")
	public String noticeDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		noticeService.deleteNotice(request, response);
		return "redirect:/noticeList.cl";
				
	}
	
	@RequestMapping(value="/noticeFileDown.cl")
	public void noticeFileDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		noticeService.filedown(request, response);
	}
	
	
}
