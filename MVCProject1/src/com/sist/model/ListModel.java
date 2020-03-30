package com.sist.model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
/*
  	Controller => 요청을 받는다, 처리 결과값 JSP로 전송 (Model과 JSP의 연결 역할)
  	Model => 처리
  	View => 결과값 출력
  		  request                 request             request                    request   
  	사용자 ==========> Controller ===========> Model =============> Controller ==============> JSP ================> browser
  			=> ListModel model=new ListModel()
  			   model.execute(request)
  			   
  			   				                 Model => request.setAttribute();
 */
public class ListModel {
	public void execute(HttpServletRequest request){
		List<String> list=new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("박문수");
		
		request.setAttribute("list", list);
		// Controller전송 => JSP로 전송
	}

}
