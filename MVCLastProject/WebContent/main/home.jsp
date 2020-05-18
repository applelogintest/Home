<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
  <div id="slider" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="flexslider basicslider">
      <ul class="slides">
      	<c:forEach var="vo" items="${cList }">
        <li><img src="${vo.poster }" alt="" width=100%>
          <div class="txtoverlay">
            <div class="centralise">
              <div class="verticalwrap">
                <article>
                  <h2 class="heading uppercase">${vo.title }</h2>
                  <p><a class="btn orange pushright" href="#">자세히 보기</a></p>
                </article>
              </div>
            </div>
          </div>
        </li>
        </c:forEach>
      </ul>
    </div>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <div id="services" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="group">
      <div class="one_third first">
        <article class="service"><i class="icon red circle fa fa-bell-o"></i>
          <h2 class="heading" style="font-family: 맑은 고딕">한류관광</h2>
          <p class="btmspace-10">서울의 10대 한류명소<br>
                  한류스타 출연 공연 일정<br>
          K-pop & K-drama 추천코스
          </p>
          <p><a href="#">Read More &raquo;</a></p>
        </article>
      </div>
      <div class="one_third">
        <article class="service"><i class="icon orange circle fa fa-bicycle"></i>
          <h2 class="heading" style="font-family: 맑은 고딕">추천코스</h2>
          <p class="btmspace-10">서울 한 바퀴<br>
          2019 창경궁 야간 상시관람<br>
                   서울의 무인서비스
          </p>
          <p><a href="#">Read More &raquo;</a></p>
        </article>
      </div>
      <div class="one_third">
        <article class="service"><i class="icon green circle fa fa-mortar-board"></i>
          <h2 class="heading" style="font-family: 맑은 고딕">공연예약</h2>
          <p class="btmspace-10">
                   아라리오뮤지엄 인 스페이스 서울<br>
                   모던 앤 클래식 3<br>
                   난타(NANTA) - 명동공연
          </p>
          <p><a href="#">Read More &raquo;</a></p>
        </article>
      </div>
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row6">
  <section id="cta" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="three_quarter first">
      <h2 class="heading" style="font-family: 맑은 고딕">SIST Recipe</h2>
      <p>${count }개의 맛있는 레시피가 있습니다.</p>
    </div>
    <div class="one_quarter"><a class="btn" href="#">Get it now <span class="fa fa-arrow-right"></span></a></div>
    <!-- ################################################################################################ -->
  </section>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <div class="latest"> 
    <!-- ################################################################################################ -->
    <ul class="nospace group">
      <c:forEach var="vo" items="${rList }">
      <li>
        <figure><a class="overlay" href="#"><img src="${vo.poster }" alt=""></a>
          <figcaption class="inspace-30 center">
            <p class="bold uppercase">${vo.title }</p>
            <p style="color:FC6">${vo.chef }</p>
            <p><a href="#">View Here &raquo;</a></p>
          </figcaption>
        </figure>
      </li>
      </c:forEach>
    </ul>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="container nospace clear"> 
    <!-- main body -->
    <!-- ################################################################################################ -->
    <div class="group">
      <div class="one_half first paditout">
        <h2 class="heading uppercase btmspace-50">${fvo.title }</h2>
        <ul class="nospace group">
          <li class="btmspace-30">
            <article class="service largeicon"><i class="icon nobg circle fa fa-ra"></i>
              <h6 class="heading"><a href="#">주소</a></h6>
              <p>${fvo.address }&nbsp; ${fvo.tel }</p>
            </article>
          </li>
          <li class="btmspace-30">
            <article class="service largeicon"><i class="icon nobg circle fa fa-female"></i>
              <h6 class="heading"><a href="#">음식종류</a></h6>
              <p>
                 ${fvo.type }
              </p>
            </article>
          </li>
          <li>
            <article class="service largeicon"><i class="icon nobg circle fa fa-history"></i>
              <h6 class="heading"><a href="#">가격대</a></h6>
              <p>${fvo.price }</p>
            </article>
          </li>
        </ul>
      </div>
      <div class="one_half"><a href="#"><img src="${fvo.image }" alt=""></a></div>
    </div>
    <!-- ################################################################################################ -->
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
</body>
</html>