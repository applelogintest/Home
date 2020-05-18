<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#inwonBtn').click(function(){
		var ss=$('#ss').val();
		var s=$('#s').val();
		if(ss==0 && s==0)
		{
			alert("인원을 선택하세요");
			return;
		}
		
		var res="";
		var price1=0;
		var price2=0;
		if(ss!=0)
		{
			res+='[성인]:'+ss+","
			price1=ss*10000;
		}
		if(s!=0)
		{
			res+='[소인]:'+s;
			price2=s*8000;
		}
		var p=price1+price2;
		
		$('#movie-inwon').html(res);
		$('#movie-price').html(p);
		
		$('#rinwon').val(res);
		$('#rprice').val(p);
		
		$('#resBtn').attr('disabled',false);
	})
})
</script>
</head>
<body>
	<table class="table">
		<tr>
			<td>성인:
				<select id="ss" class="input-sm">
					<c:forEach var="i" begin="0" end="10">
						<option>${i }</option>
					</c:forEach>
				</select>
			</td>
			<td>소인:	
			<select id="s" class="input-sm">
				<c:forEach var="i" begin="0" end="10">
					<option>${i }</option>
				</c:forEach>
			</select>
			</td>
			<td>
				<input type="button" value="선택" id="inwonBtn" class="btn btn-sm btn-danger">
			</td>
		</tr>
	</table>
</body>
</html>