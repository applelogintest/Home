<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<table width="100%">
				<tr>
					<td>
						<c:forEach var="vo" items="${list }">
							<table class="table table-hover">
								<tr>
									<td width=30% class="text-center" rowspan="3">
										<a href="detail.do?no=${vo.no}"><img src="${vo.image}>" width=350px height=180px class="img-rounded"></a>
									</td>
									<td width=70%>
										<a href="detail.do?no=${vo.no }"><h3>${vo.title}&nbsp;<span style="color:#FC6">${vo.score} </span></h3></a>
									</td>
								</tr>
								<tr>
									<td width=70%>
										${vo.address }
									<%-- ${f:substring(vo.address,0,9) }<br>
									지번) ${f:substring(vo.address,3)}<br>  --%>
									</td>
								</tr>
								<tr>
									<td width=70%>${vo.tel }</td>
								</tr>
							</table>
						
						</c:forEach>
					</td>
				</tr>
			</table>
		</div>
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-right">
						<a href="category.do" class="btn btn-lg btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>