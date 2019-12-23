package services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import model.AccountBean;
import model.FiscalBean;

public interface AccountService {
	public void apply(String[] tran_dates,String[] contents, String[] carrotS, String[] savefiles, String[] pos, String month, HttpServletRequest request, HttpServletResponse response) throws Exception;//예산신청
	public Map<String, Object> getBasket(String[] args, HttpServletResponse response) throws Exception;//회계 장바구니
	public void delete(String[] args) throws Exception;//내역 삭제
	public void report(String[] tran_dates,String[] contents, String[] carrotS, String[] savefiles, String[] pos, String month,  HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception;//내역 등록
	public void addYear(int year) throws Exception; //회계연도 추가
	public List<AccountBean> filter(String byWhat, int month, int year) throws Exception;//내역 필터
	public Map<String, Object> getReportList(HttpServletRequest request) throws Exception; //보고서 리스트 출력
	public void download(String where, String file, HttpServletRequest request, HttpServletResponse response) throws Exception; //보고서 다운
	public List<FiscalBean> accountForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
