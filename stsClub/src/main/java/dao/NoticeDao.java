package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.NoticeBean;

@Repository
public class NoticeDao {
	@Autowired
	private SqlSession sqlSession;//mybatis 쿼리문 실행 변수

	public void noticeWrite(NoticeBean bean) {
		// TODO Auto-generated method stub
		int result = 0;
			result = sqlSession.insert("notice.write", bean);
	}

	public NoticeBean getNotice(int no) {
		// TODO Auto-generated method stub
		NoticeBean dto = null;
			dto = (NoticeBean) sqlSession.selectOne("notice.content", no );
		return dto;
	}

	public void noticeUpdate(NoticeBean bean) {
		// TODO Auto-generated method stub
			sqlSession.update("notice.update", bean);
	}

	public void navHighlight(HttpServletRequest request, int i) {
		// TODO Auto-generated method stub
		HashMap<Integer, String> page = new HashMap<>();
		page.put(0, "main");
		page.put(1, "noti");
		page.put(2, "hi");
		page.put(3, "tabl");
		page.put(4, "mone");
//		0,1,2,3,4
		String[] activate = {"","active"};
//		1이면 active
		for(int k=0;k<5;k++) {
			if(k==i) {
				request.setAttribute(page.get(k), activate[1]);
			}else {
				request.setAttribute(page.get(k), activate[0]);
			}
		}
	}

	public List<NoticeBean> getNoticeList(Map rows) {
		// TODO Auto-generated method stub
		List<NoticeBean> list = new ArrayList<>();
			list = sqlSession.selectList("notice.NoticeList", rows);
			
		return list;
	}

	public int getNoticeCount() {
		// TODO Auto-generated method stub
		int result = 0;
			result = (int) sqlSession.selectOne("notice.count");
		return result;
	}
	
	public void readcountUp(int no) {
			sqlSession.update("notice.countup",no);
		
	}

	public int deleteNotice(int no) {
		// TODO Auto-generated method stub
		int result = 0;
		result = sqlSession.delete("notice.delete",no);
		return result;
	}
}
