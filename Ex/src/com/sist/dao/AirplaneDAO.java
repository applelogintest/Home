package com.sist.dao;

import java.util.*;

import com.sist.manager.AirplaneManager;
import com.sist.vo.AirSeatVO;
import com.sist.vo.AirTimeVO;
import com.sist.vo.AirplaneVO;

import java.sql.*;
import java.text.DecimalFormat;

public class AirplaneDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.210:1521:XE";

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
/*
 	private int plane_id;
	private int first;
	private int business;
	private int economy;
	private int sizeType;
	private String airline;
 */
	public void insertAirplane(AirplaneVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO airplane VALUES(ap_planeId_seq.nextval,?,?,?,?,?)";
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
			String sql = "SELECT plane_id, first, business, economy,seattype,airline "
					+ "FROM airplane";
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

	public void insertAirSeat(List<AirplaneVO> plane) {
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
				// 메서드를 만드는데, 입력받는 것이 k하나이고, 랜덤으로 숫자 하나를 만든다
				// int getPriceOfPlane(int k) -> k == 2 -> 이코노미에 대한 가격이 나옵니다.
				// 100000 int tmpPrice = 100000;
				firstLiteral = 'A';
				int tmpPrice = getRandomPrice(k);
				System.out.println("arr[k] : " + arr[k]);
				if (arr[k] != 0) {
					System.out.println("pos ++");
					pos++;
				}
				for (int j = 0; j < arr[k]; j++) {
					// System.out.println("plane selected! size : " +
					// vo.getFirst());
					/*
					 * System.out.println("correct id : " + vo.getPlane_id());
					 */
					AirSeatVO svo = new AirSeatVO();
					svo.setPlane_id(vo.getPlane_id());
					svo.setPrice(Integer.toString(tmpPrice)); 
					svo.setType(k);
					if (j % (vo.getSizeType() * 2 + 4) == 0 && j != 0) {
						firstLiteral = 'A';
						pos++;
					}

					svo.setSeat_no("" + (char) firstLiteral + (pos));
					firstLiteral++;
					
					// insert를 하자
					insertSeat(svo);
					
				}
			}
		}
	}
	
	public int getRandomPrice(int type) {
		int res = 0;
		switch (type) {
		case 0: // 퍼스트
			res = (int) (Math.random() * 100000) + 300000;
			break;
		case 1: // 비즈니스
			res = (int) (Math.random() * 100000) + 200000;
			break;
		case 2: // 이코노미
			res = (int) (Math.random() * 50000) + 50000;
			break;
		}
		return res;
	}

	
	//좌석
	public void insertSeat(AirSeatVO vo) {
		try {
			// System.out.println("insertSeat start");
			getConnection();
			String sql = "INSERT INTO air_seat(no, seat_no ,plane_id, type, price) "
					+ "VALUES(as_no_seq.nextval, ?, ?, ?, TO_CHAR(REPLACE(?,SUBSTR(?,-3),'000'),'999,999'))";
			ps = conn.prepareStatement(sql);
			// System.out.println("insertSeat mid");
			
			ps.setString(1, vo.getSeat_no());
			ps.setInt(2, vo.getPlane_id());
			ps.setInt(3, vo.getType());
			ps.setString(4, vo.getPrice());
			ps.setString(5, vo.getPrice());
			ps.executeUpdate();
			
			// System.out.println("insertSeat end");
		} catch (Exception e) {
			System.out.println("wrong id : " + vo.getPlane_id());
			System.out.println(vo.getSeat_no());
			System.out.println(vo.getType());
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public int calArr(int[] arr, int k) {
		if (k == 0) {
			return 0;
		} else if (k == 1) {
			return arr[0];
		} else {
			return arr[0] + arr[1];
		}
	}

	// 비행기의 좌석 갯수 얻어오기
	public List<AirplaneVO> airplaneSeatData() {
		List<AirplaneVO> list = new ArrayList<AirplaneVO>();
		try {
			getConnection();
			String sql = "SELECT first,business,economy,first+business+economy as total " + "FROM airplane";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AirplaneVO vo = new AirplaneVO();
				vo.setFirst(rs.getInt(1));
				vo.setBusiness(rs.getInt(2));
				vo.setEconomy(rs.getInt(3));
				vo.setTotal(rs.getInt(4));

				list.add(vo);
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

		return list;
	}

<<<<<<< HEAD
	
	// 비행기의 좌석 갯수 얻어오기
	public List<AirplaneVO> getAirplaneId() {
		List<AirplaneVO> list = new ArrayList<AirplaneVO>();
=======
	public int getRandomPrice(int type) {
		int res = 0;
		switch (type) {
		case 0: // 퍼스트
			res = (int) (Math.random() * 100000) + 300000;
			break;
		case 1: // 비즈니스
			res = (int) (Math.random() * 100000) + 200000;
			break;
		case 2: // 이코노미
			res = (int) (Math.random() * 50000) + 50000;
			break;
		}
		return res;
	}

	// air_time 테이블에서 id와 출발일자 가져와서 테스트 하기위한 메소드
	public List<AirTimeVO> getAirtimeTest() {
		List<AirTimeVO> list = new ArrayList<AirTimeVO>();
		try {
			getConnection();
			String sql = "SELECT plane_id, SUBSTR(start_time,7,2) "
					+ "FROM air_time "
					+ "ORDER BY plane_id ASC";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AirTimeVO vo=new AirTimeVO();
				vo.setPlane_id(rs.getInt(1));
				vo.setStart_time(rs.getString(2));

				list.add(vo);
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

		return list;
	}

	// 비행기의 좌석 갯수 얻어오기
	public List<AirplaneVO> getAirplaneAllData() {
		List<AirplaneVO> list = new ArrayList<AirplaneVO>();
		try {
			getConnection();
			String sql = "SELECT plane_id,first,business,economy,sizeType,airline " + "FROM airplane "
					+ "ORDER BY plane_id ASC";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AirplaneVO vo = new AirplaneVO();
				vo.setPlane_id(rs.getInt(1));
				vo.setSizeType(rs.getInt(2));

				list.add(vo);
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

		return list;
	}

	public List<AirSeatVO> getSeatData(int id) {
		List<AirSeatVO> list = new ArrayList<AirSeatVO>();
		try {
			getConnection();
			String sql = "SELECT no, price " + "FROM air_seat " + "WHERE plane_id = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AirSeatVO vo = new AirSeatVO();
				vo.setSeat_no(rs.getString(1));
				vo.setPrice(rs.getString(2));

				list.add(vo);
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

		return list;
	}

	public void insertAirtime(AirTimeVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO air_time VALUES(at_no_seq.nextval,?," 
						+ "TO_DATE(?,'YYYY-MM-DD HH24:MI'),"
						+ "TO_DATE(TO_CHAR(TO_DATE(?,'YYYY-MM-DD HH24:MI') + 1/22,'YYYY-MM-DD HH24:MI'),'YYYY-MM-DD HH24:MI'),?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getPlane_id());
			ps.setString(2, vo.getStart_time());
			ps.setString(3, vo.getStart_time());
			ps.setString(4, vo.getStart_airport());
			ps.setString(5, vo.getEnd_airport());

			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}

	}
}
