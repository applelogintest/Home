<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1 class="text-center">네이버 뉴스</h1>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<form method="post" action="news.do">
							검색:<input type="text" name=fd size=20 class="input-sm">
								<input type="submit" value="검색" class="btn btn-sm btn-primary">
						</form>						
					</td>
				</tr>
			</table>
			<c:forEach var="vo" items="${list }">
				<table class="table">
					<tr>
						<td><b><a href="${vo.link }">${vo.title }</a></b></td>
					</tr>
					<tr>
						<td>${vo.description }</td>
					</tr>
					<tr>
						<td class="text-right">
							${vo.author }
						</td>
					</tr>
					
				</table>
			</c:forEach>
		</div>
		
	</div>
</body>
</html>