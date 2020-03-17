package com.sist.manager;
import java.sql.*;
import java.sql.Date;
import java.util.*;
public class Test {
	public static void main(String[] args)
	{
		TestManager tm=new TestManager();
		String month;
		/*int[] day={31,30,31,30,31,30,31,31,30,31,30,31};*/
		String day;
		String hour;
		String minute;
		
		for(int i=0;i<10;i++)
		{
			int monthT=((int)(Math.random()*10)+3);
			int dayT;
			int hourT;
			int minuteT;
			
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
			
			String temp="2020"+month+day+hour+minute;
			System.out.println(temp);
			tm.insertStartDate(temp);
			
		}
		
		
		
	}

}
