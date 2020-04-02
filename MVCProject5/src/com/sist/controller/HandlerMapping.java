package com.sist.controller;
import java.util.*;
import java.io.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class HandlerMapping {
	private List<String> list=
			new ArrayList<String>();
	   public HandlerMapping(String path,String defaultPath)
	   {
	      try
	      {
	    	  
	         SAXParserFactory spf=SAXParserFactory.newInstance();
	         //Sax파서기
	         SAXParser sp=spf.newSAXParser();
	         XMLParser xp=new XMLParser();
	         //dh = XMLParser의 DefaultHandler
	         sp.parse(new File(path), xp);
	         List<String> pList=xp.getList();
	         ComponentScan cs=new ComponentScan();
	         for(String pack:pList){
	        	 List<String> fNames=cs.getClassNameRead(pack,defaultPath);
	        	 for(String name:fNames)
	        	 {
	        		 list.add(name);
	        	 }
	         }
	      }catch(Exception ex) {	ex.printStackTrace();}
	   }
	   
	   public List<String> getList() {
	      return list;
	   }
	
}
