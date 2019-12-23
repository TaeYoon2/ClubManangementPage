package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.NoticeDao;
import model.NoticeBean;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	private ServiceUtil serviceutil = new ServiceUtilImpl();//잘못된 접근
	
	
	@Override
	public void insertNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		int maxSize = 1024*1024*10;
//		첨부파일 최대 용량
		String savePath = request.getSession().getServletContext().getRealPath("/upload").replaceAll("\\\\", "/");
//		request.getRealPath("/upload") 와 같다.
//		escape code 를 피하기 위해 \\\\ 를 모두 /로 바꾼다.
		//		웹서버 컨테이너 경로
//		저장 경로( ex) ~?/upload)
		System.out.println("savePath:"+savePath);
		String uploadFile = "";
//		업로드 파일명
		String newFileName = "";
//		실제 저장 이름
		
		int read = 0;
		byte[] buff= new byte[1024];
		FileInputStream fis = null;
		FileOutputStream fos = null;
		MultipartRequest multi = null;
		
		try {
			
			multi = new MultipartRequest(request,
					savePath,
					maxSize,
					"UTF-8", new DefaultFileRenamePolicy());
		} catch(Exception e) {
			e.printStackTrace();
		}
			
			String id = multi.getParameter("id");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");

			NoticeBean bean = new NoticeBean();
	        bean.setId(id);
	        bean.setSubject(subject);
	        bean.setContent(content);
//	        null값 확인
	        System.out.println(bean.getId());
	        System.out.println(bean.getSubject());
	        System.out.println(bean.getContent());
	        
	        File file = multi.getFile("uploadFile");
	        
	        if(file==null) {
	        	bean.setOrifile("");
	        	bean.setSavefile("");
	        } else {
			uploadFile = multi.getFilesystemName("uploadFile");
//			input은 html 페이지 file parameter 이름
			bean.setOrifile(multi.getOriginalFileName("uploadFile"));//원본 이름
			bean.setSavefile(file.getName());//실제 저장된 이름
			
			System.out.println("oriName: "+bean.getOrifile());
			System.out.println("saveName: "+bean.getSavefile());
	        }

		try{
			noticeDao.noticeWrite(bean);
		} catch(Exception e) {
		serviceutil.AccessDenial(response, "저장실패");
		e.printStackTrace();
		}
	}

	@Override
	public void updateNotice(NoticeBean bean, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int maxSize = 1024*1024*10;
//		첨부파일 최대 용량
		String savePath = request.getSession().getServletContext().getRealPath("/upload").replaceAll("\\\\", "/");
//		request.getRealPath("/upload") 와 같다.
//		escape code 를 피하기 위해 \\\\ 를 모두 /로 바꾼다.
		//		웹서버 컨테이너 경로
//		저장 경로( ex) ~?/upload)
		System.out.println(savePath);


		String newFileName = "";
//		실제 저장 이름
		
		int read = 0;
		byte[] buff= new byte[1024];
		FileInputStream fis = null;
		FileOutputStream fos = null;
		MultipartRequest multi = null;
		
		try {
			
			multi = new MultipartRequest(request,
					savePath,
					maxSize,
					"UTF-8", new DefaultFileRenamePolicy());
		} catch(Exception e) {
			e.printStackTrace();
		}
		int no = Integer.parseInt( multi.getParameter("no") );
		int page = Integer.parseInt( multi.getParameter("page") );
		String id = multi.getParameter("id");
		String subject = multi.getParameter("subject");
		String content = multi.getParameter("content");
		String uploadFile = multi.getOriginalFileName("uploadFile");
//		업로드 파일명
		System.out.println(uploadFile);
		
		
		/*NoticeBean bean = new NoticeBean();
		bean.setContent(content);
		bean.setId(id);
		bean.setNo(no);
		bean.setSubject(subject);*/
		
		
		if(uploadFile == null) {
//			파일변경이 없으면
			bean.setOrifile(noticeDao.getNotice(no).getOrifile());
			bean.setSavefile(noticeDao.getNotice(no).getSavefile());
			
			
			
			
		} else {
//			파일변경이 있으면
			
			File file = multi.getFile("uploadFile");
			
			if(file==null) {
//				변경된 파일이 만약에라도 null이면
				bean.setOrifile(noticeDao.getNotice(no).getOrifile());
				bean.setSavefile(noticeDao.getNotice(no).getSavefile());
	        } else {
//	        	파일변경이 있고 정상적인 파일이면
	        	
	        	String savename = savePath +"/" +noticeDao.getNotice(no).getSavefile();
	        	System.out.println("oldfile: "+savename);
	        	File oldFile = new File(savename);
	        			if(oldFile.exists()&& oldFile.isFile()) {
	        				oldFile.delete();
//		        			옛날파일삭제
	        				
	        				
	        			}
	        			uploadFile = multi.getFilesystemName("uploadFile");
//        				input은 html 페이지 file parameter 이름
        				bean.setOrifile(multi.getOriginalFileName("uploadFile"));//원본 이름
        				bean.setSavefile(file.getName());//실제 저장된 이름
        				
        				System.out.println("oriName: "+bean.getOrifile());
        				System.out.println("saveName: "+bean.getSavefile());
	
	        	        				
	//어떻게 나눌까? 저장 삭제        			
			
	        }
		}
		
		try{noticeDao.noticeUpdate(bean);
		} catch(Exception e) {
			serviceutil.AccessDenial(response, "수정실패");
		}
		
		noticeDao.navHighlight(request, 1);
		
	}

	@Override
	public NoticeBean getCont(int no) throws Exception {
		// TODO Auto-generated method stub
		NoticeBean bean = noticeDao.getNotice(no);
		noticeDao.readcountUp(no);
		return bean;
	}

	@Override
	public Map<String, Object> notice_list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/******************************list 인수 계산 ****************************************************************************/			
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		
		int PPV = 5; //한 view 당  page수
		int APP = 5; //한 page 당 Article 수
		
		int total = noticeDao.getNoticeCount();
		
		int PC = (total % APP==0) ? (total / APP) :  (total / APP) + 1;
