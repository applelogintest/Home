package com.sist.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class CreateSqlsessionFactory {
	private static SqlSessionFactory ssf;
	
	static{
		try{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static SqlSessionFactory getSsf() {
		return ssf;
	}

}
