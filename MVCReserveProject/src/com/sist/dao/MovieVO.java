package com.sist.dao;
/*
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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVO {
	private int mno;
	private String title;
	private String poster;
	private double score;
	private String genre;
	private String regdate;
	private String time;
	private String grade;
	private String director;
	private String actor;
	private String story;
	private int type;
	private String theaterNo;
}
