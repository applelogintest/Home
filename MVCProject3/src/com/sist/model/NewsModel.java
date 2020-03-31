package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.news.*;
public class NewsModel implements Model{
	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try{
			request.setCharacterEncoding("UTF-8");
			
		}catch (Exception ex) {}
		String fd=request.getParameter("fd");
		if(fd==null || fd.equals(""))
		{
			fd="맛집";
		}
		NewsManager mgr=new NewsManager();
		List<Item> list=mgr.newsAllData(fd);
		
		request.setAttribute("list", list);
		
		return "news/news.jsp";
	}
}
