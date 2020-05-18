package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 	TNO      NUMBER        
	TNAME    VARCHAR2(100) 
	TLOC     VARCHAR2(100) 
	TDATE    VARCHAR2(100) 
 */

@Getter
@Setter
public class ReserveTheaterVO {
	private int tno;
	private String tname;
	private String tloc;
	private String tdate;

}
