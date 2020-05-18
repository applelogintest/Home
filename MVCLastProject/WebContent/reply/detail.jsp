<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row text-center">
				<img src="reply.jpg">
		</div>
		<h2 class="text-center">내용보기</h2>
		<div class="row">
			<table class="table">
				<tr>
					<th class="text-center danger" width="20%">번호</th>
					<td class="text-center" width="30%">${vo.no }</td>
					<th class="text-center danger" width="20%">작성일</th>
					<td class="text-center" width="30%">
						<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				
				<tr>
					<th class="text-center danger" width="20%">이름</th>
					<td class="text-center" width="30%">${vo.name }</td>
					<th class="text-center danger" width="20%">조회수</th>
					<td class="text-center" width="30%">${vo.hit }</td>
				</tr>
				
				<tr>
					<th class="text-center danger" width="20%">제목</th>
					<td class="text-left" colspan="3">${vo.subject }</td>
				</tr>
				
				<tr>
					<td class="text-left" colspan="4" valign="top" height="200">${vo.content }</td>
				</tr>
				
				<tr>
					<td class="text-right" colspan="4">
						<a href="../reply/reply.do?no=${vo.no }" class="btn btn-xs btn-primary">답변</a>
						<a href="../reply/update.do?no=${vo.no  }&type=2" class="btn btn-xs btn-info">수정</a>
						<a href="../reply/delete.do?no=${vo.no }" class="btn btn-xs btn-danger">삭제</a>
						<a href="../reply/list.do" class="btn btn-xs btn-warning">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>