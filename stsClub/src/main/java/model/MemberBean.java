package model;

import java.sql.Date;

public class MemberBean {
	public class SEX{
		public static final String M = "M";
		public static final String F = "F";
	}
	public class GRADE{
		public static final String NEW = "N";
		public static final String MEMBER = "B";
		public static final String MANEGER = "G";
	}
	
	
	private String name;
	private String id; 
	private String passwd; 
	private Date birth;
	private String email; 
	private String grade; 
	private String sex; 
	private String tel; 
	private int postcode; 
	private String address1; 
	private String address2; 
	private Date reg_date; 

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address) {
		this.address1 = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address) {
		this.address2 = address;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}	
