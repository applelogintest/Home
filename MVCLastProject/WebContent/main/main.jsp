<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>SIST Recipe & Food</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="../main/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<style type="text/css">
body{
  font-family: 맑은 고딕;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	/* $('#logBtn').click(function(){
		
	}) */
	$('#logBtn').on('click',function(){
		let id=$('#id').val();
		if(id.trim()=="")
		{
			$('#id').focus();
			return;
		}
		
		let pwd=$('#pwd').val();
		if(pwd.trim()=="")
		{
			$('#pwd').focus();
			return;
		}
		
		//$('#log_frm').submit();
		$.ajax({
			type:'POST',
			url:'../member/login.do',
			data:{"id":id,"pwd":pwd},
			success:function(res){
				if(res.trim()=='NOID')
				{
					alert("아이디가 존재하지 않습니다");
					$('#id').val("");
					$('#pwd').val("");
					$('#id').focus();
				}
				else if(res.trim()=='NOPWD')
				{
					alert("비밀번호가 틀립니다");
					$('#pwd').val("");
					$('#pwd').focus();
				}
				else
				{
					location.href="../main/main.do";
				}
			},
			error:function(e){
				alert(e);
			}
		})
			
	})
});
</script>
</head>
<body id="top">

<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">SIST Recipe & Food</a></h1>
    </div>
    <!-- ################################################################################################ -->
    <nav id="mainav" class="fl_right">
      <ul class="clear">
        <li class="active"><a href="../main/main.do">Home</a></li>
        <li><a class="drop" href="#">회원</a>
          <ul>
          	<c:if test="${sessionScope.id==null }">
          		<li><a href="../member/join.do">회원가입</a></li>
          	</c:if>
          	
          	<c:if test="${sessionScope.id!=null }">
          		<li><a href="../member/join.do">회원수정</a></li>
          	</c:if>
          	
          	<c:if test="${sessionScope.id==null }">
            	<li><a href="pages/full-width.html">아이디찾기</a></li>
            	<li><a href="pages/sidebar-left.html">비밀번호찾기</a></li>
           	</c:if>
           	
           	<c:if test="${sessionScope.id!=null }">
            	<li><a href="pages/sidebar-right.html">회원탈퇴</a></li>
            </c:if>
          </ul>
        </li>
        <li><a class="drop" href="#">레시피</a>
          <ul>
            <li><a href="../recipe/recipe.do">레시피</a></li>
            <li><a class="drop" href="#">쉐프</a>
              <ul>
                <li><a href="../recipe/chef.do">쉐프 목록</a></li>
                <li><a href="../recipe/recipe_find.do">레시피 찾기</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li><a class="drop" href="#">맛집</a>
          <ul>
            <li><a href="#">추천코스</a></li>
            <li><a href="#">명소</a></li>
            <li><a href="#">쇼핑</a></li>
            <li><a href="#">숙박</a></li>
            <li><a href="#">음식점</a></li>
          </ul>
        </li>
        <c:if test="${sessionScope.id!=null && sessionScope.admin=='n' }">
	        <li><a class="drop" href="#">예약하기</a>
	          <ul>
	            <li><a href="#">맛집예약</a></li>
	            <li><a href="#">호텔예약</a></li>
	          </ul>
	        </li>
        </c:if>
        <li><a class="drop" href="#">커뮤니티</a>
          <ul>
          	<c:if test="${sessionScope.id!=null }">
            	<li><a href="../freeboard/list.do">자유게시판</a></li>
            </c:if>
            <li><a href="../reply/list.do">묻고답하기</a></li>
            <li><a href="#">자료실</a></li>
          </ul>
        </li>
        <li><a href="#">공지사항</a></li>
        
        <c:if test="${sessionScope.id!=null && sessionScope.admin=='n' }">
        	<li><a href="#">마이페이지</a></li>
        </c:if>
        
		<c:if test="${sessionScope.id!=null && sessionScope.admin=='y' }">        
        	<li><a href="#">예약현황</a></li>
        </c:if>
      </ul>
    </nav>
    <!-- ################################################################################################ -->
    <div class="clear text-right" >
    	<c:if test="${sessionScope.id==null }">
    		<form method="post" action="../member/login.do" id="log_frm">
		    	<input type="text" size="10" class="input-sm" placeholder="ID" style="display: inline-block"
		    	id="id" name="id">
		    	&nbsp;
		    	<input type="password" size="10" class="input-sm" placeholder="Password" style="display: inline-block"
		    	id="pwd" name="pwd">
		    	<input type="button" class="btn btn-sm btn-primary" value="로그인"
		    	id="logBtn"><br>
	    	</form>
    	</c:if>
    	
    	<c:if test="${sessionScope.id!=null }">
    		<form method=post action="../member/logout.do">
	    		<span style="display:inline-block;"><font color="blue">${sessionScope.name }
	    		(${sessionScope.admin=='y'?"관리자":"일반사용자" })</font>님 로그인중입니다</span>
	    		<input type="submit" size="10" class="btn btn-sm btn-primary" placeholder="ID" value="로그아웃" style="display: inline-block">
    		</form>
    	</c:if>
    </div>
  </header>
