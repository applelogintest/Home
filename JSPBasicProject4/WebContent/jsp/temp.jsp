<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no=request.getParameter("no");
	int n=Integer.parseInt(no);
	if(n==1)
	{
		response.sendRedirect("redirect.jsp");	
	}
	else
	{
		pageContext.forward("forward.jsp"); // 흐름제어
		// <jsp:forward page="forward.jsp">
	}
%>
