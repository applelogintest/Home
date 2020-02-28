package com.sist.travel;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TravelManager {
	public ArrayList<TravelVO> travelListData(){
		ArrayList<TravelVO> list = new ArrayList<TravelVO>();
		try {
			Document doc=Jsoup.connect();
			
		}catch (Exception ex) {}
		
		return list;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
