<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	저장 장소 : 웹 브라우저에 저장(검색어,방문한 사이트, 상세보기 저징 ==> 장바구니)
	동작 과정
		1) 생성 단계
			Cookie cookie=new Cookie("key","값") => Map형식 (key중복되면 안된다)
												=> 문자열만 저장이 가능
		2) 저장 단계 : response.addCookie(cookie)
		
		3) Cookie 읽기
		   Cookie[] cook=request.getCookie(); => ArrayList에 저장후에 출력
		
		4) 삭제 , 저장 기간
		     삭제 => setMaxAge(0)
		     저장 기간 => setMaxAge(60*60*24)
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
