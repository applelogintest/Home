package com.sist.dao;

import java.sql.*;
import java.util.*;

public class MovieDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public MovieDAO() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//연결
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {}
	}
	//연결해제
	public void disConnection(){
		try{
		
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
		}catch (Exception ex) {}
	}
	
	public int movieTotalPage(){
		int total=0;
		try{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/9.0) FROM movie";
			ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			total=rs.getInt(1);
			
			rs.close();
					
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return total;
	}
	
	public List<MovieBean> movieListData(int page)
	{
		List<MovieBean> list=new ArrayList<MovieBean>();
		
		try{
			getConnection();
			String sql="SELECT poster,title,num "
					+ "FROM (SELECT poster,title,rownum as num "
					+ "FROM (SELECT poster,title "
					+ "FROM movie ORDER BY mno ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			
			int rowSize=9;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				MovieBean vo=new MovieBean();
				vo.setPoster(rs.getString(1));
				vo.setTitle(rs.getString(2));
				
				list.add(vo);
			}
			rs.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			disConnection();
		}
		
		return list;
	}
}
