<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" info="스트립트 요소"%>
<%--
	1. 스트립트릿
		<%
			일반 자바 코드 (메소드안에 코딩되는 내용)
			int s=10;
			if(s==10){
			
			}
		%>
	2. 표현식
		<%=	출력물 %>   => out.println(출력물);
		===========  ;을 사용할 수 없다.
	
	3. 선언식  ==> 사용금지  
	   <%! 
	   		public int sum(int a, int b)
	   		{
	   			return a+b;
	   		}
	   		
	   %>
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int a=100;
%>

<h1><%= a %></h1>
</body>
</html>