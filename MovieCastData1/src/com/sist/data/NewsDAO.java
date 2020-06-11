package com.sist.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class NewsDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.210:1521:XE";
	private static NewsDAO dao;

	public NewsDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static NewsDAO newInstance()
	{
		if(dao==null)
			dao=new NewsDAO();
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
	 		NEWS_ID   NOT NULL NUMBER        
			TITLE              VARCHAR2(400) 
			SUBJECT            VARCHAR2(600) 
			THUMBNAIL          VARCHAR2(600) 
			CONTENT            CLOB          
			REGDATE            DATE          
			AUTHOR             VARCHAR2(300) 
	 */
	public void newsDataInsert(NewsVO vo)
	{
		try{
			getConnection();
			String sql="INSERT INTO movie_news "
					+ "VALUES((SELECT NVL(MAX(news_id)+1,1) as news_id FROM movie_news),?,?,?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getThumbnail());
			ps.setString(4, vo.getContent());
			ps.setString(5, vo.getRegdate());
			ps.setString(6, vo.getAuthor());
			
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
