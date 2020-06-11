package com.sist.data;
import java.sql.*;
import java.util.*;

public class CastDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static CastDAO dao;

	public CastDAO(){
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
	
	public void castDataInsert(CastVO vo)
	{
		try{
			getConnection();
			String sql="INSERT INTO cast "
					+ "VALUES(?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, vo.getCast_id());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getBirth());
			ps.setString(4, vo.getThumbnail());
			ps.setString(5, vo.getProfile());
			ps.setString(6, vo.getReward());
			
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
