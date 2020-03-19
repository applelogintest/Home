<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.member.*"%>
<%
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}

	String name=request.getParameter("name"); // name <input type=text name=name>
	String sex=request.getParameter("sex");
	String addr=request.getParameter("addr");
	String tel=request.getParameter("tel");
	String age=request.getParameter("age");
	
	MemberBean mb=new MemberBean();
	
	mb.setName(name);
	mb.setSex(sex);
	mb.setAddr(addr);
	mb.setTel(tel);
	mb.setAge(Integer.parseInt(age));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전송받은 데이터 출력</h1>
	이름:<%=mb.getName() %>
	성별:<%=mb.getSex() %>
	주소:<%=mb.getAddr() %>
	전화:<%=mb.getTel() %>
	나이:<%=mb.getAge() %>
</body>
</html>