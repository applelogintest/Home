<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper row2">
		<div id="services" class="clear">
			<div class="row text-center">
				<img src="reply.jpg">
			</div>
			
			<div class="row clear" >
				<table class="table">
					<tr>
						<td>
							<a href="../reply/insert.do" class="btn btn-sm btn-danger">새글</a>
						</td>
					</tr>
				</table>
				<table class="table">
					<tr class="success">
						<th width="10%" class="text-center">번호</th>
						<th width="45%" class="text-center">제목</th>
						<th width="15%" class="text-center">이름</th>
						<th width="20%" class="text-center">작성일</th>
						<th width="10%" class="text-center">조회수</th>
					</tr>				
					<c:forEach var="vo" items="${list }">
						<tr>
							<td width="10%" class="text-center">${vo.no }</td>
							<td width="45%">
								<c:if test="${vo.group_tab>0 }">
									<c:forEach var="i" begin="1" end="${vo.group_tab }" step="1">
										&nbsp;&nbsp;
									</c:forEach>
									<img src="icon_reply.gif">
								</c:if>
									<a href="../reply/detail.do?no=${vo.no }">${vo.subject }</a>
							</td>
							<td width="15%" class="text-center">${vo.name }</td>
							<td width="20%" class="text-center"><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/></td>
							<td width="10%" class="text-center">${vo.hit }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="row text-center">
				<a href="../reply/list.do?page=${curpage>1? curpage-1:curpage }" class="btn btn-sm btn-primary">이전</a>
					${curpage } page / ${totalpage } pages
				<a href="../reply/list.do?page=${curpage<totalpage? curpage+1:curpage }" class="btn btn-sm btn-primary">다음</a>
			</div>
		</div>
	</div>
</body>
</html>