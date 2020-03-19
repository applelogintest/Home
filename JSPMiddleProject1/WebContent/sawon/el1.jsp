<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		EL : 화면에 출력하는 언어
		=> 사용법
			${출력물} => {} 일반 자바변수(X)
		<%= 일반 자바변수 %>
		${} => getParameter("id") => ${param.id}
		${} => request.getAttribute("id") 
				=> ${requestScope.id} => ${id}
				     ============= 생략가능(많이 사용하는 객체여서 생략을 가능하게 한듯)
		${} => session.getAttribute("name","홍길동")
				=> ${sessionScope.name} => ${name} //생략은 가능하지만 request가 우선순위가 높아서 키값이 같을경우 request 값만 가져오게 된다 
		예)
			String id="admin";
			${id}(X)
			request.setAttribute("id",id);
			${id}(O)
			$는 request안에 만들어가 있는 값만 가져올수 있다
			
			request.setAttribute("id1","admin");
			session.setAttribute("id2","hong");
			
			${id1} => admin ${id2} => hong
			${sessionScope.id} ==> hong
		
--%>
<%
	String name="홍길동";
	request.setAttribute("name", name);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : ${name}
</body>
</html>