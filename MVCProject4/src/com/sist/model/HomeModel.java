package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class HomeModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "홈페이지");
		request.setAttribute("main_jsp", "home.jsp");
		return "main.jsp";
	}

}
