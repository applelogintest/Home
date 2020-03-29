<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./../css/style.css">
</head>
<body>
	<%
		for(int i=0; i<2; i++)
		{
	%>
			<div class=recommend_food>
				<div class=food_img>
					<img src="https://kr.000webhost.com/static/default.000webhost.com/themes-preview/travel_vacation/original/img/blog-img/<%=i+3 %>.jpg">
				</div>
				<div class=food_content>
					
					<jsp:include page="food.jsp"></jsp:include>
				</div>
			</div>
	<%
		}
	%>

</body>
</html>