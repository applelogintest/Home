<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1 class="text-center">예매 현황</h1>
		<div class="row">
			<table class="table">
				<tr>
				</tr>
			</table>
			<table class="table">
				<tr class="success">
					<th class="text-center">예매번호</th>
					<th class="text-center">아이디</th>
					<th class="text-center"></th>
					<th class="text-center">영화명</th>
					<th class="text-center">극장명</th>
					<th class="text-center">예매일</th>
					<th class="text-center">예매시간</th>
					<th class="text-center">인원</th>
					<th class="text-center">금액</th>
					<th class="text-center">승인상태</th>
				</tr>				
				<c:forEach var="vo" items="${list }">
					<tr>
						<td class="text-center">${vo.rno }</td>
						<td class="text-center">${vo.id }</td>
						<td class="text-center">
							<img src="${vo.poster }" width="30" height="30">
						</td>
						<td class="text-center">${vo.title }</td>
						<td class="text-center">${vo.tname}</td>
						<td class="text-center">${vo.rdate }</td>
						<td class="text-center">${vo.rtime }</td>
						<td class="text-center">${vo.rinwon }</td>
						<td class="text-center">${vo.rprice }</td>
						<td class="text-center">
							<c:if test="${vo.isReserve==0 }">
								<a href="admin_update.do?rno=${vo.rno }" class="btn btn-sm btn-danger">승인대기</a>
							</c:if>
							
							<c:if test="${vo.isReserve==1 }">
								<span style="color:blue">승인완료</span>
							</c:if>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>