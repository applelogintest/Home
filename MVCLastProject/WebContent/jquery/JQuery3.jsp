<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.sql.*"%>
<%
	try
	{	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		Connection conn=DriverManager.getConnection(url,"hr","happy");
		String sql="SELECT rank,title,singer,poster FROM music_genie";
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#keyword').keyup(function(){
		let k=$(this).val();
		//console.log(k);
		$('#user-table > tbody > tr').hide();
		let temp=$('#user-table > tbody > tr > td:nth-child(4n+3):contains("'+k+'")');
		/*
			==============================================
				  1         2          3         4	
			==============================================
				  5         6          7         8
			==============================================
			
			==============================================
					
				<tr>		$('td').parent().show()
				  <td></td> $('tr').child().first()
				  <td></td> 
				<tr>		
		*/
		$(temp).parent().show();
	});
});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">뮤직 Top50</h1>
			<table class="table">
				<tr>
					<td>
						<input type="text" size="30" class="input-sm" placeholder="검색어 입력" id="keyword">
					</td>
				</tr>
			</table>
			<table class="table table-hover" id="user-table">
				<tr class="success">
					<th>순위</th>
					<th></th>
					<th>곡명</th>
					<th>가수명</th>
				</tr>
				<tbody>
				<%
					while(rs.next())
					{
						
				%>
						<tr>
							<td><%=rs.getInt(1) %></td>
							<td><img src="<%=rs.getString(4)%>" width="35" height="35"></td>
							<td><%=rs.getString(2) %></td>
							<td><%=rs.getString(3) %></td>
						</tr>
				<%		
					}
				%>
				</tbody>
			</table>			
		</div>
	</div>
</body>
</html>
<%
	}catch(Exception ex){}
%>