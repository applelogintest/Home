<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" buffer="16kb" autoFlush="true" session="true"
    info="게시판의 목록출력(2020-03-09 수정)"
    %>
    <%--
    	사용자가 데이터 전송(한글) ====> 서버가 값을 받는다.
				EUC-KR            EUC-KR, UTF-8
				UTF-8             EUC-KR(X), UTF-8
    			                               요청 처리
    			                               결과값 보내기 
			   => 파일 업로드, Ajax, React, Vue ==> UTF-8    			                             
    	
     --%>
<%--
		page 지시자 : JSP파일에 대한 정보
	     변환 코드
	     	contentType="text/html; charset=UTF-8"
	     	
	     	=> response.setContentType("text/html; charset=UTF-8")
	     	=> default : contentType="text/html; charset=ISO-8859-1"
	     	=> 변환 형식
	     		HTML => text/html
	     		XML => text/xml
	     		
	     	errorPage : error가 있는 경우에 지정한 파일로 이동
	     	
	     	import : 라이브러리를 읽어 올 경우 , 사용자 정의 클래스 
	     	====== default : java.lang, javax.servlet.*
	     	***** 기본은 page 지시자의 속성 => 한번만 사용한다
	     	2가지 방식으로 import를 한번에 여러개 사용가능
	     	<%@ page import="java.io.*,java.util.*"%>
	     	
	     	<%@ page import="java.io.*"%>
	     	<%@ page import="java.util.*"%>
	     	
	     	buffer : 출력버퍼 (HTML을 출력하는 메모리 공간) => 8kbyte
	     	autoFlush : 자동으로 버퍼의 메모리 공간을 지워준다(새로운 파일을 다시 읽기위해 지워야한다)
 --%>
<%--
	JSP 주석
	<!-- --> HTML 주석
	
	<%
		int a=10;
		//int b=20;
		/*int c=30;*/
	%>   
	JSP => JavaServer Page => 서버에서 실행되는 자바 파일
					  ==== web에서 파일이 아니라 => 페이지 (일반 사이트 75페이지 정도)
					  
	JSP
		1) 실행과정 a.jsp => a_jsp.java => a_jsp.class => 실행 (html)
		2) 지시자
			= page 지시자 : JSP파일에 대한 정보 (O)
				<%@ page 속성=값 속성=값.. %>
			= include 지시자 : JSP안에 다른 JSP를 첨부 (조립)
				<%@ include file=""%>
			= 태그 라이브러리
				<% taglib prefix="" uri=""%>
				<c:if>
				<c:forEach>
				<c:choose>
				<c:set>
				<c:out>
			
		3) 자바 코딩
		
			<% %> : 스크립트릿 => 일반 자바 코딩
			<%! %> : 선언식 => 멤버변수,메소드를 만들 경우
			<%= %> : 표현식 => 화면 출력(out.ptinln())
		3_1) 자바 파일 => DB연동, VO(자바빈)
		4) 내장 객체 : 미리 생성된 객체
				****request : 사용자 요청값,사용자 정보(IP,PORT)
				***response : 서버에서 응답
				***session : 서버에서 사용자 정보난 기타 내용 저장
				application : 서버 정보
				out : 출력 버퍼에 대한 정보 (메모리)  ==> <%= %> , ${}
				***pageContext : JSP와 JSP를 연결 => include, forward
				page : this
				config : 환경 설정 => web.xml에서 설정
				exception : 에외처리 => try ~ catch
				  
		5) 에러페이지
				= 한번에 처리 ==> page 지시자에 존재 (errorPage="이동할 파일명")
				= 분류해서 처리 => 톰캣이 알고 있어야 한다. (web.xml)
		
		6) 액션태그
			 <jsp:include>
			 <jsp:forward>
			 <jsp:useBean> : 메모리 할당
			 <jsp:setProperty> : 요청값을 받아서 처리
			 
		7) EL, JSTL
		8) MVC
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요 JSP</h1>
	<%
		int a=10/0;
	%>
	<%= a %>
</body>
</html>