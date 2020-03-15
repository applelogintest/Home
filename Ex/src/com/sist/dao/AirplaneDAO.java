package com.sist.dao;
import java.util.*;

import com.sist.vo.AirplaneVO;

import java.sql.*;

public class AirplaneDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String  URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public AirplaneDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch (Exception ex) {
		}
	}
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public void disConnection(){
		try{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertAirplane(AirplaneVO vo){
		try{
			getConnection();
			String sql="INSERT INTO airplane VALUES("
					+ "ar_planeId_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getFirst());
			ps.setInt(2, vo.getBusiness());
			ps.setInt(3, vo.getEconomy());
			ps.setInt(4, vo.getSizeType());
			
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
