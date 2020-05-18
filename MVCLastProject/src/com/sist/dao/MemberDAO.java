package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class MemberDAO {
	private static SqlSessionFactory ssf;
	static{
		ssf=CreateSqlsessionFactory.getSsf();
	}
	
	public static List<ZipcodeVO> postfindData(String dong)
	{
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		SqlSession session=null;
		try{
			session=ssf.openSession();
			list=session.selectList("postfindData",dong);
		}catch (Exception ex) {
			System.out.println("postfindData Error : "+ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int idcheck(String id)
	{
		int count=0;
		SqlSession session=null;
		try{
			// session 생성 => Connection 객체
			session=ssf.openSession();
			count=session.selectOne("idcheckData",id);
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return count;
	}
	
	public static void memberInsert(MemberVO vo)
	{
		SqlSession session=null;
		try{
			session=ssf.openSession(true);
			session.insert("memberInsert",vo);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public static MemberVO memberLogin(String id,String pwd)
	{
		MemberVO vo=new MemberVO();
		SqlSession session=null;
		try{
			session=ssf.openSession();
			int count=session.selectOne("idCount",id);
			if(count==0)
			{
				vo.setMsg("NOID");
			}
			else
			{
				MemberVO mvo=session.selectOne("getPwd",id);
				if(pwd.equals(mvo.getPwd()))
				{
					vo.setMsg("OK");
					vo.setAdmin(mvo.getAdmin());
					vo.setName(mvo.getName());
					
				}
				else
				{
					vo.setMsg("NOPWD");
				}
			}
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return vo;
	}
	
}
