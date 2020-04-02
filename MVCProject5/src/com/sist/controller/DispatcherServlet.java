package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> list=new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		//Path (web.xml)
		String path=config.getInitParameter("contextConfigLocation");
		String defaultPath=config.getInitParameter("defaultPath");
		HandlerMapping hm=new HandlerMapping(path,defaultPath);
		list=hm.getList();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI(); // main/main.do
		cmd=cmd.substring(request.getContextPath().length()+1);
		
		try{
			for(String cls:list)
			{
				/*
				 *  @Controller
				 *  class A
				 *  
				 *  class B
				 *  
				 *  @Controller
				 *  class c
				 */
				
				Class<?> clsName=Class.forName(cls);
				System.out.println(clsName);
				if(clsName.isAnnotationPresent(Controller.class)==false)
						continue; // 제외
				Object obj=clsName.newInstance();
				// 메소드를 찾아서 호출
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					System.out.println(rm.value());
					System.out.println("cmd:"+cmd);
					if(rm.value().equals(cmd))
					{
						
						String jsp=(String)m.invoke(obj, request,response);
						System.out.println("cmd:"+cmd);
						// return "redirect:list.do"
						// return "main/list.jsp"
						if(jsp.startsWith("redirect")){
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						}
						else{
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
					
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
