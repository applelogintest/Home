<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./../css/style.css">
</head>
<body>
	<%=request.getAttribute("test")%>
	<h3>&nbsp;No. 맛집이름</h3>
	<span style="color: #9B9B9B">&nbsp;&nbsp;&nbsp;맛집 주소</span>
	<pre style="white-space: pre-wrap; border: 0px;">맛집 댓글? || 맛집에 대한설명 </pre>
</body>
</html>