//		총 페이지수
		int VC = (PC % PPV == 0) ? (PC / PPV) : (PC / PPV) +1;
//		총 view 수
		int startRow = APP * (page -1) + 1;
//		1, 6, 11, ...
		int endRow = startRow + (APP-1);
//		5, 10, 15, ....
		int view = (page -1)/ PPV + 1;
//		현재 view
		
		int startPage = PPV * (view - 1) + 1;
//		1, 2, 3, 4,
		int endPage = startPage + (PPV -1);
		if(endPage > PC) endPage = PC;
		
		int topnum = total -(page -1) * APP;
//				n, n -APP, n- 2APP, ...
/******************************list 인수 계산 ****************************************************************************/			
		Map rows = new HashMap<>();
		rows.put("start", startRow);
		rows.put("end", endRow);
		
		
		List<NoticeBean> list = noticeDao.getNoticeList(rows);//Mybatis
		System.out.println(list);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("total", total);
		resultMap.put("topnum", topnum);
		resultMap.put("page", page);
		resultMap.put("view", view);
		resultMap.put("VC", VC);
		resultMap.put("PPV", PPV);
		resultMap.put("noticelist", list);
		resultMap.put("startPage", startPage);
		resultMap.put("endPage", endPage);
		
		
		noticeDao.navHighlight(request, 1);
		
		return resultMap;
	}

	@Override
	public void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int no = Integer.parseInt( request.getParameter("no"));
		int page = Integer.parseInt( request.getParameter("page"));
		
		
	    
		NoticeBean dto = noticeDao.getNotice(no);
		String filename = dto.getOrifile();
		String savename = request.getSession().getServletContext().getRealPath("/upload").replaceAll("\\\\", "/") + "/"+dto.getSavefile();
		System.out.println(savename);
		
		File uploadFile = new File(savename);
		if(uploadFile.exists()&& uploadFile.isFile()) {
			uploadFile.delete();
		}
//		파일삭제
		
		if(noticeDao.deleteNotice(no)!=1) {
//			DB공지 삭제
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
	}

	@Override
	public void filedown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int maxSize = 1024*1024*10;
//		첨부파일 최대 용량
		String downPath = request.getSession().getServletContext().getRealPath("/upload").replaceAll("\\\\", "/");
		String fileName = request.getParameter("downFile");
		String sDownPath = downPath+"/"+fileName;
		System.out.println("DownPath: "+downPath);
		System.out.println("FileName: "+fileName);
		System.out.println(sDownPath);
		File file= new File(sDownPath);
		
		byte[] b = new byte[maxSize];
		
		FileInputStream fis = new FileInputStream(file);
		
//		유형 확인 읽어올 경로의 파일의 유형 ->페이지 생성할 때 타입을 설정해야한다?
		String sMimeType = request.getSession().getServletContext().getMimeType(sDownPath);
		String sEncoding = URLEncoder.encode(fileName,"UTF-8");
		
		String AA="Content-Disposition";
		String BB="attachment; filename="+sEncoding;
		response.setHeader(AA, BB);
		
		ServletOutputStream out2 = response.getOutputStream();
		
		int numRead =0;
		
		while((numRead=fis.read(b,0,b.length))!=-1){
			out2.write(b,0,numRead);
		}
		out2.flush();
		out2.close();
		fis.close();
	}

}
