package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class MovieDAO {
	private static SqlSessionFactory ssf;
	static 
	{
		try{
			Reader reader=Resources.getResourceAsReader("config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception ex) {
		}
	}
	public static List<MovieVO> movieAllData(){
		return ssf.openSession().selectList("movieAllData");
		
	}
	
	public static List<MusicVO> musicAllData(){
		return ssf.openSession().selectList("musicAllData");
		
	}
	
}
