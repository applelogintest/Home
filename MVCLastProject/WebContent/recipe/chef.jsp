<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.table,td{
	background: white;
}
</style>
</head>
<body>
	<div class="wrapper row2">
	  <div id="services" class="clear"> 
	    <!-- ################################################################################################ -->
	    <div class="row">
	    	<table class="table">
    			<tr>
    				<td>
    				  <c:forEach var="vo" items="${list }">
    				 	 <table class="table" >
						    <tr>
						    	<td width="30%" class="text-center" rowspan="2">
						    		<a href="../recipe/chef_detail.do?name=${vo.chef }"><img src="${vo.poster }" class="img-circle" style="width: 100px; height: 100px;"></a> 
						    	</td>
						    	<td colspan="4"><h3><font color="#FC8">${vo.chef }</font></h3></td>
						    </tr>
						    <tr>
						    	<td class="text-center">
						    		<img src="image/1.png"> ${vo.mem_cont1 }
						    	</td>
						    	<td class="text-center"> 
						    		<img src="image/3.png">  ${vo.mem_cont3 }
						    	</td>
						    	<td class="text-center"> 
						    		<img src="image/7.png"> ${vo.mem_cont7 }
						    	</td>
						    	<td class="text-center"> 
						    		<img src="image/2.png">  ${vo.mem_cont2 }
						    	</td>
						    </tr>
					    </table>
				     </c:forEach>
    				</td>
    			</tr>
	    	</table>
	    </div>
	  </div>
	    <!-- ################################################################################################ -->
	    <div class="clear"></div>
	    <div class="text-center">
	    	<ul class="pagination">
	    		<c:if test="${startPage>1 }">
	    			<li><a href="../recipe/chef.do?page=${startPage-1 }">&lt;</a></li>
	    		</c:if>
	    		<c:set var="type" value=""/>
	    		<c:forEach var="i" begin="${startPage }" end="${endPage }">
	    			<c:if test="${curpage==i }">
	    				<c:set var="type" value="class=active"/>
	    			</c:if>
	    			<c:if test="${curpage!=i }">
	    				<c:set var="type" value=""/>
	    			</c:if>
	    			<li ${type }><a href="../recipe/chef.do?page=${i }">${i }</a></li>
	    		</c:forEach>
	    		<c:if test="${endPage<allPage }">
			    	<li><a href="../recipe/chef.do?page=${endPage+1 }">&gt;</a></li>
			    </c:if>
			  </ul>
	    </div>
	</div>
</body>
</html>