package com.sist.model;
import java.util.*;
import com.sist.dao.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class MovieModel {
	public void movieListDate(HttpServletRequest request)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		MovieDAO dao=new MovieDAO();
		List<MovieBean> list=dao.movieListData(curpage);
		int totalpage=dao.movieTotalPage();
		
		request.setAttribute("list",list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		/*HttpSession session=request.getSession();
		
		session.setAttribute("list",list);
		session.setAttribute("curpage", curpage);
		session.setAttribute("totalpage", totalpage);
		*/
		
	}
	
	public void movieDetailData(HttpServletRequest request)
	{
		String mno=request.getParameter("mno");
		MovieDAO dao=new MovieDAO();
		MovieBean vo=dao.movieDetailData(Integer.parseInt(mno));
		
		request.setAttribute("vo", vo);
	}
}
