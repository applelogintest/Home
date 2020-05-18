<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.times').hover(function(){
		$(this).css('cursor','pointer');
		$(this).attr("class","times success");
	},function(){
		$(this).css('cursor','none');
		$(this).attr("class","times");
	})
	
	$('.times').click(function(){
		var time=$(this).text();
		var h=time.substring(0,time.indexOf(":"));
		var m=time.substring(time.indexOf(":")+1);
		
		var t=h+"시 "+m+"분";
		
		$('#movie-time2').text(t);
		$('#rtime').val(t);
		
		$.ajax({
			type:'POST',
			url:'inwon.do',
			success:function(res)
			{
				$('#movie-inwon2').html(res);
			}
		})
	})
	
})
</script>
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<tr>
			<c:forEach var="time" items="${tList }">
				<td class="times">${time }</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>