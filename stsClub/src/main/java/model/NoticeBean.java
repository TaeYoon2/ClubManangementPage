package model;

import java.sql.Timestamp;

public class NoticeBean {
	private int no;
	private String id;
	private String subject;
	private String content;
	private Timestamp reg_date;
	private int readcount;
	private String orifile;
	private String savefile;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getOrifile() {
		return orifile;
	}
	public void setOrifile(String orifile) {
		this.orifile = orifile;
	}
	public String getSavefile() {
		return savefile;
	}
	public void setSavefile(String savefile) {
		this.savefile = savefile;
	}
	
}
