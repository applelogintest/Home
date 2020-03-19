<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	jsp액션 태그 (7장,8장)
	1) <jsp:include> : JSP안에 특정부분에 다른 JSP를 첨부 => 모듈화
		pageContext.include("jsp파일명")
	2) <jsp:forward> : request를 계속 유지할 경우 (MVC구조)
	3) <jsp:useBean> : 메모리 할당
		<jsp:useBean id="vo" class="com.sist.doa.MemberVO">
			    	 =======
			 => MemberVO vo=new MemberVO();
			 			 ==
	4) <jsp:setProperty> : setXxx()에 값을 채운다
		=====> VO,DTO,BEAN(JSP)
		Bean
			1) 데이터 관리빈 => 데이터를 모아서 관리
							읽기/쓰기 =====> getter/setter
			2) 데이터 액션빈 => 빈(VO) 값을 설정하는 클래스
			   ==========
			   				데이터 베이스 연결 ~DAO, ~Manager, (DAO+DAO) ~Service
			public class SawonVO{
				private int sabun;
				private String name;
				private String dept;
				
				publiv int getSabun()
				{
					return sabun;
				}
				public void setSabun(int sabun)
				{
					this.sabun=sabun;
				}
			} 
			
			=> SawonVO vo=new SawonVO();
			   vo.setSabun(1);
			   vo.setName("홍길동");
			   vo.setDept("개발부");
			   
			   ==> jsp 액션 태그
			   <jsp:useBean id="vo" class="SawonVO">
			   		<jsp:setProperty name="vo" property="sabun" value="1"/>
			   		vo.setName("홍길동");
			   		<jsp:setProperty name="vo" property="name" value="홍길동"/>
			   		vo.setDept("개발부");
			   		<jsp:setProperty name="vo" property="dept" value="개발부"/>
			   </jsp:useBean>
			   
			   ==> 다른 JSP에서 값을 보낸다
			    String sabun=request.getParameter("sabun");
			    String name=request.getParameter("name");
			    String dept=request.getParameter("dept");
			    
			    SawonVO vo=new SawonVO();
			    vo.setSabun(Integer.parseInt(sabun));
			    vo.setName(name);
			    vo.setDept(dept);
			    
			    <jsp:useBean id="vo" class="SawonVO">
			    	<jsp:setProperty name="vo" property="*"/> //자동 형변환도해줌
			    </jsp:useBean>
			   
			   <jsp:getProperty name="vo" property="name"> (X) 거의 사용x 
			   ===========================================
			   	 <%=vo.getName()%>
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