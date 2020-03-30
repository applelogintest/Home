package com.sist.model;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class UpdateModel implements Model{
	@Override
	public String execute(HttpServletRequest request) {
		String no=request.getParameter("no");
		
		BoardVO vo=new BoardDAO().boardDetailUpdateData(Integer.parseInt(no));
		
		//detail.jsp로 전송
		request.setAttribute("vo",vo);
		
		return "board/update.jsp";
	}

}
