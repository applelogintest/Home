<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<%
	String id=request.getParameter("id");
	
	int temp=0;  // 사용자 입력체크
	int count=0; // 아이디 체크
	if(id!=null)
	{
		count=dao.idCheck(id);
		temp=1;
	}
	
	//"null"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 320px;
}
</style>
<script type="text/javascript">
function idcheck(){
	var id=document.frm.id.value;
	if(id.trim()=="")
	{
		document.frm.id.focus();
		return;
	}
	document.frm.submit();
}
//opener 확인버튼이 있는 창 document는 확인버튼을 눌러서 켜진 창 
function ok(){
	opener.frm.id.value=document.frm.id.value;
}
</script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">아이디중복체크</h3>
		<div class="row">
			<table class="table">
				<tr>
					<td>
					<form method="post" action="idcheck.jsp" name=frm>
				입력:<input type="text" name=id size=15 value="<%=id!=null?id:""%>">
					<input type="button" value="검색" class="btn btn-sm btn-primary" onclick="idcheck()">
					</form>
					</td>
					
				</tr>
				<tr>
					<td class="text-center">
						<%	
							if(temp==0)
							{
						%>
								<font color=gray>ID를 입력하세요</font>
						<%
							}
							if(count==0 && temp==1)
							{
						%>
								<font color=blue><%=id %>는(은) 사용 가능합니다</font>	
						<%
							}
							else if(count==1 && temp==1)
							{
						%>
								<font color=red><%=id %>는(은) 이미 사용중입니다</font>
						<%								
							}
						%>
					</td>
				</tr>
				<%	
					if(count==0 && temp==1)
					{
				%>
				<tr>
					<td class="text-center">
						<input type="button" value="확인" class="btn btn-sm btn-danger" onclick="ok()">
					</td>
				</tr>
				<%
					}
				%>
			</table>
			
		</div>
	</div>

</body>
</html>