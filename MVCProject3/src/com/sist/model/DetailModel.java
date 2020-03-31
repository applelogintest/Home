package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;
public class DetailModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String no=request.getParameter("no");
		FoodVO vo=FoodDAO.detailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		return "food/detail.jsp";
	}

}
