<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
<%
	try{
		request.setCharacterEncoding("UTF-8");		
	}catch(Exception ex){}
%>
<jsp:useBean id="vo" class="com.sist.dao.BoardBean">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>

<%
	dao.boardInsert(vo);
	//이동
	response.sendRedirect("main.jsp?mode=5");
%>


