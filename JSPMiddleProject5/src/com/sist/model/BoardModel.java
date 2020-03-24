package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;

public class BoardModel {
	public void boardListData(HttpServletRequest request)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list=BoardDAO.boardListData(map);
		
		int totalpage=BoardDAO.boardTotalPage();
		/*	
		  	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		  	Date date=new Date();
		  	String today=sdf.format(date);
		 */
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//JSP로 결과값 전송
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("today", today);
	}
	public void boardDetailUpdateData(HttpServletRequest request)
	{
		String no=request.getParameter("no");
		
		BoardVO vo=new BoardDAO().boardDetailUpdateData(Integer.parseInt(no));
		
		//detail.jsp로 전송
		request.setAttribute("vo",vo);
	}
	public void boardDetailData(HttpServletRequest request)
	{
		String no=request.getParameter("no");
		String type=request.getParameter("type");		
		BoardVO vo=new BoardDAO().boardDetailData(Integer.parseInt(no),Integer.parseInt(type));
		
		//detail.jsp로 전송
		request.setAttribute("vo",vo);
		
	}
	
	public void boardInsert(HttpServletRequest request,HttpServletResponse response)
	{
		try{
			request.setCharacterEncoding("UTF-8");
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			
			BoardVO vo=new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO.boardInsert(vo);
			
			response.sendRedirect("list.jsp");
		}catch (Exception ex) {}
	}
	
	public void boardUpdate(HttpServletRequest request, HttpServletResponse response)
	{
		try{
			request.setCharacterEncoding("UTF-8");
			String no=request.getParameter("no");
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			
			
			BoardVO vo=new BoardVO();
			vo.setNo(Integer.parseInt(no));
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			
			BoardDAO.boardUpdate(vo);
			
			response.sendRedirect("detail.jsp?no="+vo.getNo()+"&type=2");
		}catch (Exception ex) {}
	}
	
	
}
