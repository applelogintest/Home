package com.sist.dao;
import java.util.*;

import com.sist.vo.BoardVO;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class FreeBoardDAO {
	private Connection conn;
	private CallableStatement cs; //Procedure 호출시에 전송 객체 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public FreeBoardDAO(){
		try{
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	// SqlSession session=ssf.openSession();
	// 연결
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL, "hr", "happy");
		}catch (Exception ex) {}
	}
	
	// session.close()
	// 해제
	public void disConnection(){
		try{
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		}catch (Exception ex) {}
	}
	
	/*
	 * CREATE OR REPLACE PROCEDURE boardListData(
	    pStart NUMBER,
	    pEnd NUMBER,
	    pResult OUT SYS_REFCURSOR
	
	 */
	// session.selectList()
	public List<BoardVO> freeboardListData(int page)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		try{
			getConnection();
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=(rowSize*page);
			
			String sql="{CALL boardListData(?,?,?)}";
			cs=conn.prepareCall(sql);// 프로시저 호출
			
			// ?에 값을 채운다
			cs.setInt(1, start);
			cs.setInt(2, end);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			// 실행 요청
			cs.executeUpdate(); // boardListData(?,?,?) 호출
			
			// Object (자바에서 CURSOR가 없기때문에 오브젝트로 받음)
			// 결과값
			ResultSet rs=(ResultSet)cs.getObject(3); // CURSOR => ResultSet
			
			while(rs.next())
			{
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
				
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
	
	// 총페이지 구하기
	/*
	 	CREATE OR REPLACE PROCEDURE boardTotalPage(
    	pTotal OUT NUMBER
	 */
	public int freeboardTotalPage(){
		int total=0;
		try{
			getConnection();
			String sql="{CALL boardTotalPage(?)}";
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.INTEGER);// 정수 저장할 수 있는 공간(메모리) 만들어라
			
			// 실행
			cs.executeUpdate();
			
			// 메모리에서 결과값을 읽어 온다
			total=cs.getInt(1);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return total;
	}
	
	// INSERT
	/*
 	CREATE OR REPLACE PROCEDURE boardInsert(
    pName board.name%TYPE,
    pSubject board.subject%TYPE,
    pContent board.content%TYPE,
    pPwd board.pwd%TYPE
)
 */
	public void freeboardInsert(BoardVO vo){
		try{
			getConnection();
			String sql="{CALL boardInsert(?,?,?,?)}";
			cs=conn.prepareCall(sql);
			// ? 에 값을 채운다
			cs.setString(1, vo.getName());
			cs.setString(2, vo.getSubject());
			cs.setString(3, vo.getContent());
			cs.setString(4, vo.getPwd());
			
			cs.executeUpdate();
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	
	/*
	 	CREATE OR REPLACE PROCEDURE freeboardInfoData(
	    pNo board.no%TYPE,
	    pType NUMBER,
	    pResult OUT SYS_REFCURSOR
)
	 */
	// 상세보기, 수정하기
	public BoardVO freeboardInfoData(int no,int type)
	{
		BoardVO vo=new BoardVO();
		try{
			getConnection();
			String sql="{CALL freeboardInfoData(?,?,?)}";
			cs=conn.prepareCall(sql);
			
			cs.setInt(1, no);
			cs.setInt(2, type);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			// 실행
			cs.executeUpdate();
			
			ResultSet rs=(ResultSet)cs.getObject(3);
			
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo; 
	}
	
	
	/*
	 	CREATE OR REPLACE PROCEDURE freeboardUpdate(
	    pNo board.no%TYPE,
	    pName board.name%TYPE,
	    pSubject board.subject%TYPE,
	    pContent board.content%TYPE,
	    pPwd board.pwd%TYPE,
	    pResult OUT VARCHAR2
	)
	 */
	public boolean freeboardUpdate(BoardVO vo)
	{
		boolean bCheck=false;
		try{
			getConnection();
			String sql="{CALL freeboardUpdate(?,?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, vo.getNo());
			cs.setString(2, vo.getName());
			cs.setString(3, vo.getSubject());
			cs.setString(4, vo.getContent());
			cs.setString(5, vo.getPwd());
			cs.registerOutParameter(6, OracleTypes.VARCHAR);
			
			cs.executeUpdate();
			String result=cs.getString(6);
			
			bCheck=Boolean.parseBoolean(result);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
	/*
	 	CREATE OR REPLACE PROCEDURE freeboardDelete(
	    pNo board.no%TYPE,
	    pPwd board.pwd%TYPE,
	    pResult OUT VARCHAR2
	    
	 */
	public boolean freeboardDelete(int no,String pwd)
	{
		boolean bCheck=false;
		try{
			getConnection();
			String sql="{CALL freeboardDelete(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setString(2, pwd);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			
			cs.executeUpdate();
			String result=cs.getString(3);
			
			bCheck=Boolean.parseBoolean(result);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
	
}












