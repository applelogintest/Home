<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>회원가입</h2>
		<form method=post action="join_ok2.jsp">
			<table border=1 width=350>
				<tr>
					<td width=20%>이름</td>
					<td width=80%><input type="text" name=name size=15/></td>
				</tr>
				
				<tr>
					<td width=20%>성별</td>
					<td width=80%><input type="text" name=sex size=15/></td>
				</tr>
				
				<tr>
					<td width=20%>주소</td>
					<td width=80%><input type="text" name=addr size=15/></td>
				</tr>
				
				<tr>
					<td width=20%>전화</td>
					<td width=80%><input type="text" name=tel size=15/></td>
				</tr>
				
				<tr>
					<td width=20%>나이</td>
					<td width=80%><input type="text" name=age size=15/></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<button>전송</button>
						<!-- 
							<input type="submit">
							<input type="image">
							<button>
							3가지 다 submit 기능							
						 -->
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>