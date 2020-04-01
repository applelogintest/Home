package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.temp.Controller;
import com.sist.temp.RequestMapping;
@Controller
public class BoardModel implements Model {
	
	@RequestMapping("main/home.do")
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
