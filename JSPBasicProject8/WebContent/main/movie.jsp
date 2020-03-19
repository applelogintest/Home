<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.MovieDAO"/>
<%
	String strPage=request.getParameter("page");
	// request => main이 가지고 있는 request
	if(strPage==null)
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	List<MovieBean> list=dao.movieListData(curpage);
	int totalpage=dao.movieTotalPage();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	text-align:center;
	margin: 0px auto;
	width: 960px;
}
</style>
</head>
<body>
	<h1>영화</h1>
	 <div class="row">
	 <%
	 	for(MovieBean mb:list)
	 	{
	 %>
		 	<div class="col-md-4">
		    <div class="thumbnail">
		      <a href="/w3images/lights.jpg">
		        <img src="<%=mb.getPoster() %>" style="width:100%">
		        <div class="caption">
		          <p><%=mb.getTitle() %></p>
		        </div>
		      </a>
		    </div>
		  </div>
	 <%
	 	}
	 %>
	
	 </div>
	 <div class="row row1">
	 	<a href="main.jsp?mode=1&page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-primary">이전</a>
	 	<%=curpage %> page / <%=totalpage %> pages
	 	<a href="main.jsp?mode=1&page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-primary">다음</a>
	 </div>
	
</body>
</html>