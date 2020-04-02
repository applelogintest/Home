package com.sist.controller;
import java.io.*;
import java.util.*;

public class ComponentScan {
	public List<String> getClassNameRead(String pack,String path)
	{
		List<String> list=new ArrayList<String>();
		try{
			/*String path="C:\\Users\\sist\\git\\Home\\MVCProject5\\src";*/
			path=path+"\\"+pack.replace(".","\\");
			File dir=new File(path);
			File[] files=dir.listFiles();
			for(File f:files)
			{
				String name=f.getName();
				if(name.endsWith("java"))
				{
					String s=pack+"."+name.substring(0,name.indexOf("."));
					// name="BoardModel.java"
					// s="com.sist.model"+"."+"BoardModel"
					list.add(s);
				}
			}
		}catch (Exception ex) { ex.printStackTrace();}
		
		return list;
	}
	
}
