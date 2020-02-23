package com.sist.dao;
import java.sql.*;
import java.util.*;


import com.sist.vo.*;

public class TravelDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static TravelDAO dao;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 싱글턴 (하나의 객체로만 사용)
	public static TravelDAO newInstance()
	{
		if(dao==null)
			dao=new TravelDAO();
		return  dao;
	}
	
	public TravelDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
	public void getConnection()
	{
		try {
			 conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) ps.close();
		}catch (Exception ex) {}
	}
	/*
	 	LOC_NO      NOT NULL NUMBER        
		LOC         NOT NULL VARCHAR2(20)  
		SPOT                 VARCHAR2(50)  
		PEAK_SEASON          VARCHAR2(200) 
		SPECIAL              VARCHAR2(200) 
		FESTIVAL             VARCHAR2(300) 
		ACTIVITY             VARCHAR2(200) 
	 */
	public void insertDomesticAllData(DomesticVO vo){
		try{
			getConnection();
			String sql="INSERT INTO domestic_info "
					+ "VALUES((SELECT NVL(MAX(loc_no)+1,1) FROM domestic_info),?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getLoc());
			ps.setString(2, vo.getSpot());
			ps.setString(3, vo.getPeak_season());
			ps.setString(4, vo.getSpecial());
			ps.setString(5, vo.getFestival());
			ps.setString(6, vo.getActivity());
			
			ps.executeUpdate();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		
	}
	/*
	 	LOC_NO      NOT NULL NUMBER        
		LOC         NOT NULL VARCHAR2(20)  
		WEATHER              VARCHAR2(50)  
		PEAK_SEASON          VARCHAR2(200) 
		TIP                  VARCHAR2(200) 
		VOLT                 VARCHAR2(300) 
		VISA                 VARCHAR2(200) 
		EXCHANGE             VARCHAR2(200) 
	 */
	public void insertHaeoeAllData(HaeoeVO vo){
		try{
			getConnection();
			String sql="INSERT INTO haeoe_info(loc_no,loc,weather,peak_season,tip,volt,visa) "
					+ "VALUES((SELECT NVL(MAX(loc_no)+1,1) FROM haeoe_info),?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getLoc());
			ps.setString(2, vo.getWeather());
			ps.setString(3, vo.getPeak_season());
			ps.setString(4, vo.getTip());
			ps.setString(5, vo.getVolt());
			ps.setString(6, vo.getVisa());
			
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
