package com.sist.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ReviewDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.210:1521:XE";
	private static CastDAO dao;

	public ReviewDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static CastDAO newInstance()
	{
		if(dao==null)
			dao=new CastDAO();
		return dao;
	}

	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {}

	}

	public void disConnection(){
		try{
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();

		}catch (Exception ex) {}

	}
	/*
	 		REVIEW_ID NOT NULL NUMBER        
			MOVIE_ID           NUMBER        
			USER_ID            VARCHAR2(400) 
			RATE               NUMBER        
			CONTENT            CLOB          
			REGDATE            VARCHAR2(300) 
	 */
	public void reviewDataInsert(ReviewVO vo)
	{
		try{
			getConnection();
			String sql="INSERT INTO review "
					+ "VALUES((SELECT NVL(MAX(review_id)+1,1) as review_id FROM review),?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, vo.getMovie_id());
			ps.setString(2, vo.getUser_id());
			ps.setInt(3,vo.getRate());
			ps.setString(4, vo.getContent());
			ps.setString(5, vo.getRegdate());
			
			ps.executeUpdate();
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		
	}
	
}
