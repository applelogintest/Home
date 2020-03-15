<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
	List<MusicVO> list=MovieDAO.musicAllData();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1 class="text-center">뮤직 Top 200</h1>
			<table>
				<tr>
					<th width="10%" style="text-align: center">순위</th>
					<th width="10%" style="text-align: center"></th>
					<th width="40%" style="text-align: center">곡명</th>
					<th width="20%" style="text-align: center">가수명</th>
					<th width="20%" style="text-align: center">앨범</th>
				</tr>
				<%
					for(MusicVO vo:list)
					{
				%>
						<tr>
							<td style="text-align: center"><%=vo.getRank() %></td>
							<td><img src="<%=vo.getPoster() %>" width="50" height="50"></td>
							<td><%=vo.getTitle() %></td>
							<td><%=vo.getSinger() %></td>
							<td><%=vo.getAlbum() %></td>						
						</tr>		
				<%
					}
				%>
			</table>
	</div>
</body>
</html>