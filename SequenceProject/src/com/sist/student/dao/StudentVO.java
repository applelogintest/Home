package com.sist.student.dao;
import java.util.*;
/*
 	hakbun not null number
 	name   not null varchar2(30)
 	kor    not null number
 	eng    not null number
 	math   not null number
 	regdate         date
 	sex             varchar2(10)
 */
public class StudentVO {
	private int hakbun;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private Date date;
	private String sex;
	
	public int getHakbun() {
		return hakbun;
	}
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
