package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.sist.manager.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static FoodDAO dao;
	// DAO를 각 사용자당 한개만 사용이 가능하게 만든다
	public static FoodDAO newInstance(){
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	
	// <dataSource type="POOLED"> ==> JSoup, JAXP, JAXB, SIMPLE-JSON,CSV
	/*public FoodDAO()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}*/
	public void getConnection(){
		try{
			/*conn=DriverManager.getConnection(URL,"hr","happy");*/
			Context init=new InitialContext(); //JNDI reg
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	//반환
	public void disConnection(){
		// 닫기 => ps,conn
		try{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch (Exception ex) {}
		
	}
	
	// 기능(오라클)
	/*
	 * private int cateno;
		private String title;
		private String subject;
		private String poster;
		private String link;
	 */
	public void catagoryInsert(CategoryVO vo)
	{
		try{
			getConnection();
			String sql="INSERT INTO category VALUES(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getCateno());
			ps.setString(2,vo.getTitle());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getPoster());
			ps.setString(5, vo.getLink());
			
			ps.executeUpdate();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	
	public ArrayList<CategoryVO> categoryAllData()
	{
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try{
			getConnection();
			String sql="SELECT cateno,title,subject,poster "
					+ "FROM category "
					+ "ORDER BY cateno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CategoryVO vo=new CategoryVO();
				vo.setCateno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setPoster(rs.getString(4));
				
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
	
	/*
	 *  NO      NOT NULL NUMBER         
		CNO              NUMBER         
		TITLE   NOT NULL VARCHAR2(200)  
		SCORE   NOT NULL NUMBER(2,1)    
		ADDRESS NOT NULL VARCHAR2(200)  
		TEL     NOT NULL VARCHAR2(30)   
		TYPE    NOT NULL VARCHAR2(100)  
		PRICE            VARCHAR2(100)  
		IMAGE   NOT NULL VARCHAR2(2000) 
		GOOD             NUMBER         
		SOSO             NUMBER         
		BAD              NUMBER         
		TAG              VARCHAR2(2000) 
	 */
	public void foodHouseInsert(FoodHouseVO vo)
	{
		try{
			getConnection();
			String sql="INSERT INTO foodhouse VALUES("
					+ "foodhouse_no_seq.nextval,?,?,?,?,"
					+ "?,?,?,?,?,?,?,'none')";
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1,vo.getCno());
			ps.setString(2,vo.getTitle());
			ps.setDouble(3, vo.getScore());
			
			ps.setString(4, vo.getAddress());
			ps.setString(5, vo.getTel());
			ps.setString(6, vo.getType());
			ps.setString(7, vo.getPrice());
			ps.setString(8, vo.getImage());
			
			ps.setInt(9, vo.getGood());
			ps.setInt(10, vo.getSoso());
			ps.setInt(11, vo.getBad());
			
			ps.executeUpdate();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	
	public ArrayList<FoodHouseVO> foodHouseList(int cno)
	{
		ArrayList<FoodHouseVO> list=new ArrayList<FoodHouseVO>();
		try{
			getConnection();
			String sql="SELECT image,title,score,address,no "
					+ "FROM foodhouse "
					+ "WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodHouseVO vo=new FoodHouseVO();
				String img=rs.getString(1);
				vo.setImage(img.substring(0,img.indexOf("^")));
				vo.setTitle(rs.getString(2));
				vo.setScore(rs.getDouble(3));
				vo.setAddress(rs.getString(4));
				vo.setNo(rs.getInt(5));
				
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
	
	public CategoryVO categoryInfoData(int cno)
	{
		CategoryVO vo=new CategoryVO();
		try{
			getConnection();
			String sql="SELECT title,subject "
					+ "FROM category "
					+ "WHERE cateno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setTitle(rs.getString(1));
			vo.setSubject(rs.getString(2));
			
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
	
	public FoodHouseVO foodDetailData(int no)
	{
		FoodHouseVO vo=new FoodHouseVO();
		try{
			getConnection();
			String sql="SELECT image,title,score,address,tel,type,price,good,soso,bad "
					+ "FROM foodhouse "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setImage(rs.getString(1));
			vo.setTitle(rs.getString(2));
			vo.setScore(rs.getDouble(3));
			vo.setAddress(rs.getString(4));
			vo.setTel(rs.getString(5));
			vo.setType(rs.getString(6));
			vo.setPrice(rs.getString(7));
			vo.setGood(rs.getInt(8));
			vo.setSoso(rs.getInt(9));
			vo.setBad(rs.getInt(10));
			
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
	//서울특별시 강남구 도산대로53길 30 지번 서울시 강남구 신사동 665-9
	public ArrayList<FoodHouseVO> foodLocationData(String loc)
	{
		ArrayList<FoodHouseVO> list=new ArrayList<FoodHouseVO>();
		try{
			getConnection();
			String sql="SELECT image,title,score,address,tel,type,price,rownum "
					+ "FROM (SELECT image,title,score,address,tel,type,price "
					+ "FROM SELECT image,title,score,address,tel,type,price "
					+ "WHERE address LIKE '%'||?||'%' "
					+ "ORDER BY score DESC) "
					+ "WHERE rownum<=5";
			ps=conn.prepareStatement(sql);
			ps.setString(1, loc);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodHouseVO vo=new FoodHouseVO();
				vo.setImage(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setScore(rs.getDouble(3));
				vo.setAddress(rs.getString(4));
				vo.setTel(rs.getString(5));
				vo.setType(rs.getString(6));
				vo.setPrice(rs.getString(7));
				
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
