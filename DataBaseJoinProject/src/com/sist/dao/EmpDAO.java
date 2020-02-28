package com.sist.dao;
import java.util.*;
import java.util.Date;
import java.sql.*;
// 오라클 연결 => 데이터베이스 프로그램 ==> 단위 RECODE (RECODE=>Column 여러개를 묶어준것)
// => bit => byte => word => record => table
// 					column 값
//					일반데이터형  클래스   => ArrayList
/*
		class A
		{
			int aa;
		}
		
		for(int i=0; i<10; i++)
		{
			A a=new A();
			a.aa=i;
		}
		
		for(int i=0; i<10; i++)
		{
			int a=i;
		}
 */
public class EmpDAO {
	private Connection conn;
	//입출력 => InputStream,OutputStream
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
		// 드라이버 등록 => 한번 수행
		public EmpDAO() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		//연결
		public void getConnection() {
			try {
				conn=DriverManager.getConnection(URL,"hr","happy");
				// conn hr/happy
			}catch (Exception ex) {}
		}
		//해제
		public void disConnection() {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch (Exception ex) {}
			
		}
		
		// 기능 => JOIN
		public ArrayList<EmpVO> empJoinData(){
			ArrayList<EmpVO> list=new ArrayList<EmpVO>();
			try {
				getConnection();
				// SQL 문장
				String sql="SELECT empno,ename,job,hiredate,sal,dname,loc,grade "
						+ "FROM emp,dept,salgrade "
						+ "WHERE emp.deptno=dept.deptno "
						+ "AND sal BETWEEN losal AND hisal";
				// 전송
				ps=conn.prepareStatement(sql);
				// 실행요청
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					EmpVO vo=new EmpVO();
					
					vo.setEmpno(rs.getInt(1));
					vo.setEname(rs.getString(2));
					vo.setJob(rs.getString(3));
					vo.setHiredate(rs.getDate(4));
					vo.setSal(rs.getInt(5));
					vo.getDvo().setDname(rs.getString(6));
					vo.getDvo().setLoc(rs.getString(7));
					vo.getSvo().setGrade(rs.getInt(8));
					
					list.add(vo);
				}
				
				rs.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			
			return list;
		}
}
