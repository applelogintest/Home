package com.sist.dao;

import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpDAO dao=EmpDAO.newInstance();
		ArrayList<EmpVO> list=dao.empAllData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEname()+" "+vo.getDvo().getDname());
		}
	}

}
