package com.sist.xml;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
/*
 	XML
 	XML -> 데이터 읽기 ==> 솔루션
 */
public class SawonXML {
	public static void main(String[] args) {
		SawonXML xml=new SawonXML();
		xml.xmlParser();
	}
	
	public void xmlParser()
	{
		try{
			// 파서기 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db=dbf.newDocumentBuilder(); // 파서기
			// 문자 저장
			Document doc=db.parse(new File("C:\\webDev\\webStudy\\JSPMiddleProject4\\WebContent\\WEB-INF\\sawon.xml"));
			// 테이블 읽기
			Element table=doc.getDocumentElement();
			System.out.println(table.getTagName());
			
			NodeList list=table.getElementsByTagName("list");
			System.out.println(list.getLength());
			
			for(int i=0; i<list.getLength(); i++)
			{
				list=table.getElementsByTagName("name");
				String name=list.item(i).getFirstChild().getNodeValue();
				System.out.println("name="+name);
				
				list=table.getElementsByTagName("addr");
				String addr=list.item(i).getFirstChild().getNodeValue();
				System.out.println("addr="+addr);
				
				list=table.getElementsByTagName("sex");
				String sex=list.item(i).getFirstChild().getNodeValue();
				System.out.println("sex="+sex);
				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
