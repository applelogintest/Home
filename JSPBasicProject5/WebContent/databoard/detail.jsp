<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>

<%
	int a=10/0;
	String no=request.getParameter("no");
	String strPage=request.getParameter("page");
	// no=> DAO로 전송
	FileBoardDAO dao=new FileBoardDAO();
	// DAO에서 요청한 => VO를 받아 온다
	FileBoardVO vo=dao.boardDetailData(Integer.parseInt(no));
	// VO에 저장된 데이터를 HTML를 이용해서 출력
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 800px;
	
}

h2{
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// window.onload
/*
 *	window.onload=function()
 	{
	
 	}
 */
var i=0;
$(function(){
	//function delClick(){}
	// $('#delBtn') ======> document.getElementById("delBtn");
	// Selector
	/*
		 태그명 td{}       ==== $("td")
		   ID #id{}		==== $("#id명")
		Class .class{}  ==== $('.class명')
		
		 => <a>bbb</a>  $('a').text() 태그와 태그사이는 text  getter
		 => <a></a>     $('a').text("bbb") setter
		 
		 => <a href="kkk" id="k"> ==> $('#k').attr("href") ===> kkk
		 
		 => <input type="text" id="p" value="kkk"> => $('#p').val() ===> $('#p').val("ddd")
		 
		 => <td><span>ooo</span></td>  ===> $('td span').text() ===> ooo
		 									$('td').text() ===> ooo
		 									$('td').html() ===> <span>ooo</span>
		 
	*/
	$('#delBtn').click(function(){
		if(i==0)
		{
			$('#delBtn').val("취소");
			$('#del').show();
			i=1;
		}
		else
		{
			$('#delBtn').val("삭제");
			$('#del').hide();
			i=0;
		}
	});
	
	$('#sendBtn').click(function(){
		var pwd=$('#pwd').val();
		var no=$('#no').val();
		var page=$('#page').val();
		if(pwd=="")
		{
			$('#pwd').focus();
			return;
		}
		// delete.jsp?no=1&pwd=1234 => 404,500,200
				/*
					open('GET','delete.jsp',callback,true)
					send(no=1&pwd=1234)
					
					if(req.readyState==4)
					{
						if(req.status==200)
						{
							
						}
					}
				*/
		$.ajax({
			type:'POST',
			url:'delete.jsp',
			data:{"no":no,"pwd":pwd},
			//res 위에 url에 데이터값을 보낸뒤 다시 받는 변수
			success:function(res)  
			{
				var result=res.trim();
				if(result==0)
				{
					//alert(res);
					alert("비밀번호가 틀립니다!!");
					$('#pwd').val("");
					$('#pwd').focus();
				}
				else
				{
					location.href="list.jsp?page="+page;// 화면이동 sendRedirect
				}
			}
			
		});
	});
			
});
</script>
</head>
<body>
	<div class=container>
		<h2>내용보기</h2>
		<div class=row>
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-center success">번호</th>
					<td width=30% class="text-center"><%=vo.getNo() %></td>
					<th width=20% class="text-center success">작성일</th>
					<td width=30% class="text-center"><%=vo.getRegdate() %></td>
				</tr>
				
				<tr>
					<th width=20% class="text-center success">이름</th>
					<td width=30% class="text-center"><%=vo.getName() %></td>
					<th width=20% class="text-center success">조회수</th>
					<td width=30% class="text-center"><%=vo.getHit() %></td>
				</tr>
				
				<tr>
					<th width=20% class="text-center success">제목</th>
					<td colspan="3" class="text-left"><%=vo.getSubject() %></td>
				</tr>
				<%
					if(vo.getFilesize()!=0)
					{
				%>
						<tr>
							<th width=20% class="text-center success">첨부파일</th>
							<td colspan="3" class="text-left">
							<a href="download.jsp?fn=<%=vo.getFilename() %>"><%=vo.getFilename() %></a>(<%=vo.getFilesize() %>Bytes)</td>
						</tr>
				<%
					}
				%>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200" >
					 <pre style="white-space: pre-wrap; background-color: white; border:none; "><%=vo.getContent() %></pre>
				</td>
				</tr>
				
				<tr>
					<td colspan="4" class="text-right">
						<a href="reply.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-success">답변</a>
						<a href="update.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-primary">수정</a>
						<input type="button" class="btn btn-xs btn-danger" id="delBtn" value="삭제"/>
						<a href="list.jsp" class="btn btn-xs btn-info">목록</a>
					</td>
				</tr>
				<tr id="del" style="display:none">
					<td colspan="4" class="text-right">
						비밀번호:<input type="password" id="pwd" size=10 class="input-sm" />
							   <input type=hidden id="no" value="<%=no %>"/>
							   <input type=hidden id="page" value="<%=strPage %>"/>
							   <input type="button" value="삭제" class="btn btn-sm btn-danger" id="sendBtn"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>