</div>
<div style="height:30px"></div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<jsp:include page="${main_jsp }"></jsp:include>
<div class="wrapper row4">
  <footer id="footer" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="one_quarter first">
      <h6 class="title">공지사항</h6>
      <ul class="nospace linklist">
        <li><a href="#">좌석을 변경할 수 있나요?</a></li>
        <li><a href="#">예약은 어떻게 취소하나요?</a></li>
        <li><a href="#">예약을 일부만 취소할 수 있나요? </a></li>
        <li><a href="#">티켓은 어디서 받나요?</a></li>
        <li><a href="#">관광시간이 긴데 줄일 수 없나요?</a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="title">인기게시물</h6>
      <ul class="nospace linklist">
        <li><a href="#">좌석을 변경할 수 있나요?</a></li>
        <li><a href="#">예약은 어떻게 취소하나요?</a></li>
        <li><a href="#">예약을 일부만 취소할 수 있나요? </a></li>
        <li><a href="#">티켓은 어디서 받나요?</a></li>
        <li><a href="#">관광시간이 긴데 줄일 수 없나요?</a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="title">이벤트</h6>
      <ul class="nospace linklist">
        <li><a href="#">좌석을 변경할 수 있나요?</a></li>
        <li><a href="#">예약은 어떻게 취소하나요?</a></li>
        <li><a href="#">예약을 일부만 취소할 수 있나요? </a></li>
        <li><a href="#">티켓은 어디서 받나요?</a></li>
        <li><a href="#">관광시간이 긴데 줄일 수 없나요?</a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="title">인기검색어</h6>
      <ul class="nospace linklist">
        <li><a href="#">좌석을 변경할 수 있나요?</a></li>
        <li><a href="#">예약은 어떻게 취소하나요?</a></li>
        <li><a href="#">예약을 일부만 취소할 수 있나요? </a></li>
        <li><a href="#">티켓은 어디서 받나요?</a></li>
        <li><a href="#">관광시간이 긴데 줄일 수 없나요?</a></li>
      </ul>
    </div>
    <!-- ################################################################################################ -->
  </footer>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row5">
  <div id="copyright" class="clear"> 
    <!-- ################################################################################################ -->
    <p class="fl_left"><a href="#">SIST 쌍용 강북 교육센터</a></p>
    <p class="fl_right"><a target="_blank" href="#" title="Free Website Templates">G강의장</a></p>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<!-- JAVASCRIPTS -->
<script src="../main/layout/scripts/jquery.min.js"></script>
<script src="../main/layout/scripts/jquery.backtotop.js"></script>
<script src="../main/layout/scripts/jquery.mobilemenu.js"></script>
<script src="../main/layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>