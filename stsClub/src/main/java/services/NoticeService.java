package services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoticeBean;

public interface NoticeService {
	public void insertNotice(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//�����ۼ�
	public void updateNotice(NoticeBean bean, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//��������
	public NoticeBean getCont(int no) throws Exception;
	//��������
	public Map<String, Object> notice_list(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//���� ���
	public void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//���� ����
	public void filedown(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//���� �ٿ�
	
}
