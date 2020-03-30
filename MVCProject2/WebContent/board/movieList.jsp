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
		<h2 class="text-center">영화목록</h2>
		<div class="row">
			<c:forEach var="vo" items="${list}">
				<div class="col-md-4">
					<div class="panel panel-success">
				      <div class="panel-heading">
				      	${vo.title }
				      </div>
				      <div class="panel-body">
				      	<a href="list.do"><img src="${vo.poster}" width=290px; height=180px;></a>
				      </div>
				   </div>
				</div>
			</c:forEach>
		</div>
		<div class="row text-center">
			<a href="movieList.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-primary">이전</a>
			${curpage} page / ${totalpage } pages
			<a href="movieList.do?page=${curpage<totalpage? curpage+1:curpage }" class="btn btn-sm btn-danger">다음</a>
		</div>
	</div>
</body>
</html>