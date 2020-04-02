package com.sist.member.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MemberModel {
	@RequestMapping("site/member/join.do")
	public String member_join(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("msg", "회원가입");
		request.setAttribute("main_jsp", "../member/join.jsp");
		CommonData.commonData(request);
		return "../main.jsp";
	}

}
