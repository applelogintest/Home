<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#pwd2').keyup(function(){
		var k=$(this).val();
		var no=$('#no').val();
		
		$.ajax({
			type:'POST',
			url:'../reply/password_check.do',
			data:{"pwd":k,"no":no},
			success:function(res){
				var no=res.trim();
				if(no==1)
				{
					$('#result').html("<font color=blue>비밀번호가 맞습니다. 수정할 수 있습니다</font>")
					$('#updateBtn').attr('disabled',false)
				}
				else
				{
					$('#result').html("<font color=red>비밀번호가 틀립니다</font>")
					$('#updateBtn').attr('disabled',true)	
				}
			}
		})
	})
})
</script>
<style type="text/css">
.row{
	margin: 0px auto;
	width: 600px;
	
}

h2{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h2>수정하기</h2>
		<div class=row>
			<form method="post" action="../reply/update_ok.do">
				<table class="table table-hover">
					<tr>
						<th width=20% class="text-right success">이름</th>
						<td width=80%>
							<input type="text" name=name size=15 value="${vo.name }" required>
							<input type="hidden" name=no value="${vo.no }" id="no">
						</td>
					</tr>
					
					<tr>
						<th width=20% class="text-right success">제목</th>
						<td width=80%>
							<input type="text" name=subject size=50 value="${vo.subject }" required/>
						</td>
					</tr>
					
					<tr>
						<th width=20% class="text-right success">내용</th>
						<td width=80%>
							<textarea rows="8" cols="52" name=content required>${vo.content }</textarea>
						</td>
					</tr>
					
					<tr>
						<th width=20% class="text-right success">비밀번호</th>
						<td width=80%>
							<input type="password" name=pwd size=10 id="pwd2" required/>
							<p id="result"></p>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" class="text-center">
							<input type="submit" value="수정" class="btn btn-sm btn-primary" 
							id="updateBtn" disabled/>
							<input type="button" value="취소" class="btn btn-sm btn-danger" 
							onclick="javascript:history.back()"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</html>