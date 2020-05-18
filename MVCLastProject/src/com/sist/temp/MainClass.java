package com.sist.temp;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentDAO dao=new StudentDAO();
		StudentVO vo=dao.studentInfoData(1);
		System.out.println("이름 :"+vo.getName());
		System.out.println("국어점수 :"+vo.getKor());
		System.out.println("영어점수 :"+vo.getEng());
		System.out.println("수학점수 :"+vo.getMath());

	}

}
