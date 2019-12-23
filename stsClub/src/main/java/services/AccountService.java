package services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import model.AccountBean;
import model.FiscalBean;

public interface AccountService {
	public void apply(String[] tran_dates,String[] contents, String[] carrotS, String[] savefiles, String[] pos, String month, HttpServletRequest request, HttpServletResponse response) throws Exception;//�����û
	public Map<String, Object> getBasket(String[] args, HttpServletResponse response) throws Exception;//ȸ�� ��ٱ���
	public void delete(String[] args) throws Exception;//���� ����
	public void report(String[] tran_dates,String[] contents, String[] carrotS, String[] savefiles, String[] pos, String month,  HttpServletRequest request, HttpServletResponse response  ) throws Exception;
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception;//���� ���
	public void addYear(int year) throws Exception; //ȸ�迬�� �߰�
	public List<AccountBean> filter(String byWhat, int month, int year) throws Exception;//���� ����
	public Map<String, Object> getReportList(HttpServletRequest request) throws Exception; //���� ����Ʈ ���
	public void download(String where, String file, HttpServletRequest request, HttpServletResponse response) throws Exception; //���� �ٿ�
	public List<FiscalBean> accountForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
