package com.sist.manager;

import java.util.*;

public class Node{
	private int economy;
	private int business;
	private int first;
	public Node(int size){
		if(size==0) // 소 size일때 나오는 좌석
		{
			first=0; business=0; economy=(int)(Math.random()*41)+160;
		}
		else if(size==1) // 중 size일때 나오는 좌석
		{
			first=0; 
			business=(int)(Math.random()*11)+10;
			economy=(int)(Math.random()*41)+160;
			
		}
		else // 대 size일때 나오는 좌석
		{
			first=(int)(Math.random()*6)+5; 
			business=(int)(Math.random()*21)+30;
			economy=(int)(Math.random()*51)+200;
		}
			
	}
	public int getEconomy() {
		return economy;
	}
	public void setEconomy(int economy) {
		this.economy = economy;
	}
	public int getBusiness() {
		return business;
	}
	public void setBusiness(int business) {
		this.business = business;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	
	
}
