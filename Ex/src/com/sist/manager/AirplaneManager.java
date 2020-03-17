package com.sist.manager;
import java.util.*;
import com.sist.dao.AirplaneDAO;
import com.sist.vo.AirplaneVO;

public class AirplaneManager {
	
	private int planeNumberPerAireline; //각 항공사마다 가질수 있는 비행기의 수
	private String[] airLineArr; //항공사 이름
	
	public AirplaneManager(){ // 각 항공사 및 항공사 마다의 가지는 비행기수 초기화 
		planeNumberPerAireline=30; 
		
		String[] line={"대한항공", "아시아나", "티웨이", "진에어", "에어서울", "에어부산", "제주항공", "이스타항공"};
		airLineArr=line; 
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
		//비행기 만들기
		
		/*AirplaneManager am=new AirplaneManager();
		AirplaneDAO dao=new AirplaneDAO();
		List<AirplaneVO> list=new ArrayList<AirplaneVO>();
		
		String[] airLineTArr= am.setAirLine();
		int[] sizeTArr=am.setSize();
		Node[] seatTArr=am.setSeat(sizeTArr);
		for(int i=0; i<am.airLineArr.length*am.planeNumberPerAireline;i++)
		{
			AirplaneVO vo=new AirplaneVO();
			
			vo.setFirst(seatTArr[i].getFirst());
			vo.setBusiness(seatTArr[i].getBusiness());
			
			vo.setEconomy(seatTArr[i].getEconomy());
			System.out.println(vo.getEconomy());
			vo.setSizeType(sizeTArr[i]);
			vo.setAirline(airLineTArr[i]);
			
			list.add(vo);
		}
		int i=0;
		for(AirplaneVO vo:list){
			dao.insertAirplane(vo);
			System.out.println(i);
			i++;
		}
		System.out.println("Airplane Insert End...");*/
		
		
		//좌석 만들기
		AirplaneDAO dao = new AirplaneDAO();
		dao.insertAirSeat(dao.airplaneAllData());
		System.out.println("Airplane_seat Insert End...");
		
	}

}
