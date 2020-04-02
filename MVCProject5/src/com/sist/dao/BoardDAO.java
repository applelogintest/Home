package com.sist.dao;
import java.util.*;

import javax.websocket.Session;

import java.io.*; //xml읽기 위한 import
/*
	자바 
	  클래스의 구성요소
	 ============
	 	1) 멤버변수
	 		= 인스턴스 : new를 이용해서 메모리 할당 => 생성
	 		= 정적변수(static) : JVM이 클래스를 읽기 시작 => 생성
	 	2)메소드
	 	3)생성자
	 	
	 	=======================
	 	필요시에 멤버변수에 대한 초기화
	 	= 명시적 방법
	 		public class A
	 		{
	 			int a=getNumber(); (X)
	 			static int b=20; ==> 선언만 가능
	 			=====================> 외부나 메소드를 이용해서 초기화를 할 수 없다.
	 			public int getNumber()
	 			{
	 				return 10;
	 			}
	 		}
	 	= 생성자를 이용하는 방식 : 외부에 파일 읽기, 네트워크 연결, DB연결 , XML을 파싱
	 	= 초기화 블록 이용
	 	
	 	순서
	 	===
	 	 => 명시적 방법 => 초기화 블록 => 생성자 
	 	 			   ========
	 	 			     인스턴스 블록 ===> 변수가 인스턴스일 경우
	 	 			   public class A
	 	 			   {
	 	 			   		int a;
	 	 			   		
	 	 			   		{
	 	 			   			a=getNumber();
	 	 			   		}
	 	 			   		==========> 인스턴스 => 생성자
	 	 			   }
	 	 			   static 블록 ==> 변수가 static일 경우
	 	 			   public class A
	 	 			   {
	 	 			   		static int b;
	 	 			   		static
	 	 			   		{
	 	 			   			b=getNumber();
	 	 			   		}
	 	 			   	} 
	 	 			   ============================== 자동 호출
	 	
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class BoardDAO {
	private static SqlSessionFactory ssf;
	static
	{
		// 파싱 => getConnection(),disConnection()
		// id, sql 문장을 Map에 저장
		// id를 입력하고 sql문장을 실행 결과를 달라
		try{
			// xml 파일 읽기
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// Spring,Mybatis ==> classpath:src default값은 src경로
			// 파싱 
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	// 목록 읽기
	public static List<BoardVO> boardListData(Map map)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=ssf.openSession();
		list=session.selectList("boardListData",map);
							//================= ===
							//       id         parameterType
		
		session.close();// ps와 conn을 반환해준다 
		return list;
	}
	
	
	
	public static BoardVO boardDetailUpdateData(int no)
	{
		BoardVO vo=new BoardVO();
		SqlSession session=ssf.openSession();
		
		//<insert> => insert("id",값) , <delete> => delete("id",값)
		vo=session.selectOne("boardDetailData",no); // 자동 형변환 == <제네릭>
		
		session.close();
		return vo;
	}
	
	// 상세 보기
	public static BoardVO boardDetailData(int no,int type)
	{
		BoardVO vo=new BoardVO();
		SqlSession session=ssf.openSession();
		if(type==1)
		{
			session.update("hitIncrement",no); //<update>
			session.commit();
		}
		//<insert> => insert("id",값) , <delete> => delete("id",값)
		vo=session.selectOne("boardDetailData",no); // 자동 형변환 == <제네릭>
		
		session.close();
		return vo;
	}
	
	//총 페이지
	public static int boardTotalPage()
	{
		int total=0;
		SqlSession session=ssf.openSession();
		total=session.selectOne("boardTotalPage");
		
		session.close();
		return total;
	}
	
	public static int movieTotalPage(){
		int totalpage=0;
		SqlSession session=ssf.openSession();
		totalpage=session.selectOne("movieTotalPage");
		System.out.println(totalpage);
		session.close();
		return totalpage; 
	}
	
	public static void boardInsert(BoardVO vo)
	{
		SqlSession session=ssf.openSession(true); // auto커밋 setAutoCommit(true)
		session.insert("boardInsert",vo);
		session.close();
	}
	
	public static void boardUpdate(BoardVO vo)
	{
		SqlSession session=ssf.openSession();
		session.insert("boardUpdate",vo);
		session.commit();
		session.close();
	}
	

}









