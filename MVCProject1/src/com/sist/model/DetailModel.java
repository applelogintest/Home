package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DetailModel {
	public void execute(HttpServletRequest request){
		request.setAttribute("msg", "사용자 상세보기");
	}
}
