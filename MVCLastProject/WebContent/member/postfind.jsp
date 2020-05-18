<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	magin: 0px auto;
	width: 500px;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

<script type="text/javascript">
/*
 	<input type="text" id="id"> ==> $('#id').val()
 	<td>aaa</td>		==> $('td').text() 	| $('td').text("ccc") 텍스트 값을 바꿔라
 	<td><span>aaa</span></td> ==> $('td').html()
 	<a href="aaa"> => $('a').attr("href")
 	
 	<input type="text" id="id"> ==> $('#id').val('admin')
 	$('td').append() 뒤에 이어 붙이기
 */
$(function () {
	$('#findBtn').click(function() {
		var dong=$('#dong').val();
		if(dong.trim()=="")
		{
			$('#dong').focus();
			return;
		}
		//alert(dong); .do?dong=신촌
		$.ajax({
			type:'POST',
			url:'../member/postfind_result.do',
			data:{"dong":dong},
			success:function(result){
				var div=$('#result').html(result);
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
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력:&nbsp;<input type="text" id="dong" size="15" class="input-sm">
								  <input type="button" id="findBtn" class="btn btn-sm btn-danger" value="입력">
					</td>
				</tr>
				<tr>
					<td class="text-right">
						<sub style="color:red">※동/읍/면을 입력하세요</sub>
					</td>
				</tr>
			</table>
			<div id="result">
				
			</div>
		</div>
	</div>
</body>
</html>