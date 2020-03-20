<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	JSTL 
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	 	   ===========> 변수선언
	 	   				<c:set> ==> request.setAttribute("id","admin")
	 	   				<c:set var="id" value="admin">
	 	   				==> ${id}
	 	   				*** <c:out value="test"> => out.println()
	 	   				    ==================== ${}
	 	   ===========> 제어문
	 	   				<c:if> ==> else가 존재하지 않는다
	 	   				if(조건문)
	 	   				{
	 	   					실행문장
	 	   				}
	 	   				=> <c:if test="조건문">
	 	   						실행문장
	 	   					</c:if>
	 	   				<c:forEach>
	 	   				for(int i=1; i<=10; i++)
	 	   				{
	 	   				}
	 	   				=> <c:forEach var="i" begin="1" end="10" step="1">
	 	   												=======  ========
	 	   												 i<=10    step은 1일 경우에
	 	   												 *** 단점 : step="-1" => 감소는 없다
	 	   												 
	 	   					</c:forEach>
	 	   				for(MoiveVO vo:list)
	 	   				{
	 	   				}
	 	   				=> <c:forEach var="vo" items="list">
	 	   							  ===	   ====== 속성
	 	   				   </c:forEach>
	 	   				<c:choose> : 다중 조건문, 선택문
	 	   				
	 	   				<c:choose>
	 	   					<c:when test="조건문"> 실행문장 </c:when> if{}
	 	   					<c:when test="조건문"> 실행문장 </c:when> else if{}
	 	   					<c:when test="조건문"> 실행문장 </c:when> else if{}
	 	   					<c:when test="조건문"> 실행문장 </c:when> else if{}
	 	   					<c:otherwise></c:otherwise> default else{}
	 	   				</c:choose>
	 	   				JSTL => XML로 제작
	 	   				        ===
	 	   				***열고 닫기, 지원하는 속성외의 다른 속성을 이용하면 에러가 난다
	 	   				***속성값은 반드시  ""를 사용해야 된다
	 	   				<c:set var=i/> (error) 
	 	   				
	 	   				***<c:forTokens var="s" value="red,blue,green" delimt=",">
	 	   					StringTonkenizer()
	 	   				
	 	   ===========> URL
	 	   			     <c:redirect url=""> : 화면 이동(response.sendRedirect())
	 	   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
	 	   = fmt
	 	   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fn" %>
	 	   = fn ====> String,Collection
	 	   				${fn:title.substring()}
	 ***= fmt
 	 ***= fn
 	 =========
	 = sql =============> DAO
	 = xml =============> Parser (JAXP,JAXB)
	 ========= 사용하지 않음
--%>
<%
	int sex=1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Java로 코딩을 할 경우</h2>
<%
	if(sex==1)
	{
%>
		남자입니다
<%
	}
	else
	{
%>
		여자입니다
<%		
	}
%>
<br>
<h2>JSTL을 이용할 경우</h2>
<c:set var="sex" value="2"></c:set>
<%--
	c:set
	===== var ===> key
	      value ==> 값
	 requset.setAttribute("key","값");
 --%>
<c:if test="${sex==1}">
<%--
	c:if test ==> 조건문 작성하는 위치
 --%>
	남자입니다
</c:if>
<c:if test="${sex==2}">
	여자입니다
</c:if>

</body>
</html>