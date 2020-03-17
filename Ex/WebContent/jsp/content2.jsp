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
					<h3>&nbsp;No. 맛집이름</h3>
					<span style="color: #9B9B9B">&nbsp;&nbsp;&nbsp;맛집 주소</span>
					<pre style="white-space: pre-wrap; border: 0px;">맛집 댓글? || 맛집에 대한설명 </pre>
				</div>
			</div>
	<%
		}
	%>

</body>
</html>