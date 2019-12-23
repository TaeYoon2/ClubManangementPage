package action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.AccountBean;
import model.FiscalBean;
import services.AccountService;

@Controller
public class AccountAction {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/accountForm.cl")
	public String accounForm(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		List<FiscalBean> years = accountService.accountForm(request,response);
		model.addAttribute("years", years);
		System.out.println(years);
		return "accounting/AccountForm";
	}
	@RequestMapping(value="/accountApply.cl")
	public String accountApply(@RequestParam("tran_dates") String[] tran_dates,
			@RequestParam("contents") String[] contents,
			@RequestParam("carrotS") String[] carrotS, 
			@RequestParam("savefiles") String[] savefiles, 
			@RequestParam("pos") String[] pos, 
			@RequestParam("month") String month,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		accountService.apply(tran_dates, contents, carrotS, savefiles, pos, month, request, response);
		
		return "redirect:/accountForm.cl";
	}
	
	@RequestMapping(value="/accountBasket.cl")
	public String accountBasket(Model model, @RequestParam("ch") String[] chs, HttpServletResponse response) throws Exception {
		
		Map<String, Object> map = accountService.getBasket(chs, response);
		model.addAllAttributes(map);
		return "accounting/AccountingBasket";
	}
	
	@RequestMapping(value="/accountDelete.cl")
	public String accountDelete(@RequestParam("ch") String[] checkboxes) throws Exception {
		
		accountService.delete(checkboxes);
		
		return "redirect:/accountForm.cl";
	}
	
	@RequestMapping(value="/accountReport.cl")
	public String accountReport(@RequestParam("tran_dates") String[] tran_dates,
			@RequestParam("contents") String[] contents,
			@RequestParam("carrotS") String[] carrotS, 
			@RequestParam("savefiles") String[] savefiles, 
			@RequestParam("pos") String[] pos, 
			@RequestParam("month") String month,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		accountService.report(tran_dates, contents, carrotS, savefiles, pos, month, request, response);
		
		return "redirect:/accountForm.cl";
	}
	
	@RequestMapping(value="/accountUpload.cl")
	public String accountUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		accountService.upload(request, response);
		return "redirect:/accountForm.cl";
	}
	
	@RequestMapping(value="/addYear.cl")
	public String addYear(@RequestParam("addyear") int year) throws Exception {
		
		accountService.addYear(year);
		
		return "redirect:/accountForm.cl";
	}
	
	@RequestMapping(value="/reportDown.cl")
	public void reportDown(@RequestParam("where") String where, @RequestParam("file") String file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		accountService.download(where, file, request, response);
	}
	
	@RequestMapping(value="/reportList.cl")
	public String reportList(HttpServletRequest request) throws Exception {
		
		accountService.getReportList(request);
		
		return "accounting/ReportList";
	}

	
	@ResponseBody
	@RequestMapping(value="/filter.cl")
	public List<AccountBean> filter(@RequestParam("byWhat") String byWhat,@RequestParam("monthl") int month,@RequestParam("yearl") int year) throws Exception{
		
		List<AccountBean> accounts = null;
		accounts = accountService.filter(byWhat, month, year);
		return accounts;
	}
}
