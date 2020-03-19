<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
   
<%
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}
%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<jsp:useBean id="vo" class="com.sist.dao.MemberBean">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>

<%
	// 연결
	dao.memberInsert(vo);
	// 이동
	response.sendRedirect("login.jsp");
%>