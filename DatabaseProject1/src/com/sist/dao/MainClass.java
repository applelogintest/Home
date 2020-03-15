package com.sist.dao;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpDAO dao=new EmpDAO();
		ArrayList<EmpVO> list=dao.empAllData();
		
		for(EmpVO vo:list)
		{
			
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getJob()+" "
					+vo.getMgr()+" "
					+vo.getHiredate().toString()+" "
					+vo.getSal()+" "
					+vo.getComm()+" "
					+vo.getDeptno());
		}
		
		Scanner scan=new Scanner(System.in);
		
		  System.out.print("사번 입력:");
		  int empno=scan.nextInt();
		  
		  EmpVO vo=dao.empDetailData(empno);
		  System.out.println("******검색결과*******");
		  System.out.println(vo.getEmpno()+" "
							  +vo.getEname()+" "
							  +vo.getJob()+" "
							  +vo.getMgr()+" " 
							  +vo.getHiredate().toString()+" "
							  +vo.getSal()+" "
							  +vo.getComm()+" "
							  +vo.getDeptno());
		 
		
		System.out.print("이름 입력:");
		String ename=scan.next();
		list=dao.empFindData(ename.toUpperCase()); 
		
		for(EmpVO vo2:list)
		{
			
			System.out.println(vo2.getEmpno()+" "
					+vo2.getEname()+" "
					+vo2.getJob()+" "
					+vo2.getMgr()+" "
					+vo2.getHiredate().toString()+" "
					+vo2.getSal()+" "
					+vo2.getComm()+" "
					+vo2.getDeptno());
		}
		
		System.out.print("년도 입력:");
		int year=scan.nextInt();
		list=dao.empRangeData(year);
		
		for(EmpVO vo2:list)
		{
			System.out.println(vo2.getEmpno()+" "
					+vo2.getEname()+" "
					+vo2.getJob()+" "
					+vo2.getMgr()+" "
					+vo2.getHiredate().toString()+" "
					+vo2.getSal()+" "
					+vo2.getComm()+" "
					+vo2.getDeptno());
		}
	}
	
	

}
