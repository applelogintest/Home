<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	내장객체 (기본객체) => 미리 만들어준 객체
		HttpServletRequest request
		HttpServletResponse response
		================================
		
		PageContext pageContext;
	    HttpSession session = null;
	    ServletContext application;
	    ServletConfig config;
	    JspWriter out = null;
	    Object page = this;
	    JspWriter _jspx_out = null;
	    
	    request 객체
	     = 사용자 요청 정보
	     	***1) String getParameter() => 단일값 ===============> list.jsp?page=1&no=10 (공백을 주면 데이터 받을수 없음 공백주의)
	     								getParameter("page")
	     								getParameter("no")
	     	***2) String[] getParameterValues() => 다중값   ===============> list.jsp?no=1&no=2&no=3
	     								getParamterValues("no"); ==> checkbox,select(multi)
	     	***3) setCharacterEncoding("UTF-8") => 한글 변환
	     	
	     	https://www.google.com/search?q=%EC%9E%90%EB%B0%94&oq=%EC%9E%90%EB%B0%94&aqs=chrome..69i57j35i39l2j0l3j69i65l2.2025j0j7&sourceid=chrome&ie=UTF-8
	     = 사용자의 브라우저 정보
	     	***1) getRemoteAddr()  ==> 사용자의 IP
	     	2) getContentType() ==> text/html,text/xml 
	     	3) getProtocol()    ==> 사용자가 이용한 프로토콜  ==> http,https,ws(web socket)
	     	4) getMethod()   ==> GET/POST
	     	***5) getRequestURI() 
	     	***6) getContextPath()
	     	
	     	http://localhost/JSPBasicProject2/object/request.jsp
	     	==================================================== URL => getRequestURL()
	     	               ===================================== URI => getRequestURI()
	     	               ================== getContextPath
	     	http://localhost:80
	     = 서버 정보
	     	1) getServerName() => localhost 
	     	2) getServerPort() => 80 
	     = 데이터 추가 정보
	     	***1) setAttribute() : 데이터 추가
	     	***2) getAttribute() : 추가된 데이터 읽기
	  
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 
	1) 데이터 형
		var : 자동 지정 변수
		var a=1 => a(int)
		var a=10.5 => a(double)
		var a='admin' => String
		var a="admin" => String
		var a=[] ==> Array =======> [10,10.5,"",{}]
		var a={} ==> Object
		========================= var단점 (사용범위가 명확하지 않다) ==> let,const(상수형변수)
		var a=10;
		a=20;
		
		const a=10;
		a=20; (X)
	2) 연산자
		산술연산자(+,-,*,/)
		비교연산자(==, != , < , > , <= , >= )
		논리연산자( &&, ||)
		대입연산자(= , +=, -=)
	3) 제어문
		if, if~else, for, while, do~while, switch~case
	4) 함수
		function func_name(addr) ==> 매개변수
		{
		}
		var func=function(){
		}
		
		******************** 람다식
		var func=()=>{
		}
		********************
	5) 객체 구조
		window : 브라우저 ==> open
		document : 출력화면
		form    : 입력창
		location : 화면 이동
		history : 이전 => back(), forward() => go()
 -->
<!-- <script type="text/javascript">
function send(){
	var name=window.document.frm.name.value;
	if(name.trim()==""){
		alert("이름을 입력하세요!!");
		document.frm.name.focus();
		return;
	}
		
	function content(){
		var content=window.document.frm.content.value;
		if(content.trim()==""){
			alert("소개를 입력하세요!!");
			document.frm.content.focus();
			return;
		}
	document.frm.submit();
}
</script> -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
//window.onload
$(function(){
	$('#btn').click(function(){
		var name=$('#name').val();	
		if(name.trim()==""){
			alert("이름을 입력하세요!!");
			$('#name').focus();
			return;
		}
		
		var content=$('#content').val();
		if(content.trim()==""){
			alert("소개를 입력하세요!!");
			$('#content').focus();
			return;
		}
		
		$('#frm').submit();
	});
	
	
	
});

</script>
</head>
<body>
	<!--  데이터 전송 방법 : request  -->
	<center>
		<h1>개인 정보</h1>
		<form method="post" action="request_ok.jsp" name=frm id=frm>
		<table border=1 width=450>
			<tr>
				<th width="20%">이름</th>
				<td width="80%"><input type=text name=name id=name size=15/></td>
			</tr>
			
			<tr>
				<th width="20%">성별</th>
				<td width="80%">
					<input type="radio" name=sex value="남자" checked />남자
					<input type="radio" name=sex value="여자"/>여자
				</td>
			</tr>
			
			<tr>
				<th width="20%">전화</th>
				<td width="80%">
					<select name=tel1>
						<option>02</option>
						<option>031</option>
						<option>032</option>
						<option>041</option>
						<option>051</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<th width="20%">취미</th>
				<td width="80%">
					<input type="checkbox" name=hobby value=운동 />운동
					<input type="checkbox" name=hobby value=낚시 />낚시
					<input type="checkbox" name=hobby value=게임 />게임
					<input type="checkbox" name=hobby value=독서 />독서
					<input type="checkbox" name=hobby value=등산 />등산
				</td>
			</tr>
			
			<tr>
				<th width="20%">소개</th>
				<td width="80%">
					<textarea rows="5" cols="35" name=content id=content></textarea>				
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="전송" id="btn">
				</td>
				
			</tr>
			
		</table>
		</form>
	</center>
</body>
</html>