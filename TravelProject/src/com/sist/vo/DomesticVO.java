package com.sist.vo;
/*
 *  LOC_NO      NOT NULL NUMBER        
	LINK        NOT NULL VARCHAR2(200) 
	LOC         NOT NULL VARCHAR2(20)  
	SPOT                 VARCHAR2(50)  
	PEAK_SEASON          VARCHAR2(200) 
	SPECIAL              VARCHAR2(200) 
	FESTIVAL             VARCHAR2(300) 
	ACTIVITY             VARCHAR2(200) 

 */
public class DomesticVO {
	private int loc_no;
	private String loc;
	private String spot;
	private String peak_season;
	private String special;
	private String festival;
	private String activity;
	
	public int getLoc_no() {
		return loc_no;
	}
	public void setLoc_no(int loc_no) {
		this.loc_no = loc_no;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getSpot() {
		return spot;
	}
	public void setSpot(String spot) {
		this.spot = spot;
	}
	public String getPeak_season() {
		return peak_season;
	}
	public void setPeak_season(String peak_season) {
		this.peak_season = peak_season;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getFestival() {
		return festival;
	}
	public void setFestival(String festival) {
		this.festival = festival;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	
	
}
