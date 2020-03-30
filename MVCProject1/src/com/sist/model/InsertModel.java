package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel {
	public void execute(HttpServletRequest request){
		request.setAttribute("msg", "사용자 추가");
	}
}
