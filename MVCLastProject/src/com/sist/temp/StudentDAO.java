package com.sist.temp;
import java.util.*;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import java.sql.*;

public class StudentDAO {
	private Connection conn;
	private CallableStatement cs; // Procedure 호출할때 사용하는  Statement
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public StudentDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {	}
	}
	public void disConnection(){
		try{
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		}catch (Exception ex) {}
	}
	
	public StudentVO studentInfoData(int no)
	{
		StudentVO vo=new StudentVO();
		try{
			getConnection();
			String sql="{CALL pro_test_select(?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			// String name => :name (X) 자바에서는 없음  그래서 registerOutParameter를 사용
			cs.registerOutParameter(2, OracleTypes.VARCHAR );
			cs.registerOutParameter(3, OracleTypes.INTEGER );
			cs.registerOutParameter(4, OracleTypes.INTEGER );
			cs.registerOutParameter(5, OracleTypes.INTEGER );
			//  OracleTypes.CURSOR => ResultSet
			// 실행 => 프로시저는 모든 SQL => executeUpdate() 
			cs.executeUpdate();
			
			String name=cs.getString(2);
			int kor=cs.getInt(3);
			int eng=cs.getInt(4);
			int math=cs.getInt(5);
			
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMath(math);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo; 
	}
}
