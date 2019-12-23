package services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoticeBean;

public interface NoticeService {
	public void insertNotice(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//공지작성
	public void updateNotice(NoticeBean bean, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//공지수정
	public NoticeBean getCont(int no) throws Exception;
	//공지내용
	public Map<String, Object> notice_list(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//공지 목록
	public void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//공지 삭제
	public void filedown(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//파일 다운
	
}
