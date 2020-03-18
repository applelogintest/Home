<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./../css/style.css">
</head>
<body>
	<div id=container>
		<!-- 맨위 header부분 -->
		<header>
			<h3>header</h3>
		</header>
		
<<<<<<< HEAD
		<!-- 중간 content 부분   -->
=======
		<!-- 중간 content 부분  -->
>>>>>>> refs/remotes/origin/master
		<div id=content>
			<!-- slider div -->
			<div id=slider><h3>slider</h3></div>
			<!-- 항공 예매 찾기 div -->
			<div id=air_search><h3>air_search</h3></div>
			<!-- 여행 추천지 div -->
			<div id=recommend_tourist><h3>recommend_tourist</h3></div>
			<!-- 맛집,호텔 추천 div -->
			<div id="recommend_all">
				<jsp:include page="./content2.jsp"/>
			</div>
			<!-- side div -->
			<div id="side"><h3>side</h3></div>
		</div>		
		
		<!-- 맨밑 footer 부분 -->
		<footer>
			<h3>footer</h3>
		</footer>
	</div>
</body>
</html>