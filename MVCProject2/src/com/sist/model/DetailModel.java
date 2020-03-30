package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class DetailModel implements Model{
	@Override
	public String execute(HttpServletRequest request) {
		String no=request.getParameter("no");
		String type=request.getParameter("type");		
		BoardVO vo=new BoardDAO().boardDetailData(Integer.parseInt(no),Integer.parseInt(type));
		
		//detail.jsp로 전송
		request.setAttribute("vo",vo);
		
		return "board/detail.jsp";
	}

}
