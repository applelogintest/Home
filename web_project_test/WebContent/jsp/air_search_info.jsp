<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="./air_search_info.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
var i=0;
$(function(){
	$('.container').click(function(){
		if(i==0)
		{
			$('.air_detail_info').show();
			i=1;
		}
		else
		{
			$('.air_detail_info').hide();	
			i=0;
		}
	});
	
});
</script>
</head>
<body>
	<div class="container">
		<div class="air_info">
			<div class="air_img">
				<img class="airport_img" src="https://content.r9cdn.net/rimg/provider-logos/airlines/v/7C.png?crop=false&width=108&height=92&fallback=default3.png&_v=44be2646eb1ebdc2abd56608ce20111955a22a02"/>
			</div>
			<div class="air_time_port">
				<div class="top"><b>17:05 - 19:30</b></div>
				<div class="bottom"><span>제주항공</span></div>
			</div>
			<div class="air_total_time">
				<div class="top"><b>2시간 40분</b></div>
				<div class="bottom"><span>인천 - 제주</span></div>
			</div>
			<div class="air_img">
				<img class="airport_img" src="https://content.r9cdn.net/rimg/provider-logos/airlines/v/7C.png?crop=false&width=108&height=92&fallback=default3.png&_v=44be2646eb1ebdc2abd56608ce20111955a22a02"/>
			</div>
			<div class="air_time_port">
				<div class="top"><b>17:05 - 19:30</b></div>
				<div class="bottom"><span>제주항공</span></div>
			</div>
			<div class="air_total_time">
				<div class="top"><b>2시간 40분</b></div>
				<div class="bottom"><span>인천 - 제주</span></div>
			</div>
			
		</div>
		<div class="air_reservation">
			<div class="air_price">
				<b>73,500원</b>
			</div>
			<div class="air_reservation_btn">
				<a class="btn btn-sm btn-success" href="#"><span style="font-size: 15px;">예매 하기</span></a>
			</div>
		</div>
		<div class="air_detail_info" >
			<div class="detail_font"><b>가는 여정</b> 인천 - 제주</div>
				<div class="detail_info">
					<div class="detail_date">
						<b>4월 28일</b>
					</div>
					<div class="detail_img">
						<img width="30px" height="30px" src="https://content.r9cdn.net/rimg/provider-logos/airlines/v/7C.png?crop=false&width=108&height=92&fallback=default3.png&_v=44be2646eb1ebdc2abd56608ce20111955a22a02">
					</div>
					<div class="detail_time_port">
						<div><b>17:05 - 19:30</b></div>
						<div style="color:#647582"><span>인천 - 제주</span></div>
					</div>
					<div class="detail_seat_totalTime">
						<div><span style="color:#647582">일반석</span></div>
						<div><b>2시간 25분</b></div>
					</div>
				</div>
			<div class="detail_font"><b>오는 여정</b> 제주 - 김포</div>
			<div class="detail_info">
					<div class="detail_date">
						<b>4월 28일</b>
					</div>
					<div class="detail_img">
						<img width="30px" height="30px" src="https://content.r9cdn.net/rimg/provider-logos/airlines/v/7C.png?crop=false&width=108&height=92&fallback=default3.png&_v=44be2646eb1ebdc2abd56608ce20111955a22a02">
					</div>
					<div class="detail_time_port">
						<div><b>17:05 - 19:30</b></div>
						<div style="color:#647582"><span>인천 - 제주</span></div>
					</div>
					<div class="detail_seat_totalTime">
						<div><span style="color:#647582">일반석</span></div>
						<div><b>2시간 25분</b></div>
					</div>
				</div>
		</div>
	</div>
	
</body>
</html>
