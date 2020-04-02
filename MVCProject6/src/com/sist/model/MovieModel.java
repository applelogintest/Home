package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MovieModel {
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request)
	{
		request.setAttribute("msg", "회원가입");
		return "join.jsp"; 
	}
}
