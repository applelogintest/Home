<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 700px;
	
}

h2{
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var index=2;

$(function(){
	$('#add').click(function(){
		$('#user-table').append(
		'<tr id="f'+(index)+'">'
		+ '<td>'
		+ '파일 '+(index)+':'
		+ '<input type=file size=20>'
		+ '</td>'
		+ '</tr>'
		)
		index=index+1;
	})	
	$('#cancel').click(function(){
		if(index>1)
		{
			$('#f'+(index-1)).remove();
			index=index-1;
		}	
	})
})
</script>
</head>
<body>
	<div class="container">
		<h2>글쓰기</h2>
		<div class=row>
			<form method="post" action="insert_ok.jsp">
				<table class="table table-hover">
					<tr>
						<th width=20% class="text-right success">이름</th>
						<td width=80%>
							<input type="text" name=name size=15 required>
						</td>
					</tr>
					
					<tr>
						<th width=20% class="text-right success">제목</th>
						<td width=80%>
							<input type="text" name=subject size=50 required/>
						</td>
					</tr>
					
					<tr>
						<th width=20% class="text-right success">내용</th>
						<td width=80%>
							<textarea rows="8" cols="52" name=content required></textarea>
						</td>
					</tr>
					<tr>
						<th width="20%" class="text- right success">첨부파일</th>
						<td width="80%">
							<table class="table">
								<tr>
									<td class="text-right">
										<input type="button" id="add" class="btn btn-sm btn-primary" value="추가">
										<input type="button" id="cancel" class="btn btn-sm btn-danger" value="취소">
									</td>
									
								</tr>
							</table>
							<table class="table" id="user-table">
								<tr>
									<td>파일 1 : <input type="file" size=15 id="f1"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th width=20% class="text-right success">비밀번호</th>
						<td width=80%>
							<input type="password" name=pwd size=10 required/>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" class="text-center">
							<input type="submit" value="글쓰기" class="btn btn-sm btn-primary" />
							<input type="button" value="취소" class="btn btn-sm btn-danger" 
							onclick="javascript:history.back()"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>