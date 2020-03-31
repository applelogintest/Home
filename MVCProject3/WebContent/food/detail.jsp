<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
				<table class="table">
					<tr>
					<%-- 	<%
							StringTokenizer st=new StringTokenizer(vo.getImage(),"^");
							while(st.hasMoreTokens())
							{
						%>
								<td class="text-center"><img src="${st.nextToken()}" width="98%"></td>
						<%
							}
						%> --%>
						<c:forTokens items="${vo.image }" delims="^" var="img">
							<td class="text-center"><img src="${img}" width="100%"></td>
						</c:forTokens>
					</tr>
					<tr>
						<td class="text-center" colspan=5>
							<h3>${ vo.title} &nbsp;<span style="color:#FC6">${vo.score }</span></h3>
						</td>
					</tr>
					
					<tr>
						<td class="text-right" width=15%>주소</td>
						<td colspan="4">
							${vo.address }
						<%-- 	<%
											String temp=vo.getAddress();
											String a1=temp.substring(0,temp.indexOf("지"));
											String a2=temp.substring(temp.indexOf("지")+3);
											
										%>
										<%=a1 %><br>
										<sub style="color:gray"><%=a2 %></sub>  --%>
						</td>						
					</tr>
					
					<tr>
						<td class="text-right" width=15%>전화번호</td>
						<td colspan="4">${vo.tel } </td>						
					</tr>
					<tr>
						<td class="text-right" width=15%>음식종류</td>
						<td colspan="4">${vo.type}</td>						
					</tr>
					<tr>
						<td class="text-right" width=15%>가격대</td>
						<td colspan="4">${vo.price }</td>						
					</tr>
					<tr>
						<td class="text-center" colspan=5>
							좋아요:${vo.good }&nbsp;&nbsp; 별로:${vo.soso }&nbsp;&nbsp; 나쁨:${vo.bad }
						</td>
					</tr>
					
					
					<tr>
						<td class="text-right" colspan=5>
							<a href="#" class="btn btn-sm btn-danger">찜</a>
							<a href="#" class="btn btn-sm btn-success">예약</a>
							<a href="category.do" class="btn btn-sm btn-info">목록</a>
						</td>
					</tr>
					
				</table>
			</div>
		</div>
</body>
</html> 