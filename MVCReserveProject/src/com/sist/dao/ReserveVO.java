package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 	RNO          NUMBER        
	ID           VARCHAR2(20)  
	MNO          NUMBER        
	TNAME        VARCHAR2(100) 
	RDATE        VARCHAR2(100) 
	RTIME        VARCHAR2(100) 
	RINWON       VARCHAR2(20)  
	RPRICE       VARCHAR2(20)  
	ISRESERVE    NUMBER        
 */
@Getter
@Setter

public class ReserveVO {
	private int rno;
	private String id;
	private int mno;
	private String tname;
	private String rdate;
	private String rtime;
	private String rinwon;
	private String rprice;
	private int isReserve;
	private String poster;
	private String title;

}
