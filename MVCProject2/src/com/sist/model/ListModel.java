package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;
/*
 	ListModel model=new ListModel();
 	Model model=new ListModel();
 		  model=new InsertModel();
 */
/*
	Model => execute()
	class ListModel implements Model
	{
		execute(){}
		list(){}
	}
	
	Model model=new ListModel();
	model.execute()
	model.list() (X)
	
*/
public class ListModel implements Model{
	@Override
	public String execute(HttpServletRequest request) {
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
		
		return "board/list.jsp";
	}

}
