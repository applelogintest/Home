package com.sist.vo;
/*
 *  LOC_NO      NOT NULL NUMBER        
	LINK        NOT NULL VARCHAR2(200) 
	LOC         NOT NULL VARCHAR2(20)  
	WEATHER              VARCHAR2(50)  
	PEAK_SEASON          VARCHAR2(200) 
	TIP                  VARCHAR2(200) 
	VOLT                 VARCHAR2(300) 
	VISA                 VARCHAR2(200) 
	EXCHANGE             VARCHAR2(200) 
 */
public class HaeoeVO {
	private int loc_no;
	private String loc;
	private String weather;
	private String peak_season;
	private String tip;
	private String volt;
	private String visa;
	private String exchange;
	
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
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getPeak_season() {
		return peak_season;
	}
	public void setPeak_season(String peak_season) {
		this.peak_season = peak_season;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getVolt() {
		return volt;
	}
	public void setVolt(String volt) {
		this.volt = volt;
	}
	public String getVisa() {
		return visa;
	}
	public void setVisa(String visa) {
		this.visa = visa;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	
}
