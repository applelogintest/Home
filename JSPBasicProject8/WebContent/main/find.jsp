<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
 <jsp:useBean id="dao" class="com.sist.dao.MovieDAO"/>
 <%
 	List<MovieBean> list=dao.movieFindData(1);
 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<table class="table table-hover">
			<tr>
				<td>
					<form method=post action="main.jsp  ">
						<input type=text name=ss size=20 class="input-sm">
						<input type=hidden name=mode value="4">
						<button class="btn btn-sm btn-danger">검색</button>
					</form>
				</td>
			</tr>
		</table>
		<table class="table table-hover">
			<tr>
				<th></th>
				<th>영화명</th>
				<th>장르</th>
			</tr>
			<%
				for(MovieBean mb:list)
				{
			%>
					<tr>
						<td><img src="<%=mb.getPoster() %>" width=30 height="30"></td>
						<td><%=mb.getTitle() %></td>
						<td><%=mb.getGenre() %></td>
					</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>