<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	${연산자}
	==> 산술 연산자 
		+ , - , * , / , % 
		================= / (<%= 10/3 %>) => 3 (${10/3}) => 3.333 실수값 나옴
		+ : 산술연산, 문자열 결합
			"Hello" + 10 ==> Hello10
		+ : 순수하게 산술만 가능 
			"Hello" + 10 ==> error 
			"Hello"	+= 10 ==> Hello10 +=을 사용해야 문자열 결합이됨
	
	자바 : <%= "100" + "10" %> => 10010
		 ${"100"+"10"} => 110 (정수형으로 변환후 계산함)
		 ==> 문자열 결합 => (+=)
		 
		 <%= null+10 %> => error
		 ${null+10} => 10
		   ====
		      0
	비교 연산자 ===> true/false
		 == | eq (문자열 ,숫자) ==> ${requestScope.id=='admin'} ${requestScope.id eq 'admin'} 
		 != | ne (문자열, 숫자) ==> ${requestScope.id!='admin'} ${requestScope.id ne 'admin'} 
		 <  | lt ${10<5}
		 >  | gt ${10>5}
		 <= | le ${10=<5}
		 >= | ge ${10>=5}
		 
	논리 연산자 ===> true/false
		&& | and 
		|| | or  
		!  | not 
		
		String id; ===> null
		String id=""; ===> ""
	empty연산자 
		${empty list} 
	
	삼항연산자
		${조건?값1:값2}
		
	문자열 결합	: +=
	
	===> JSP안에서는 <% %> (X)
	
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%="Hello" %><br>
	${"Hello" }<br>
	<%="10" + "100" %><br>
	${"Hello" +=100 }
</body>
</html>