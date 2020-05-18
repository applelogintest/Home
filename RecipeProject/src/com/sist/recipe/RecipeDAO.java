package com.sist.recipe;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.*;
import java.io.*;
public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	//기능 설정
	public static void chefInsert(ChefVO vo)
	{
		SqlSession session=ssf.openSession(true);
		//autoCommit()
		session.insert("chefInsert",vo);
		
		session.close();
	}
	
	public static void recipeInsert(RecipeVO vo)
	{
		SqlSession session=ssf.openSession(true);
		session.insert("recipeInsert",vo);
		session.close();
		
	}
	
	public static void recipeDetailInsert(RecipeDetailVO vo)
	{
		SqlSession session=ssf.openSession(true);
		session.insert("recipeDetailInsert",vo);
		session.close();
	} 
	
	public static List<RecipeVO> recipeData()
	{
		SqlSession session=ssf.openSession();
		List<RecipeVO> list=session.selectList("recipeData");
		session.close();
		
		return list;
	}
	
}













