package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
//POJO 클래스 클래스끼리 서로 연관성이 없는 클래스 인터페이스 , 상속  X
@Controller
public class BoardModel {
	@RequestMapping("board/list.do")
	public String boardListData(HttpServletRequest request,HttpServletResponse response)
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
		
		return "list.jsp";
	}
	@RequestMapping("board/update.do")
	public void boardDetailUpdateData(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		
		BoardVO vo=new BoardDAO().boardDetailUpdateData(Integer.parseInt(no));
		
		//detail.jsp로 전송
		request.setAttribute("vo",vo);
	}
	@RequestMapping("board/detail.do")
	public String boardDetailData(HttpServletRequest request)
	{
		String no=request.getParameter("no");
		String type=request.getParameter("type");		
		BoardVO vo=new BoardDAO().boardDetailData(Integer.parseInt(no),Integer.parseInt(type));
		
		//detail.jsp로 전송
		request.setAttribute("vo",vo);
		return "detail.jsp";
	}
	@RequestMapping("board/insert_ok.do")
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
			
		}catch (Exception ex) {}
	}
	@RequestMapping("board/update_ok.do")
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
			
			//response.sendRedirect("detail.jsp?no="+vo.getNo()+"&type=2");
		}catch (Exception ex) {}
	}
	
	
}