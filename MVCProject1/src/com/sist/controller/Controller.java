package com.sist.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Controller => Model => Controller => JSP
// Controller?cmd=list
// Controller?cmd=detail
// Controller?cmd=insert
import com.sist.model.*;
@WebServlet("*.do")
// list.do insert.do detail.do
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String cmd=request.getParameter("cmd");
		String uri=request.getRequestURI();
		
		// uri => /MVCProject1/*.do
		//getContextPath() => /MVCProject1/
		// 요청 받기 => 처리 => Model호출
		String cmd=uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf("."));
		System.out.println(cmd);
		String jsp=uri.substring(request.getContextPath().length(),uri.lastIndexOf("."));
		System.out.println(jsp);
		jsp=jsp+".jsp";
		System.out.println(jsp);
		/*
		 * http://localhost/MVCProject1/*.do
		 * 
		 * map.put("list",new ListModel());
		 * map.put("insert",new InsertModel());
		 * map.put("update",new UpdateModel());
		 */
		if(cmd.equals("list"))
		{
			ListModel model=new ListModel();
			model.execute(request);
			//jsp="member/list.jsp";
		}
		else if(cmd.equals("detail"))
		{
			DetailModel model=new DetailModel();
			model.execute(request);
			//jsp="member/detail.jsp";
		}
		else if(cmd.equals("insert"))
		{
			InsertModel model=new InsertModel();
			model.execute(request);
			//jsp="member/insert.jsp";
		}
		else if(cmd.equals("update"))
		{
			UpdateModel model=new UpdateModel();
			model.execute(request);
			//jsp="board/update.jsp";
		}
		
		RequestDispatcher rd=request.getRequestDispatcher(jsp);
		System.out.println(jsp);
		rd.forward(request, response);
	}

}
