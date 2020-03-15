package com.sist.student.dao;
import java.util.*;

import sun.security.provider.DSAPublicKeyImpl;

import java.sql.*;

public class StudentDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록
	public StudentDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	//연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL, "hr","happy");
		}catch (Exception ex) {}
	}
	//연결해제
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		}catch (Exception ex) {}
	}
	
	public ArrayList<StudentVO> stdAllData(int page)
	{
		ArrayList<StudentVO> list=new ArrayList<StudentVO>();
		try{
			getConnection();
			String sql="SELECT hakbun,name,kor,eng,math "
					+ "FROM student "
					+ "ORDER BY 1";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			int i=0;
			int j=0;
			int pageStart=(page*10)-10;
			
			while(rs.next())
			{
				if(i<10 && j>=pageStart)
				{
					StudentVO vo=new StudentVO();
					vo.setHakbun(rs.getInt("hakbun")); // 컬럼명  Mybatis에서 많이 사용
					vo.setName(rs.getString("name"));
					vo.setKor(rs.getInt("kor"));
					vo.setEng(rs.getInt("eng"));
					vo.setMath(rs.getInt("math"));
					list.add(vo);
					i++;
				}
				j++;
			}
			rs.close();
			
		}catch (Exception ex) {
			System.out.println("stdAlldata"+ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		
		return list;
	}
	
	public void stdInsert(StudentVO vo)
	{
		try{
			getConnection();
			String sql="INSERT INTO student(hakbun,name,kor,eng,math,sex) "
					+ "VALUES(std_hakbun_seq.nextval,?,?,?,?,?)";
			
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.getKor());
			ps.setInt(3, vo.getEng());
			ps.setInt(4, vo.getMath());
			ps.setString(5, vo.getSex());
			
			ps.executeUpdate();
			
		}catch (Exception ex) {
			System.out.println("stdInsert"+ex.getMessage());
		}
		finally
		{
			disConnection();
		}
	}
	
	public int stdRowCount()
	{
		int count=0;
		try{
			getConnection();
			String sql="SELECT COUNT(*) "
					+ "FROM student";
			ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
			
		}catch (Exception ex) {
			System.out.println("stdRowCount"+ex.getMessage());
		}
		finally
		{
			disConnection();
		}
		return count;
		
	}
	
	public void stdDelete(int hakbun)
	{
		try{
			getConnection();
			String sql="DELETE FROM student "
					+ "WHERE hakbun=?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, hakbun);
			
			ps.executeUpdate();
			
		}catch (Exception ex) {
			System.out.println("stdInsert"+ex.getMessage());
		}
		finally
		{
			disConnection();
		}
	}
}
