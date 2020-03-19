<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
<%
	List<BoardBean> list=dao.boardListData();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자료실</h2>
		<div class="row">
			<table class="table">
				<tr>
					<td style="border: 0px;">
						<a href="main.jsp?mode=6" class="btn btn-sm btn-info">새글</a>
					</td>
				</tr>
			</table>
			<table class="table table-hover">
				<tr class="warning">
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center">제목</th>
					<th width=15% class="text-center">이름</th>
					<th width=20% class="text-center">작성일</th>
					<th width=10% class="text-center">조회수</th>
				</tr>
				<%
					for(BoardBean vo:list)
					{
				%>
						<tr>
							<td width=10% class="text-center"><%=vo.getNo() %></td>
							<td width=45% class="text-center"><%=vo.getSubject() %></td>
							<td width=15% class="text-center"><%=vo.getName() %></td>
							<td width=20% class="text-center"><%=vo.getRegdate() %></td>
							<td width=10% class="text-center"><%=vo.getHit() %></td>
						</tr>
						
				<%
					}
				%>
			</table>
		</div>
</body>
</html>