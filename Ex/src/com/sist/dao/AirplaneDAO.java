package com.sist.dao;
<<<<<<< HEAD

import java.util.*;

import com.sist.vo.AirSeatVO;
import com.sist.vo.AirplaneVO;

import java.sql.*;

public class AirplaneDAO {
	/* */
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	public AirplaneDAO() {
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

	public ArrayList<AirplaneVO> airplaneAllData() {
		ArrayList<AirplaneVO> list = new ArrayList<AirplaneVO>();

		try {
			getConnection();
			String sql = "SELECT plane_id, first, business, economy," + "sizetype, airline " + "FROM airplane";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AirplaneVO vo = new AirplaneVO();
				vo.setPlane_id(rs.getInt(1));
				vo.setFirst(rs.getInt(2));
				vo.setBusiness(rs.getInt(3));
				vo.setEconomy(rs.getInt(4));
				vo.setSizeType(rs.getInt(5));
				vo.setAirline(rs.getString(6));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		System.out.println("get all data");
		return list;
	}

	public void insertAirSeat(ArrayList<AirplaneVO> plane) {
		System.out.println("plane size : " + plane.size());
		for (AirplaneVO vo : plane) {
			System.out.println("plane id : " + vo.getPlane_id());
			char firstLiteral = 'A';
			// 1. first 좌석을 만들고 insert한다
			int[] arr = new int[3];
			arr[0] = vo.getFirst();
			arr[1] = vo.getBusiness();
			arr[2] = vo.getEconomy();
			int pos = 0;
			for (int k = 0; k < 3; k++) {
				firstLiteral = 'A';
				pos++;
				for (int j = 0; j < arr[k]; j++) {
					//System.out.println("plane selected! size : " + vo.getFirst());
					/*System.out.println("correct id : " + vo.getPlane_id());*/
					AirSeatVO svo = new AirSeatVO();
					svo.setPlane_id(vo.getPlane_id());
					svo.setPrice("20");
					svo.setType(k);
					//System.out.println("first literal : " + firstLiteral);
					if (j % (vo.getSizeType() * 2 + 4) == 0 && j != 0) {
						firstLiteral = 'A';
						pos++;
					}
					
					svo.setNo("" + (char) firstLiteral + (pos));
					firstLiteral++;
					// svo가 완성이 되었다
					// insert를 하자
					/*
					 * try { Thread.sleep(100); insertSeat(svo);
					 * System.out.println("plane seat is created - " +
					 * svo.getNo()); } catch (InterruptedException e) { // TODO
					 * Auto-generated catch block e.printStackTrace(); }
					 */
					insertSeat(svo);
					//System.out.println("plane seat is created - " + svo.getNo());
				}
			}
		}
	}

	public void insertSeat(AirSeatVO vo) {
		try {
			//System.out.println("insertSeat start");
			getConnection();
			String sql = "INSERT INTO air_seat(plane_id, no, type, price) " + "VALUES(?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			//System.out.println("insertSeat mid");
			ps.setInt(1, vo.getPlane_id());
			//System.out.println("id : " + vo.getPlane_id());
			ps.setString(2, vo.getNo());
			//System.out.println("no : " + vo.getNo());
			ps.setInt(3, vo.getType());
			//System.out.println("type : " + vo.getType());
			ps.setString(4, vo.getPrice());
			//System.out.println("price : " + vo.getPrice());
			ps.executeUpdate();
			//System.out.println("insertSeat end");
		} catch (Exception e) {
			System.out.println("wrong id : " + vo.getPlane_id());
			System.out.println(vo.getNo());
			System.out.println(vo.getType());
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	public int calArr(int[] arr, int k){
		if(k == 0){
			return 0;
		}else if(k == 1){
			return arr[0];
		}else{
			return arr[0] + arr[1];
=======
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
>>>>>>> refs/remotes/origin/master
		}
	}
}
