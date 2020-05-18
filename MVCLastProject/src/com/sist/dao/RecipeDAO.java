package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;
/*
 *
 				request						 request
 				           service(requst)             recipeListData(requst)
 			요청 =========> DispacherServlet ==========> Model <====================> DAO 
 							 request를 받는다  <==========  DAO를 받은 값을 request에 추가
 							 							 request.setAttribute()
 					       ================   request
 					              |
 					              | forward(request)
 					             해당 JSP로 request를 전송
 					       JSP => request에 담긴 데이터를 출력
 					       =============================> 메모리에 출력 ===========> 사용자가 브라우저에서 읽기
 *
 */
public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlsessionFactory.getSsf();
	}
	
	public static List<RecipeVO> recipeListData(Map map)
	{
		SqlSession session=null;
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		try{
			session=ssf.openSession();
			list=session.selectList("recipeListData",map);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	
	public static int recipeTotalPage()
	{
		SqlSession session=null;
		int total=0;
		try{
			session=ssf.openSession();
			total=session.selectOne("recipeTotalPage");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static List<ChefVO> chefListData(Map map)
	{
		SqlSession session=null;
		List<ChefVO> list=new ArrayList<ChefVO>();
		try{
			session=ssf.openSession();
			list=session.selectList("chefListData",map);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	
	public static int chefTotalPage()
	{
		SqlSession session=null;
		int total=0;
		try{
			session=ssf.openSession();
			total=session.selectOne("chefTotalPage");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static RecipeDetailVO recipeDetailData(int no)
	{
		RecipeDetailVO vo=new RecipeDetailVO();
		SqlSession session=null;
		try{
			session=ssf.openSession();
			vo=session.selectOne("recipeDetailData",no);
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
	
	public static int recipeCount(int no)
	{
		SqlSession session=null;
		int total=0;
		try{
			session=ssf.openSession();
			total=session.selectOne("recipeCount2",no);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static List<RecipeVO> chefDetailData(Map map)
	{
		SqlSession session=null;
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		try{
			session=ssf.openSession();
			list=session.selectList("chefDetailData",map);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	
	public static int chefDataTotalPage(String chef)
	{
		SqlSession session=null;
		int total=0;
		try{
			session=ssf.openSession();
			total=session.selectOne("chefDataTotalPage",chef);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	public static List<RecipeVO> recipeFindData(String fd)
	{
		SqlSession session=null;
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		try{
			session=ssf.openSession();
			Map map=new HashMap();
			map.put("fd", fd);
			list=session.selectList("recipeFindData",map);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	
}
