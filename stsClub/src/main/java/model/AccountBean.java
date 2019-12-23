package model;

import java.sql.Date;

public class AccountBean {
	int no;
	String inout;
	int carrot;
	Date tran_date;
	String content;
	String orifile;
	String savefile;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getInout() {
		return inout;
	}
	public void setInout(String inout) {
		this.inout = inout;
	}
	public int getCarrot() {
		return carrot;
	}
	public void setCarrot(int carrot) {
		this.carrot = carrot;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
