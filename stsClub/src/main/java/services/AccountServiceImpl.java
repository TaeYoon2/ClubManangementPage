package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;                                                                                                                                          
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.AccountDao;
import model.AccountBean;
import model.FiscalBean;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountdao;
	private ServiceUtil serviceutil = new ServiceUtilImpl();//잘못된 접근
	
	
	@Override
	public List<FiscalBean> accountForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if(!request.getSession().getAttribute("grade").equals("G")) {
			serviceutil.AccessDenial(response, "잘못된 접근입니다.");
			
		}
		
		
		List<FiscalBean> years = accountdao.getYears();
		return years;
	}

	@Override
	public void apply(String[] tran_dates,String[] contents, String[] carrotS, String[] savefiles, String[] pos, String month,  HttpServletRequest request, HttpServletResponse response  ) throws Exception {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
//		String[] tran_dates = request.getParameterValues("tran_date");
		int year = Integer.parseInt(tran_dates[0].substring(0, 4));
//		String[] contents = request.getParameterValues("content");
//		String[] carrotS = request.getParameterValues("carrot");
		
		int[] carrots = new int[carrotS.length];
		for(int i=0;i<carrots.length;i++) {
			carrots[i] = Integer.parseInt(carrotS[i]);
		}
//		String[] savefiles = request.getParameterValues("savefile");
//		String[] pos = request.getParameterValues("po");
//		String month = request.getParameter("month");
		int monthint = Integer.parseInt(month);
		
		GregorianCalendar gc = new GregorianCalendar(year,monthint-1,1);
		int maxday = gc.getActualMaximum(gc.DAY_OF_MONTH);
//		해당 월 날짜수
		String readname = "report.xlsx";
		String filename = "report"+month+".xlsx";
		
		System.out.println(tran_dates.length);
		System.out.println(contents.length);
		System.out.println(carrots.length);
		System.out.println(filename);
		int poslen = pos.length;
//		경과보고 내용 크기
		int startporow = 15;
//		경과보고 시작 행
		int endporow = startporow+poslen-1;
//		경과보고 마지막 행
		
		int contcol = 3;
//		세부내역 열
		int carrcol = 14;
//		금액 열
		int contlen = contents.length;
//		경과보고 내용 크기
		int startcontrow = 25;
//		내용 시작행
		int endcontrow = startcontrow+contlen-1;
//		경과보고 마지막 행
	     // Workbook 생성
        Workbook xlsWb; 
//        Workbook xlsxWb = new XSSFWorkbook(); // Excel 2007 이상
     // Sheet 생성
        Sheet sheet1;
        
        Row row = null;
        Cell cell = null;
    	  try
    	    {
    		  String downPath = request.getSession().getServletContext().getRealPath("/account")+"/";
    		  
    		  System.out.println(downPath+readname);
    		  File newFile =new File(downPath+readname );
    		  if (newFile.exists()) { 
    			   // Load existing
    			  xlsWb = WorkbookFactory.create(newFile);
    			} else {
    			   // What kind of file are they trying to ask for?
    			   // Add additional supported types here
    			   if (newFile.getName().endsWith(".xls")) {
    				   xlsWb = new HSSFWorkbook();
    			   }
    			   else if (newFile.getName().endsWith(".xlsx")) {
    				   xlsWb = new XSSFWorkbook();
    			   }
    			   else {
    			      throw new IllegalArgumentException("I don't know how to create that kind of new file");
    			   }
    			}
    			   
    	       
 
    	        if( xlsWb != null)
    	        {
    	            sheet1 = xlsWb.getSheetAt( 0);
 
    	            if( sheet1 != null)
    	            {
 
    	                //기록물철의 경우 실제 데이터가 시작되는 Row지정
    	                int nRowStartIndex = 0;
    	                //기록물철의 경우 실제 데이터가 끝 Row지정
    	                int nRowEndIndex   = sheet1.getLastRowNum();
 
    	                //기록물철의 경우 실제 데이터가 시작되는 Column지정
    	                int nColumnStartIndex = 0;
    	                //기록물철의 경우 실제 데이터가 끝나는 Column지정
    	                int nColumnEndIndex = sheet1.getRow( 1).getLastCellNum();
 
    	                String szValue = "";
 
    	                for( int i = nRowStartIndex; i <= nRowEndIndex ; i++)
    	                {
    	                    row  = sheet1.getRow( i);
 
    	                    for( int nColumn = nColumnStartIndex; nColumn <= nColumnEndIndex ; nColumn++)
    	                    {
    	                        cell = row.getCell(( short ) nColumn);
    	                        
    	                  
    	            	        // 행사명-------------
    	            	        
    	            	        if((i==7)&&nColumn==3) {
    	            	        	cell = row.createCell(nColumn);
    	            	        	cell.setCellValue(month+"월 활동비");
    	            	        }
    	            	        
    	            	     // 년도앞-------------
    	            	        if(i==12 &&nColumn==4) {
    	            	        	cell = row.createCell(nColumn);
    	            	        	cell.setCellValue(Integer.parseInt(Integer.toString(year).substring(0,2)));
//    	            	        	행사내용 배열 0~
    	            	        }
    	            	     // 년도뒤-------------
    	            	        if(i==12&&nColumn==5) {
    	            	        	cell = row.createCell(nColumn);
    	            	        	cell.setCellValue(Integer.parseInt(Integer.toString(year).substring(2)));
//    	            	        	행사내용 배열 0~
    	            	        }
    	            	     // 월-------------
    	            	        if(i==12 &&nColumn==7) {
    	            	        	cell = row.createCell(nColumn);
    	            	        	cell.setCellValue(monthint);
//    	            	        	행사내용 배열 0~
    	            	        }
    	            	     // 일-------------
    	            	        if(i==12 &&nColumn==18) {
    	            	        	cell = row.createCell(nColumn);
    	            	        	cell.setCellValue(maxday);
//    	            	        	행사내용 배열 0~
    	            	        }
    	                    
    	            	     // 행사내용-------------
    	            	        
    	            	        if(i>=startporow&&i<=endporow &&nColumn==3) {
    	            	        	cell = row.createCell(nColumn);
    	            	        	cell.setCellValue(pos[i-startporow]);
//    	            	        	행사내용 배열 0~
    	            	        }
    	            	        
    	            	        
    	            	        // 세부내역-------------
    	            	        
    	            	        if(i>=startcontrow&&i<=endcontrow &&nColumn==contcol) {
    	            	        	cell = row.createCell(nColumn);
    	            	        	cell.setCellValue(contents[i-startcontrow]);
//    	            	        	세부내역 배열 0~
    	            	        }
    	            	        // 세부내역 금액-------------
    	            	        
    	            	        if(i>=startcontrow&&i<=endcontrow &&nColumn==carrcol) {
    	            	        	cell = row.createCell(nColumn);
    	            	        	cell.setCellValue(carrots[i-startcontrow]);
//    	            	        	세부내역 금액 0~
    	            	        }
    	            	        
       
    	            	        
    	           

    	            	         
    	                    }
    	                    System.out.println();
    	                }
//	                    참조값 수행
            	        xlsWb.getCreationHelper().createFormulaEvaluator().evaluateAll();
            	        
        	     // excel 파일 저장
            	       String place= downPath+filename;
            	       
            	        try {
            	        	File xlsFile = new File(place);
            	            FileOutputStream fileOut = new FileOutputStream(xlsFile);
            	            xlsWb.write(fileOut);
            	        } catch (FileNotFoundException e) {
            	            e.printStackTrace();
            	        } catch (IOException e) {
            	            e.printStackTrace();
            	        }
    	            }else	{
		System.out.println("Sheet is null!!");
	}
   }else{System.out.println("WorkBook is null!!");
   }

	}catch(
	Exception e)
	{
		e.printStackTrace();
	}
	}

	@Override
	public Map<String, Object> getBasket(String[] args, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
//		String[] checkboxes = request.getParameterValues("ch");
		List<String> nos = null;
		List<AccountBean> accounts = null;
		int sum = 0;
		
		if(args !=null) {
		nos = Arrays.asList(args);
//		장바구니에 담은 내역 번호들
		accounts =  accountdao.getBasket(nos);
		System.out.println("Size: "+accounts.size());
		System.out.println(accounts.get(0).getTran_date());
		
		for(int i=0;i<accounts.size();i++) {
			if(accounts.get(i).getInout().equals("수입")) {
			sum +=accounts.get(i).getCarrot();
			} else {
				sum-=accounts.get(i).getCarrot();
			}
			//합계
		}
		}else {
			serviceutil.AccessDenial(response, "활동사항을 입력하세요.");
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("sum", Math.abs(sum));
		resultMap.put("beans",accounts);
		return resultMap;
	}

	@Override
	public void delete(String[] args) throws Exception {
		// TODO Auto-generated method stub
		List<String> nos = Arrays.asList(args);
//		장바구니에 담은 내역 번호들
		
		accountdao.deleteAccounts(nos);
	}

	@Override
	public void report(String[] tran_dates,String[] contents, String[] carrotS, String[] savefiles, String[] pos, String month,  HttpServletRequest request, HttpServletResponse response  ) throws Exception {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");

//		String[] tran_dates = request.getParameterValues("tran_date");
//		String[] contents = request.getParameterValues("content");
//		String[] carrotS = request.getParameterValues("carrot");
		int[] carrots = new int[carrotS.length];
		for (int i = 0; i < carrots.length; i++) {
			carrots[i] = Integer.parseInt(carrotS[i]);
		}
//		String[] savefiles = request.getParameterValues("savefile");
//		String[] pos = request.getParameterValues("po");
//		String month = request.getParameter("month");
		String filename = "report" + month + ".xlsx";
		System.out.println(tran_dates.length);
		System.out.println(contents.length);
		System.out.println(carrots.length);
		System.out.println(filename);
		int poslen = pos.length;
		// 경과보고 내용 크기
		int startporow = 13;
		// 경과보고 시작 행
		int endporow = startporow + poslen - 1;
		// 경과보고 마지막 행

		int contcol = 25;
		// 세부내역 열
		int carrcol = 36;
		// 금액 열
		int contlen = contents.length;
		// 경과보고 내용 크기
		int startcontrow = 28;
		// 내용 시작열
		int endcontrow = startcontrow + contlen - 1;
		// 경과보고 마지막 행

		// Workbook 생성
		Workbook xlsWb;
		// Workbook xlsxWb = new XSSFWorkbook(); // Excel 2007 이상
		// Sheet 생성
		Sheet sheet1;

		Row row = null;
		Cell cell = null;
		try {
			String downPath = request.getSession().getServletContext().getRealPath("/account")+"/";
			System.out.println(downPath + filename);
			File newFile = new File(downPath + filename);
			if (newFile.exists()) {
				// Load existing
				xlsWb = WorkbookFactory.create(newFile);
			} else {
				// What kind of file are they trying to ask for?
				// Add additional supported types here
				if (newFile.getName().endsWith(".xls")) {
					xlsWb = new HSSFWorkbook();
				} else if (newFile.getName().endsWith(".xlsx")) {
					xlsWb = new XSSFWorkbook();
				} else {
					throw new IllegalArgumentException("I don't know how to create that kind of new file");
				}
			}

			if (xlsWb != null) {
				sheet1 = xlsWb.getSheetAt(0);

				if (sheet1 != null) {

					// 기록물철의 경우 실제 데이터가 시작되는 Row지정
					int nRowStartIndex = 0;
					// 기록물철의 경우 실제 데이터가 끝 Row지정
					int nRowEndIndex = sheet1.getLastRowNum();

					// 기록물철의 경우 실제 데이터가 시작되는 Column지정
					int nColumnStartIndex = 0;
					// 기록물철의 경우 실제 데이터가 끝나는 Column지정
					int nColumnEndIndex = sheet1.getRow(1).getLastCellNum();

					String szValue = "";

					for (int i = nRowStartIndex; i <= nRowEndIndex; i++) {
						row = sheet1.getRow(i);

						for (int nColumn = nColumnStartIndex; nColumn <= nColumnEndIndex; nColumn++) {
							cell = row.getCell((short) nColumn);

							// i 번째 줄

							/*
							 * // Z6,Z10 설정하기-------------
							 * 
							 * if((i==5||i==9)&&nColumn==25) { cell = row.createCell(nColumn);
							 * cell.setCellValue(month+"월 활동비"); }
							 */
							// Z14부터 설정하기-------------

							if (i >= startporow && i <= endporow && nColumn == 25) {
								cell = row.createCell(nColumn);
								cell.setCellValue(pos[i - startporow]);
								// 경과보고 배열 0~
							}
							// 세부내역 설정하기-------------

							if (i >= startcontrow && i <= endcontrow && nColumn == contcol) {
								cell = row.createCell(nColumn);
								cell.setCellValue(contents[i - startcontrow]);
								// 세부내역 배열 0~
							}

							// AK29부터 설정하기-------------

							if (i >= startcontrow && i <= endcontrow && nColumn == carrcol) {
								cell = row.createCell(nColumn);
								cell.setCellValue(carrots[i - startcontrow]);
								// 세부내역 금액 배열 0~
							}

							

							

						}
						System.out.println();
					}
					try {
						// excel 파일 저장
						String place = downPath+"saved/" + filename;
						File xlsFile = new File(place);
						FileOutputStream fileOut = new FileOutputStream(xlsFile);
						xlsWb.write(fileOut);
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Sheet is null!!");
				}
			} else {
				System.out.println("WorkBook is null!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int maxSize = 1024*1024*10;
//		첨부파일 최대 용량
		String savePath = request.getSession().getServletContext().getRealPath("/upload/accounting").replaceAll("\\\\", "/");
//		request.getRealPath("/upload") 와 같다.
//		escape code 를 피하기 위해 \\\\ 를 모두 /로 바꾼다.
		//		웹서버 컨테이너 경로
//		저장 경로( ex) ~?/upload)
		System.out.println(savePath);
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
	
		String inout = multi.getParameter("inout");
		String year = multi.getParameter("year");
		String month = multi.getParameter("month");
		String day = multi.getParameter("day");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date tran_date = null;
		try {
			tran_date = new Date(sdf.parse(year+"-"+month+"-"+day).getTime());
		} catch(Exception e ) {
			e.printStackTrace();
		}
		int carrot = Integer.parseInt( multi.getParameter("carrot"));
		String content= multi.getParameter("content");
		
		
		
		AccountBean dto = new AccountBean();
		dto.setInout(inout);
		dto.setTran_date(tran_date);
		dto.setCarrot(carrot);
		dto.setContent(content);
		
		File file = multi.getFile("paper");
		
		if(file==null) {
        	dto.setOrifile("");
        	dto.setSavefile("");
        } else {
		uploadFile = multi.getFilesystemName("paper");
//		input은 html 페이지 file parameter 이름
		dto.setOrifile(multi.getOriginalFileName("paper"));//원본 이름
		dto.setSavefile(file.getName());//실제 저장된 이름
		
		System.out.println("oriName: "+dto.getOrifile());
		System.out.println("saveName: "+dto.getSavefile());
        }
		
		try {
			accountdao.accountUpload(dto);
		} catch(Exception e) {
			serviceutil.AccessDenial(response, "저장실패");
		}
	
	}

	@Override
	public void addYear(int year) throws Exception {
		// TODO Auto-generated method stub

		accountdao.addYear(year);
	}

	@Override
	public List<AccountBean> filter(String byWhat, int month, int year) throws Exception {
		// TODO Auto-generated method stub

//		String byWhat = request.getParameter("byWhat");
//		int month = Integer.parseInt( request.getParameter("monthl"));
//		int year = Integer.parseInt( request.getParameter("yearl"));
		
		List<AccountBean> accounts = new ArrayList<>();
//		List<A> accounts = null;
		//		필터로 담을 바구니
		
		if(byWhat.equals("m")) {
//			월별 필터
			String dat1 = year+"-"+month;
			GregorianCalendar dar = new GregorianCalendar(year,month-1,1);
			
			String dat2 = "-"+Integer.toString(dar.getActualMaximum(dar.DAY_OF_MONTH));
			
			Map dats = new HashMap<>();
			dats.put("dat1", dat1);
			dats.put("dat2", dat2);
			
			System.out.println(dat1);
			System.out.println(dat2);
			
			accounts = accountdao.getAccountsByMonth(dats);
		} else if(byWhat.equals("y")) {
//			연도별 필터
			int yearp =year+1;
			String yearStr = Integer.toString(year);
			
			System.out.println(yearStr);
			accounts = accountdao.getAccountsByYear(yearStr);
			System.out.println( accounts.get(0));
		}
		/*Gson gson = new GsonBuilder()
				   .setDateFormat("MM월 dd, yyyy").create();
		String j =  gson.toJson(accounts);
		response.addHeader("Content-Type", "application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(j);
		
		out.println(j);*/
		return accounts;
	}

	@Override
	public Map<String, Object> getReportList(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String downPath = request.getSession().getServletContext().getRealPath("/account").replaceAll("\\\\", "/");
		System.out.println("Apply Report path:"+downPath);
		File dirFile = new File(downPath);
		File dirFile1 = null;
		File[] fileList = dirFile.listFiles();
		List<String> fileNames1 = new ArrayList<>();
//		예산신청
		List<String> fileNames2 = new ArrayList<>();
//		집행보고
		
		for(File tempFile : fileList) {
//			예산신청
			if(tempFile.isFile()) {
				fileNames1.add(tempFile.getName());
			} else if(tempFile.isDirectory()) {
				dirFile1 = tempFile;
			}
		}
		
		for(File tempFile : dirFile1.listFiles()) {
//			집행보고
			if(tempFile.isFile()) {
				fileNames2.add(tempFile.getName());
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("fnames1", fileNames1);
		resultMap.put("fnames2", fileNames2);
		
		return resultMap;
	}

	@Override
	public void download(String where, String file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int maxSize = 1024*1024*10;
//		파일 최대 용량
		
		byte[] b = new byte[maxSize];
		
		FileInputStream fis = null;
//		String where = null;
//		where = request.getParameter("where");
//		String file = request.getParameter("file");
		String filename = null;
		String downPath = request.getSession().getServletContext().getRealPath("/account").replaceAll("\\\\", "/");
		String downPath2 = downPath + "/saved";
		System.out.println(downPath);
		System.out.println(downPath2);
		
		if(where.equals("1")) {
			filename =downPath+"/"+file;
			fis = new FileInputStream(filename);
		} else if(where.equals("2")) {
			filename = downPath2+"/"+file;
			fis = new FileInputStream(filename);
		}
		
		String sMimeType = request.getSession().getServletContext().getMimeType(filename);
		String sEncoding = URLEncoder.encode(file,"UTF-8");
		
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
