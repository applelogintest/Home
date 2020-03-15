<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	application 객체 ==> ServletContext
		=> Servlet (Sever+let) let(가벼운 프로그램) => web => JSP
		   MIDlet(WML) , Applet
		   
		   Java SE : Application
		   Java EE : 기업용 환경(WEB)
		   Java ME : 모바일
		   
		   Servlet(연결) VS JSP(화면 UI)
		   ==========================
	브라우저 ==> 사용자가 데이터 전송 ===> 받을 수 있는 파일(Servlet,JSP)
	==================================================
	 데이터 ====> Servlet ===> Java ===> JSP ===> MVC구조 (개발,유지보수)
	==================================================
	
	application
		서버와 관련
			1) 서버 정보							   < 차이가큼   <= 거의 차이가 없음
				= getServerInfo()  ==> 3.1     1.7   1.8         1.8_01       1.12
				= 버전 정보 getMajorVersion() 
				= 		 getMinorVersion()
				= 
			2) 자원 정보
				= getRealPath()
			3) web.xml을 읽어서 처리 => log파일
				= getInifParameter()
			
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	서버이름 : <%=application.getServerInfo() %><br>
	버전 : <%=application.getMajorVersion() %><br>
			<%=application.getMinorVersion() %><br>
	<%
		String driver=application.getInitParameter("driver");
		String url=application.getInitParameter("url");
		String username=application.getInitParameter("username");
		String pwd=application.getInitParameter("password");
		
		application.log("Driver:"+driver);
		application.log("url:"+url);
		application.log("username:"+username);
		application.log("password:"+pwd);
	%>
</body>
</html>