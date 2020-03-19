<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
try{
	request.setCharacterEncoding("utf-8");
	}catch(Exception ex){}

	String mode=request.getParameter("mode");
	if(mode==null)
		mode="0";
	int no=Integer.parseInt(mode);
	
	String jsp="home.jsp";
	
	switch(no)
	{
	case 0:
		jsp="home.jsp";
		break;
	case 1:
		jsp="movie.jsp";
		break;
	case 2:
		jsp="music.jsp";
		break;
	case 3:
		jsp="find.jsp";
		break;
	case 4:
		jsp="find_ok.jsp";
		break;
	case 5:
		jsp="list.jsp";
		break;
	case 6:
		jsp="insert.jsp";
		break;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<%@include file="menu.jsp" %> <!-- 파일 하나 고정으로 넣어줘야함 변수넣어줘도 안됨 -->
		  
	<div class="container">
		<jsp:include page="<%=jsp %>"></jsp:include>	
	</div>
</body>
</html>