package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.temp.Controller;

@Controller
public class MovieModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "영화목록");
		request.setAttribute("main_jsp", "../movie/movie.jsp");
		return "../main/main.jsp";
	}

}
