package com.sist.dao;
import java.util.*;
import java.sql.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public FoodDAO() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//연결
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {}
	}
	//연결해제
	public void disConnection(){
		try{
		
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
		}catch (Exception ex) {}
	}
	
	public List<CategoryBean> categoryListDate()
	{
		List<CategoryBean> list=new ArrayList<CategoryBean>();
		try{
			getConnection();
			String sql="SELECT cateno,title,subject,poster "
					+ "FROM category "
					+ "ORDER BY cateno ASC";
			ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CategoryBean vo=new CategoryBean();
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
	
	public CategoryBean categoryInfoData(int cateno)
	{
			CategoryBean vo=new CategoryBean();
			try{
				getConnection();
				String sql="SELECT title,subject FROM category "
						+ "WHERE cateno=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, cateno);
				
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
	
	public List<FoodHouseBean> foodCategoryList(int cateno)
	{
		List<FoodHouseBean> list=new ArrayList<FoodHouseBean>();
		try{
			getConnection();
			String sql="SELECT no,image,title,score,address,tel "
					+ "FROM foodhouse "
					+ "WHERE cno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cateno);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				FoodHouseBean vo=new FoodHouseBean();
				vo.setNo(rs.getInt(1));
				String temp=rs.getString(2);
				temp=temp.substring(0,temp.indexOf("^"));
				
				vo.setImage(temp);
				vo.setTitle(rs.getString(3));
				vo.setScore(rs.getDouble(4));
				vo.setAddress(rs.getString(5));
				vo.setTel(rs.getString(6));
				
				list.add(vo);
			}
			rs.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			disConnection();
		}
		return list;
	}
	
	/*
	NO      NOT NULL NUMBER         
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
	
	[
       ['Task', 'Hours per Day'],
       ['Work',     11],
       ['Eat',      2],
       ['Commute',  2],
       ['Watch TV', 2],
       ['Sleep',    7]
    ]
*/
	public FoodHouseBean foodDetailData(int no)
	{
		FoodHouseBean vo=new FoodHouseBean();
		try{
			getConnection();
			String sql="SELECT title,score,address,tel,type,price,image,good,bad,soso,no "
					+ "FROM foodhouse "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setTitle(rs.getString(1));
			vo.setScore(rs.getDouble(2));
			vo.setAddress(rs.getString(3));
			vo.setTel(rs.getString(4));
			vo.setType(rs.getString(5));
			vo.setPrice(rs.getString(6));
			vo.setImage(rs.getString(7));
			int good=rs.getInt(8);
			int bad = rs.getInt(9);
			int soso = rs.getInt(10);
			vo.setNo(rs.getInt(11));
			
			String temp="[";
			temp+="['리뷰','현황'],['좋아요',"+good+"],['괜찮다',"+soso+"],['별로',"+bad+"]";
			temp+="]";
			
			vo.setTag(temp);
			
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
