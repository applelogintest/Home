package com.sist.data;

import java.net.InetSocketAddress;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
/*
 	REVIEW_ID NOT NULL NUMBER        
	MOVIE_ID           NUMBER        
	USER_ID            VARCHAR2(400) 
	RATE               NUMBER        
	CONTENT            CLOB          
	REGDATE            VARCHAR2(300) 
 */
public class ReviewJson {
	
	private MongoClient mc; // Connection
	private DB db; // DataBase (XE)
	private DBCollection dbc; // Table
	
	public ReviewJson()
	{
		try{
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.192", 27017)));
			db=mc.getDB("mydb");
			dbc=db.getCollection("movie_review");
		}catch (Exception ex) {
			System.out.println("연결 초기화"+ex.getMessage());
		}
	}
	
	public void reviewToJson(List<ReviewAnalysisVO> list)
	{
		
		try{
			for(ReviewAnalysisVO vo:list)
			{
				BasicDBObject obj=new BasicDBObject();
				obj.put("review_id", vo.getReview_id());
				obj.put("movie_id", vo.getMovie_id());
				obj.put("user_id", vo.getUser_id());
				obj.put("rate", vo.getRate());
				obj.put("regdate", vo.getRegdate());
				System.out.println(obj.get("review_id"));
				System.out.println(obj.get("movie_id"));
				System.out.println(obj.get("user_id"));
				System.out.println(obj.get("rate"));
				System.out.println(obj.get("regdate"));
				dbc.insert(obj);
				System.out.println("VO to JSON :" + vo.getReview_id());
				
			}
			System.out.println("Insert start ...");
			System.out.println("Insert end ...");
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
