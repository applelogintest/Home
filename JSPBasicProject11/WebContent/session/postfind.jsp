<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DiaryDAO"></jsp:useBean>
<%	
	try{
		request.setCharacterEncoding("UTF-8");		
	}catch(Exception ex){}

	String dong=request.getParameter("dong");

	List<ZipcodeBean> list=null;
	
	if(dong!=null)
	{
		list=dao.postFind(dong);
	}
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
	width: 430px;
}
td{
	font-size: 8px;
}

</style>
<script type="text/javascript">
function postfind(){
	var dong=document.frm.dong.value;
	if(dong.trim()=="")
	{
		document.frm.dong.focus();
		return;
	}
	
	document.frm.submit();
	
}
function ok(zip,addr){
	opener.frm.post1.value=zip.substring(0,3);
	opener.frm.post2.value=zip.substring(4,7);
	opener.frm.addr1.value=addr;
	self.close();
}
</script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">우편번호 검색</h3>
		<div class="row">
			<form method="post" action="postfind.jsp" name=frm>
			<table class="table">
				<tr>
					<td>
					입력: <input type="text" name="dong" size=15 class="input-sm" value="<%=dong!=null?dong:""%>">
						 <input type="button" value="찾기" class="btn btn-sm btn-primary" onclick="postfind()">
					</td>
				</tr>
				<tr>
					<td class="text-right">
						<sub><font color="red">※동/읍/면을 입력하세요</font></sub>
					</td>
				</tr>
			</table>
			</form>
			<%
				if(list!=null && list.size()>0)
				{
			%>
					<table class="table table-hover">
						<tr>
							<th width=20% class="text-center">우편번호</th>
							<th width=80% class="text-center">주소</th>
						</tr>
						
						<%
							for(ZipcodeBean vo:list)
							{
						%>
								<tr>
									<td width=20% class="text-center"><%=vo.getZipcode() %></td>
									<td width=80%><a href="javascript:ok('<%=vo.getZipcode() %>','<%=vo.getAddress() %>')"><%=vo.getAddress() %></a></td>
								</tr>
						<%
							}
						%>
					</table>
			<%		
				}
			%>
		</div>
	</div>
</body>
</html>