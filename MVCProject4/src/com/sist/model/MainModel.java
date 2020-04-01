package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.temp.Controller;
@Controller
public class MainModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		/*request.setAttribute("main_jsp", "home.jsp");*/
		return "main.jsp";
	}

}
