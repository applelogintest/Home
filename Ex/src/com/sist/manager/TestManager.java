package com.sist.manager;

import java.sql.*;
import java.util.*;

import com.sist.vo.AirplaneVO;

public class TestManager {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	public TestManager() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (Exception ex) {
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void insertAirplane(AirplaneVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO airplane VALUES(air_planeId_seq.nextval,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getFirst());
			ps.setInt(2, vo.getBusiness());
			ps.setInt(3, vo.getEconomy());
			ps.setInt(4, vo.getSizeType());
			ps.setString(5, vo.getAirline());

			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	public void insertStartDate(String startDate){
		try{
			getConnection();
			String sql="INSERT INTO test3(dt) VALUES(TO_DATE(?,'YYYY/MM/DD HH24:MI'))";
			ps=conn.prepareStatement(sql);
			ps.setString(1, startDate);
			
			ps.executeUpdate();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			disConnection();
		}
		
		
	}
}
