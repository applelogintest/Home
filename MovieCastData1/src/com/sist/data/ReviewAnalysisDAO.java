package com.sist.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewAnalysisDAO {
		private Connection conn;
		private PreparedStatement ps;
		private final String URL="jdbc:oracle:thin:@211.238.142.210:1521:XE";
		private static NewsDAO dao;

		public ReviewAnalysisDAO(){
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public static NewsDAO newInstance()
		{
			if(dao==null)
				dao=new NewsDAO();
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
		
		
		public List<ReviewAnalysisVO> reviewList()
		{
			List<ReviewAnalysisVO> list=new ArrayList<ReviewAnalysisVO>();
			try{
				getConnection();
				String sql="SELECT * FROM review";
				
				
				ps=conn.prepareStatement(sql);
				//ps.setInt(1, 171539);
				
				
				ResultSet rs=ps.executeQuery();
				
				System.out.println("Oracle Select Start ... ");
				while(rs.next())
				{
					ReviewAnalysisVO vo=new ReviewAnalysisVO();
					vo.setReview_id(rs.getInt("review_id"));
					vo.setContent(rs.getString("content"));
					vo.setRate(rs.getInt("rate"));
					vo.setRegdate(rs.getString("regdate"));
					vo.setUser_id(rs.getString("user_id"));
					vo.setMovie_id(rs.getInt("movie_id"));
					
					list.add(vo);
					System.out.println("review_id : "+vo.getReview_id());
				}
				rs.close();
				System.out.println("Oracle Select List Insert End ...");
				
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
