package com.sist.manager;
import java.util.*;
import com.sist.dao.AirplaneDAO;
import com.sist.vo.AirTimeVO;
import com.sist.vo.AirplaneVO;

public class AirplaneManager {
	private int planeNumberPerAireline; //각 항공사마다 가질수 있는 비행기의 수
	private String[] airLineArr; //항공사 이름
	private int randomStartDayCount;
	private int airplaneCount;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private String[] airports={"인천 국제공항","김포 국제공항","포항 공항","제주 국제공항"};
	
	List<AirTimeVO> airTimeList=new ArrayList<AirTimeVO>();
	ArrayList<Integer> randomCount;
	
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
	
	
	public AirplaneManager(){ // 각 항공사 및 항공사 마다의 가지는 비행기수 초기화 
		planeNumberPerAireline=30; 
		
		String[] line={"대한항공", "아시아나", "티웨이", "진에어", "에어서울", "에어부산", "제주항공", "이스타항공"};
		airLineArr=line;
		
		randomStartDayCount=0;
		airplaneCount=240;
		randomCount=new ArrayList<Integer>();
	}
	
	public String[] setAirLine(){ //8개의 항공사 30개씩 배분 총 240
		String[] result= new String[airLineArr.length*planeNumberPerAireline];
		for(int i=0; i<airLineArr.length;i++)
		{
			for(int j=0; j<planeNumberPerAireline;j++)
			{
				result[i*planeNumberPerAireline+j] = airLineArr[i];
			}
			
		}
		return result;
	}
	
	public int[] setSize(){ // 각 항공사마다 대중소 10개씩 배분 총240
		int[] result=new int[airLineArr.length*planeNumberPerAireline];
		for(int i=0;i<airLineArr.length;i++)
		{
			for(int j=0;j<3;j++)
			{
				for(int k=0; k<10; k++)
				{
					result[i*planeNumberPerAireline+j*10+k]=j;
				}
			}
		}
		return result;
	}
	
	public Node[] setSeat(int[] sizeTArr) //대중소 사이즈를 받아서 각 항공기에 랜덤으로 좌석수 배분
	{
		Node[] result= new Node[airLineArr.length*planeNumberPerAireline];
		for(int i=0; i<sizeTArr.length; i++)
		{
			Node temp=new Node(sizeTArr[i]);
			result[i]=temp;
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AirplaneManager am=new AirplaneManager();
		AirplaneDAO dao=new AirplaneDAO();
		/*List<AirplaneVO> list=dao.airplaneSeatData();*/
		
		/*String[] airLineTArr= am.setAirLine();
		int[] sizeTArr=am.setSize();
		Node[] seatTArr=am.setSeat(sizeTArr);
		for(int i=0; i<am.airLineArr.length*am.planeNumberPerAireline;i++)
		{
			AirplaneVO vo=new AirplaneVO();
			
			vo.setFirst(seatTArr[i].getFirst());
			vo.setBusiness(seatTArr[i].getBusiness());
			vo.setEconomy(seatTArr[i].getEconomy());
			vo.setSizeType(sizeTArr[i]);
			
			list.add(vo);
		}
		int i=0;
		for(AirplaneVO vo:list){
			dao.insertAirplane(vo);
			System.out.println(i);
			i++;
		}
		System.out.println("Airplane Insert End...");*/
		
		//출발 시간 및 공항 데이터 넣기
		ArrayList<String> time=am.setStartDate();
		ArrayList<String> airport=am.setAirport(time);
		int random=0;
		List<AirTimeVO> list=new ArrayList<AirTimeVO>();
		
		for(int i=0; i<am.airplaneCount; i++)
		{
			for(int j=0; j<am.randomCount.get(i);j++)
			{
				AirTimeVO vo=new AirTimeVO();
				vo.setPlane_id(i+1);
				vo.setStart_time(time.get(j));
				vo.setStart_airport(airport.get(j));
				if(airport.get(j).equals("제주 국제공항"))
				{
					random=(int)(Math.random()*3);
					vo.setEnd_airport(am.airports[random]);
				}
				else
				{
					vo.setEnd_airport(am.airports[3]);
				}
				list.add(vo);
			}
			
		}
		
		for(int j=0; j<list.size(); j++)
		{
			for(int i=0; i<list.size(); i++)
			{
			}
			
		}
		
		System.out.println("Airtime Insert...");
	/*	for(AirTimeVO vo:list)
		{
			dao.insertAirtime(vo);
		}
	*/	System.out.println("Airtime Insert End...");
		
		
	}

}
