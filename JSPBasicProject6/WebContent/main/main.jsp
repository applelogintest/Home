<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>영화예매사이트</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="../css/style.css">
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">SISTMovieCenter</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="#">Home</a></li>
	      <li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">회원정보
		        <span class="caret"></span></a>
		        <ul class="dropdown-menu">
		          <li><a href="#">회원수정</a></li>
		          <li><a href="#">아이디찾기</a></li>
		          <li><a href="#">비밀번호찾기</a></li>
		          <li><a href="#">회원탈퇴</a></li>
		        </ul>
	    	</li>
	    	
	     	<li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">영화센터
		        <span class="caret"></span></a>
		        <ul class="dropdown-menu">
		          <li><a href="#">영화메인</a></li>
		          <li><a href="#">현재상영/개봉예정</a></li>
		          <li><a href="#">박스오피스</a></li>
		          <li><a href="#">영화예매</a></li>
		          <li><a href="#">영화뉴스</a></li>
		        </ul>
	    	</li>
	    	
	    	<li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">영화추천
		        <span class="caret"></span></a>
		        <ul class="dropdown-menu">
		          <li><a href="#">영화추천</a></li>
		          <li><a href="#">근처맛집</a></li>
		        </ul>
	    	</li>
	    	
	     	<li class="dropdown">
		        <a class="dropdown-toggle" data-toggle="dropdown" href="#">커뮤니티
		        <span class="caret"></span></a>
		        <ul class="dropdown-menu">
		          <li><a href="#">묻고답하기</a></li>
		          <li><a href="#">자유게시판</a></li>
		          <li><a href="#">자료실</a></li>
		        </ul>
	    	</li>
	    	<li><a href="#contact">마이페이지</a></li>
	    	
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li><a href="#"><span class="glyphicon glyphicon-user"></span>회원가입</a></li>
	      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span>로그인</a></li>
	    </ul>
	  </div>
	</nav>
	
	
	<jsp:include page="../movie/movie_home.jsp"></jsp:include>
	
	<footer class="container-fluid bg-4 text-center">
	  <p>강북 쌍용교육센터 3층 G강의실</p> 
	</footer>
	
<script>
$(document).ready(function(){
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
    // Make sure this.hash has a value before overriding default behavior
    if (this.hash !== "") {
      // Prevent default anchor click behavior
      event.preventDefault();

      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 900, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    } // End if
  });
  
  $(window).scroll(function() {
    $(".slideanim").each(function(){
      var pos = $(this).offset().top;

      var winTop = $(window).scrollTop();
        if (pos < winTop + 600) {
          $(this).addClass("slide");
        }
    });
  });
})
</script>
	
</body>
</html>