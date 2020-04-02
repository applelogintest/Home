package com.sist.common.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class CommonModel {
	@RequestMapping("site/main.do")
	public String main_page(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "home.jsp");
		request.setAttribute("side", "사이드 데이터");
		
		return "main.jsp";
	}
	
}
