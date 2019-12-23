package services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;

public interface MemberService {
//	������ 1, ������ 0
	public void idCheck(String id, HttpServletResponse response) throws Exception;//id �ߺ��˻�
	public void join(MemberBean bean,HttpServletResponse response, HttpServletRequest request) throws Exception;//ȸ������
	public void dropout(String AuthUser, String passwd, HttpServletRequest request, HttpServletResponse response) throws Exception;//ȸ��Ż��
	public void login(String id, String pass, HttpServletRequest request, HttpServletResponse response) throws Exception;//�α���
//	�α׾ƿ��� DB���� �����Ƿ� ��Ʈ�ѷ����� ó������!
	public Map<String, Object> member_list(HttpServletRequest request,
			HttpServletResponse response) throws Exception;//ȸ�����
	public MemberBean getInfo(String user) throws Exception;//��������
	 
}

