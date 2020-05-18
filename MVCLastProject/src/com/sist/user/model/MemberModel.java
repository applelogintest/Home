package com.sist.user.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;
@Controller
public class MemberModel {
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../member/join.jsp");//include
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/postfind.do")
	public String member_postfind(HttpServletRequest request, HttpServletResponse response)
	{
		return "../member/postfind.jsp";
	}
	
	@RequestMapping("member/postfind_result.do")
	public String member_postfind_result(HttpServletRequest request,HttpServletResponse response)
	{
		try{
			request.setCharacterEncoding("UTF-8");
			
		}catch (Exception ex) {}
		String dong=request.getParameter("dong");
		List<ZipcodeVO> list=MemberDAO.postfindData(dong);
		
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		
		return "../member/postfind_result.jsp";
	}
	
	@RequestMapping("member/idcheck.do")
	public String member_idcheck(HttpServletRequest request,HttpServletResponse response)
	{
		return "../member/idcheck.jsp";
	}
	
	@RequestMapping("member/idcheck_result.do")
	public String member_idcheck_result(HttpServletRequest request,HttpServletResponse response)
	{
		String id=request.getParameter("id");
		int count=MemberDAO.idcheck(id);
		request.setAttribute("count", count);
		
		return "../member/idcheck_result.jsp"; 
	}
	
	@RequestMapping("member/join_ok.do")
	public String member_join_ok(HttpServletRequest request, HttpServletResponse response)
	{
		try{
			request.setCharacterEncoding("UTF-8");
		}catch (Exception ex) {}
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String post1=request.getParameter("post1");
		String post2=request.getParameter("post2");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String tel3=request.getParameter("tel3");
		String email=request.getParameter("email");
		String content=request.getParameter("content");
		
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setContent(content);
		vo.setPost(post1+"-"+post2);
		vo.setTel(tel1+"-"+tel2+"-"+tel3);
		vo.setEmail(email);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		
		//insert
		MemberDAO.memberInsert(vo);
		return "redirect:../main/main.do";
	}
	/*
	 	           요청                    DB처리
	 	 사용자 =====> Model <======> DAO
	 	              |      결과값
	 	            jsp(결과값 전송)
	 */
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request, HttpServletResponse response)
	{
		String id=request.getParameter("id");// name=id
		String pwd=request.getParameter("pwd");
		// DAO연결
		MemberVO vo=MemberDAO.memberLogin(id, pwd);
		if(vo.getMsg().equals("OK"))
		{
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
			
		}
		request.setAttribute("msg", vo.getMsg());
		return "../member/login.jsp";
	}
	
	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:../main/main.do";
	}
}
