package com.sist.manager;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;
import com.sist.vo.*;

public class TravelManager {
	//textdddd
	
	// 국내 정보 가져오기 
	public ArrayList<DomesticVO> domesticData(){
		ArrayList<DomesticVO> list=new ArrayList<DomesticVO>();
		try {
			Document doc=Jsoup.connect("http://localhost/TravelProject/html/Travel.html").get();
			Elements link_data=doc.select("div.cityList a");
			for(int i=0; i<link_data.size(); i++)
			{
				if(i>42)
				{
					DomesticVO vo=new DomesticVO();
					String temp_link=link_data.get(i).attr("href");
					Document doc2=Jsoup.connect(temp_link).get();
					Element loc=doc2.select("div.in h1").first();
					Element spot=doc2.select("li.spot p").first();
					Element peak_season=doc2.select("li.peak_season p").first();
					Element special=doc2.select("li.special p").first();
					Element festival=doc2.select("li.festival p").first();
					Element activity=doc2.select("li.activity p").first();
					
					//System.out.println(loc.text()+" "+spot.text()+" "+peak_season.text()+" "+special.text()+" "+festival.text()+" "+activity.text());
					
					vo.setLoc(loc.text());
					vo.setSpot(spot.text());
					vo.setPeak_season(peak_season.text());
					vo.setSpecial(special.text());
					vo.setFestival(festival.text());
					vo.setActivity(activity.text());
					
					list.add(vo);
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<HaeoeVO> haeoeData(){
		ArrayList<HaeoeVO> list=new ArrayList<HaeoeVO>();
		try {
			Document doc=Jsoup.connect("http://localhost/TravelProject/html/Travel.html").get();
			Elements link_data=doc.select("div.cityList a");
			for(int i=0; i<link_data.size(); i++)
			{
				if(i<43)
				{
					HaeoeVO vo=new HaeoeVO();
					String temp_link=link_data.get(i).attr("href");
					Document doc2=Jsoup.connect(temp_link).get();
					
					Element loc=doc2.select("div.in h1").first();
					Element weather=doc2.select("li.weather p").first();
					String temp="";
					temp=weather.text().substring(0, weather.text().indexOf("월")-1);
					
					Element peak_season=doc2.select("li.peak_season p").first();
					Element tip=doc2.select("li.tip p").first();
					Element volt=doc2.select("li.voltage p").first();
					Element visa=doc2.select("li.visa p").first();
					
					vo.setLoc(loc.text());
					vo.setWeather(temp);
					vo.setPeak_season(peak_season.text());
					vo.setTip(tip.text());
					vo.setVolt(volt.text());
					vo.setVisa(visa.text());
					
					System.out.println(vo.getLoc()+" "+temp+" "+vo.getPeak_season()+" "+vo.getTip()+" "+vo.getVolt()+" "+vo.getVisa());
					
					list.add(vo);
					
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TravelManager tm=new TravelManager();
		TravelDAO dao=TravelDAO.newInstance();
		
		//해외 여행지 데이터 넣기
		ArrayList<HaeoeVO> list=tm.haeoeData();
		for(HaeoeVO vo:list)
		{
			dao.insertHaeoeAllData(vo);
			System.out.println(vo.getLoc()+" "+vo.getWeather()+" "+vo.getPeak_season()+" "+vo.getTip()+" "+vo.getVolt()+" "+vo.getVisa());
		}
		
		
		System.out.println("Haeoe Data Insert End...");
		
		//국내 여행지 데이터 넣기
		/*
		ArrayList<DomesticVO> list=tm.domesticData();
		for(DomesticVO vo:list)
		{
			dao.insertDomesticAllData(vo);
			System.out.println(vo.getLoc()+" "+vo.getSpecial()+" "+vo.getPeak_season()+" "+vo.getSpot()+" "+vo.getFestival()+" "+vo.getActivity());
		}
		
		
		System.out.println("Domestic Data Insert End...");
		*/
		
		
	}

}
