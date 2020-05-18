<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$.ajax({
		type:'POST',
		url:'../recipe/recipe_find_ok.do',
		data:{"no":1},
		success:function(res)
		{
			$('#recipes').html(res);
		},
		error:function(e)
		{
			alert(e);
		}
	})
	
	$('.images').hover(function(){
		$(this).css('cursor','pointer');
		
	},function(){
		$(this).css('cursor','none');
	})	
	
	$('.images').click(function(){
		let no=$(this).attr('value');
		$.ajax({
			type:'POST',
			url:'../recipe/recipe_find_ok.do',
			data:{"no":no},
			success:function(res)
			{
				$('#recipes').html(res);
			},
			error:function(e)
			{
				alert(e);
			}
		})
	})
})
</script>
</head>
<body>
	<div class="wrapper row2">
		<div id="services" class="clear">
			<div class="col-md-4">
				<c:forEach var="i" begin="1" end="58">
					<img src="image2/${i }.png" style="width:50px; height:50px" value="${i }" class="images"
					title="${nlist[i-1] }">
				</c:forEach>
			</div>
			<div class="col-md-8" id="recipes">
				
			</div>
		</div>
	</div>
</body>
</html>