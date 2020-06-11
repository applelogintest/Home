package com.sist.data;
/*
 	REVIEW_ID NOT NULL NUMBER        
	MOVIE_ID           NUMBER        
	USER_ID            VARCHAR2(400) 
	RATE               NUMBER        
	CONTENT            CLOB          
	REGDATE            VARCHAR2(300) 
 */
public class ReviewAnalysisVO {
	private int review_id;
	private int movie_id;
	private String user_id;
	private int rate;
	private String content;
	private String regdate;
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	

}
