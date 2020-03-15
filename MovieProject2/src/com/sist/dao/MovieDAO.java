package com.sist.dao;
import java.util.*;

import com.sist.vo.*;
import com.sun.javafx.geom.RectangularShape;

import java.sql.*;

public class MovieDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static MovieDAO dao;
	
	public MovieDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	//싱글턴 => 접속자마다 한개의 DAO만 사용할 수 있다
	/*
	 * 	디자인 패턴
	 *  ========
	 *    싱글턴 =====> Spring
	 *    팩토리 
	 *    MV  => JSP Model View
	 *    MVC => JSP Model View Controller
	 *    옵져버 => 시스템이 감지해서 처리
	 *    어뎁터(POJO) => 자동 형변환
	 */
	public static MovieDAO newInstance()
	{
		if(dao==null)
			dao=new MovieDAO();
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
	/*
	 	private int mno;
		private String title;
		private String poster;
		private double score;
		private String genre;
		private String regdate;
		private String time;
		
		MNO      NOT NULL NUMBER         
		TITLE    NOT NULL VARCHAR2(1000) 
		POSTER   NOT NULL VARCHAR2(2000) 
		SCORE             NUMBER(4,2)    
		GENRE    NOT NULL VARCHAR2(100)  
		REGDATE           VARCHAR2(100)  
		TIME              VARCHAR2(10)   
		GRADE    NOT NULL VARCHAR2(100)  
		DIRECTOR          VARCHAR2(200)  
		ACTOR             VARCHAR2(200)  
		STORY             CLOB           
		TYPE              NUMBER  
	 */
	// 저장
	public void movieInsert(MovieVO vo)
	{
		try{
			getConnection();
			String sql="INSERT INTO movie VALUES("
					+ "(SELECT NVL(MAX(mno)+1,1) FROM movie),"
					+ "?,?,?,?,?,?,?,?,?,?,?)";
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPoster());
			ps.setDouble(3,vo.getScore());
			ps.setString(4, vo.getGenre());
			ps.setString(5, vo.getRegdate());
			ps.setString(6, vo.getTime());
			ps.setString(7, vo.getGrade());
			ps.setString(8, vo.getDirector());
			ps.setString(9, vo.getActor());
			ps.setString(10, vo.getStory());
			ps.setInt(11, vo.getType());
			
			ps.executeUpdate();
			
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
	}
	
	public ArrayList<MovieVO> movieListData(int page,int type)
	{
		ArrayList<MovieVO> list=new ArrayList<MovieVO>();
		try{
			getConnection();
			int rowSize=12;
			int start=(page*rowSize)-(rowSize-1);
			int end=page*rowSize;
			String sql="";
			if(type<3)
			{
				sql="SELECT mno,title,poster,score,regdate,num "
						+ "FROM (SELECT mno,title,poster,score,regdate,rownum as num "
						+ "FROM (SELECT mno,title,poster,score,regdate "
						+ "FROM movie WHERE type=? ORDER BY mno ASC)) "
						+ "WHERE num BETWEEN ? AND ?";
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, type);
				ps.setInt(2, start);
				ps.setInt(3, end);
			}
			else
			{
				sql="SELECT mno,title,poster,score,regdate "
						+ "FROM movie WHERE type=? ORDER BY mno ASC";
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, type);
				
			}
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MovieVO vo=new MovieVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setScore(rs.getDouble(4));
				vo.setRegdate(rs.getString(5));
				
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
	
	public void newsInsert(NewsVO vo)
	{
		try{
			getConnection();
			String sql="INSERT INTO news VALUES("
					+ "?,?,?,?,?,?)";
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getPoster());
			ps.setString(3, vo.getLink());
			ps.setString(4, vo.getAuthor());
			ps.setString(5, vo.getRegdate());
			ps.setString(6, vo.getContent());
			
			
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
	 *  TITLE   NOT NULL VARCHAR2(4000) 
		POSTER  NOT NULL VARCHAR2(1000) 
		LINK    NOT NULL VARCHAR2(1000) 
		AUTHOR  NOT NULL VARCHAR2(200)  
		REGDATE NOT NULL VARCHAR2(100)  
		CONTENT NOT NULL CLOB     
	 */
	// 망고 플레이트 => JDBC => DBCP,View
	// XML,JSON => 자바스크립트 객체 표현법
	/*
	 	var member=[{name:"hong",sex:"man",age:30},{name:"hong",sex:"man",age:30}]
		=> member[0].name
	 */
	public ArrayList<NewsVO> newsListData(int page)
	{
		ArrayList<NewsVO> list=new ArrayList<NewsVO>();
		try{
			getConnection();
			int rowSize=10;
			/*
			 *  1p 1 10
			 *  2p 11 20
			 *  3p 21 30 ==> rownum => 1
			 */
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			
			String sql="SELECT title,poster,link,author,regdate,content "
					+ "FROM (SELECT title,poster,link,author,regdate,content,rownum as num "
					+ "FROM (SELECT title,poster,link,author,regdate,content "
					+ "FROM news)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				NewsVO vo= new NewsVO();
				vo.setTitle(rs.getString(1));
				vo.setPoster(rs.getString(2));
				vo.setLink(rs.getString(3));
				vo.setAuthor(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				vo.setContent(rs.getString(6));
				
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
	
	public int newTotalPage()
	{
		int total=0;
		try{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM news";
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
	/*
	 *  MNO      NOT NULL NUMBER         
		TITLE    NOT NULL VARCHAR2(1000) 
		POSTER   NOT NULL VARCHAR2(2000) 
		SCORE             NUMBER(4,2)    
		GENRE    NOT NULL VARCHAR2(100)  
		REGDATE           VARCHAR2(100)  
		TIME              VARCHAR2(10)   
		GRADE    NOT NULL VARCHAR2(100)  
		DIRECTOR          VARCHAR2(200)  
		ACTOR             VARCHAR2(200)  
		STORY             CLOB           
	 */
	public MovieVO newsDetailData(int mno)
	{
		MovieVO vo=new MovieVO();
		try{
			getConnection();
			String sql="SELECT mno,title,poster,score,genre,regdate,time,grade,director,actor,story "
					+ "FROM movie "
					+ "WHERE mno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, mno);
			
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setScore(rs.getDouble(4));
			vo.setGenre(rs.getString(5));
			vo.setRegdate(rs.getString(6));
			vo.setTime(rs.getString(7));
			vo.setGrade(rs.getString(8));
			vo.setDirector(rs.getString(9));
			vo.setActor(rs.getString(10));
			vo.setStory(rs.getString(11));
			
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
}
