<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	JSP (list.do) ======> DispatcherServlet =======> Model
													 @RequestMapping("list.do")
									 				 public String list(req,res) <==> DAO
							request  <==========	 request.addAttribute() 요청 처리 내용 추가
								     forward를 이용해서 request를 사용자에 전송
								      
 --%>
<c:choose>
	<c:when test="${vo.msg=='NOID' }">
		<script>
			alert("ID가 존재하지 않습니다");
			history.back();
		</script>
	</c:when>
	<c:when test="${vo.msg=='NOPWD' }">
			alert("비밀번호가 틀렸습니다");
			history.back();
	</c:when>
	<c:otherwise>
		<c:if test="${vo.admin=='y' }">
			<c:redirect url="admin.do"/>
		</c:if>
		<c:if test="${vo.admin!='y' }">
			<c:redirect url="reserve.do"/>
		</c:if>
	</c:otherwise>
</c:choose>