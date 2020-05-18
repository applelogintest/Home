package com.sist.dao;
import java.sql.ResultSet;
import java.util.*;
import com.sist.vo.*;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class FreeBoardReplyDAO {
	private static SqlSessionFactory ssf;
	static{
		ssf=CreateSqlsessionFactory.getSsf();
		
	}
	
	public static List<BoardReplyVO> replyListData(Map map)
	{
		List<BoardReplyVO> list=new ArrayList<BoardReplyVO>();
		SqlSession session=null;
		try{
			session=ssf.openSession();
			session.update("replyListData2",map);
			list=(ArrayList<BoardReplyVO>)map.get("pResult");
			//no,bno,id,name,msg,regdate,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday,group_tab,num
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static void replyInsert(Map map)
	{
		SqlSession session=null;
		try{
			session=ssf.openSession();
			session.update("replyInsert2",map); // 프로시저 호출
			
		}catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally
		{
			if(session!=null)
				session.close();
		}
		
	}
	// Model => DAO (매개변수로 전송)
	// 매개변수 => 클래스 => 값변경, 추가가 가능하다(주소의 의한 전달)
	public static int replyTotalPage(Map map)
	{
		SqlSession session=null;
		int total=0;
		try{
			session=ssf.openSession();
			session.update("replyTotalPage2",map); // 프로시저 호출
			total=(int)map.get("pTotal");
		}catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
		
	}
	
	public static void replyUpdate(Map map)
	{
		SqlSession session=null;
		try{
			session=ssf.openSession();
			session.update("replyUpdate2",map); // 프로시저 호출
			
		}catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally
		{
			if(session!=null)
				session.close();
		}
		
	}
	
	public static void replyReplyInsert(Map map)
	{
		SqlSession session=null;
		try{
			session=ssf.openSession();
			session.update("replyReplyInsert2",map); // 프로시저 호출
			
		}catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally
		{
			if(session!=null)
				session.close();
		}
		
	}
	
	public static void replyDelete(Map map)
	{
		SqlSession session=null;
		try{
			session=ssf.openSession();
			session.update("replyDelete2",map); // 프로시저 호출
			
		}catch (Exception ex) {
			ex.printStackTrace();
		} 
		finally
		{
			if(session!=null)
				session.close();
		}
		
	}
	
}
