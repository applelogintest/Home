package com.sist.manager;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.sist.vo.AirTimeVO;
public class Test {
	List<AirTimeVO> airTimeList=new ArrayList<AirTimeVO>();
	int randomStartDayCount;
	ArrayList<Integer> randomCount;
	int airplaneCount;
	String month;
	String day;
	String hour;
	String minute;
	String[] airports={"인천","김포","포항","제주"};
	
	public Test(){
		randomStartDayCount=0;
		airplaneCount=240;
		randomCount=new ArrayList<Integer>();
	}
	
	public ArrayList<String> setStartDate(){
		ArrayList<String> list=new ArrayList<String>();
		
		for(int i=0; i<airplaneCount; i++)
		{
			randomStartDayCount=(int)(Math.random()*11)+10;
			randomCount.add(randomStartDayCount);
			for(int j=0;j<randomStartDayCount;j++)
			{
				int monthT;
				int dayT;
				int hourT;
				int minuteT;
				
				monthT=((int)(Math.random()*9)+4);
				hourT=(int)(Math.random()*24)+1;
				minuteT=(int)(Math.random()*59)+1;
				
				switch (monthT) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					dayT=(int)(Math.random()*31)+1;
					break;
					default:
						dayT=(int)(Math.random()*30)+1;
						break;
				}
				if(monthT < 10)
				{
					month="0"+Integer.toString(monthT);
				}
				else
				{
					month=Integer.toString(monthT);
				}
				
				if(dayT < 10)
				{
					day="0"+Integer.toString(dayT);
				}
				else
				{
					day=Integer.toString(dayT);
				}
				
				
				if(hourT<10)
				{
					hour="0"+Integer.toString(hourT);
				}
				else
				{
					hour=Integer.toString(hourT);
				}
				
				if(minuteT<10)
				{
					minute="0"+Integer.toString(minuteT);
				}
				else
				{
					minute=Integer.toString(minuteT);
				}
				
				
				list.add("2020"+month+day+hour+minute);
				
				
				/*vo.setStart_time("2020"+month+day+hour+minute);
				vo.setNo(i);
				temp.add(vo);*/
			
			}
		}
		
		return list;
	}
	
	public ArrayList<String> setAirport(ArrayList<String> airportCount){
		ArrayList<String> result=new ArrayList<>();
		for(int i=0; i<airportCount.size(); i++)
		{
			int k=(int)(Math.random()*4);
			result.add(airports[k]);
			
		}
		return result;
		
	}
	public static void main(String[] args)
	{
		Test tm=new Test();
		ArrayList<String> time=tm.setStartDate();
		ArrayList<String> airport=tm.setAirport(time);
		int random=0;
		String[] airports={"인천","김포","포항","제주"};
		List<AirTimeVO> list=new ArrayList<AirTimeVO>();
		
		for(int i=0; i<tm.airplaneCount; i++)
		{
			for(int j=0; j<tm.randomCount.get(i);j++)
			{
				AirTimeVO vo=new AirTimeVO();
				vo.setPlane_id(i);
				vo.setStart_time(time.get(i));
				vo.setStart_airport(airport.get(i));
				if(airport.get(i).equals("제주"))
				{
					random=(int)(Math.random()*3);
					vo.setEnd_airport(airports[random]);
				}
				else
				{
					vo.setEnd_airport(airports[3]);
				}
				list.add(vo);
			}
			
		}
		
	}
	
	

}
