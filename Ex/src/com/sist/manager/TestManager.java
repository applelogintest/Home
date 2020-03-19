package com.sist.manager;

import java.sql.*;
import java.util.*;

import com.sist.vo.AirTimeVO;
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
	
	public void insertStartDate(AirTimeVO vo,int no){
		try{
			getConnection();
			String sql="INSERT INTO airtime_table(no,plane_id,start_time,start_airport,end_airport) "
					+ "VALUES(no,plane_id,TO_DATE(?,'YYYY/MM/DD HH24:MI'),start_airport,end_aiport)";
			ps=conn.prepareStatement(sql);
			
			
			ps.executeUpdate();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			disConnection();
		}
		
		
	}
}
