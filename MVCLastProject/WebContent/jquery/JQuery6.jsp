<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  
  $( function() {
    $.getJSON('weekly.json',function(data){
    		alert(data)
    	$.each(data["datas"],function(key,value){
    		$('#list').append(
    			'<div class="col-md-3">'
    		    +'<div class="thumbnail">'
    		    +'<img src="'+value.poster+'" alt="Lights" style="width:100%" onclick=detail('+value.no+')>'
    		    +'<div class="caption">'
    		    +'<p>'+value.title+'</p>'
    		    +'</div>'
    		    +'</a>'
    			+'</div>'
    			+'</div>'
    		)
    	});
     });
  });
  
 
  function detail(no){
  	$.getJSON('weekly.json',function(data){
  	$.each(data["datas"],function(key,value){
  		if(no==value.no)
  		{
  			$('#img').attr("src",value.poster);
  			$('#title').text(value.title);
  			$('#director').text(value.director);
  			$('#actor').text(value.actor);
  			$('#genre').text(value.genre);
  			$('#grade').text(value.grade);
  			$('#score').text(value.score);
  			$('#regdate').text(value.regdate);
  			$('#story').text(value.story);
  			return false;	
  		}
  		
  	});
   });
  	
  	$('#dialog').dialog({
  		width:600,
  		height:500
  	})
  }
  </script>
</head>
<!-- 
	  <div class="col-md-4">
    <div class="thumbnail">
      <a href="/w3images/lights.jpg">
        <img src="/w3images/lights.jpg" alt="Lights" style="width:100%">
        <div class="caption">
          <p>Lorem ipsum...</p>
        </div>
      </a>
    </div>
  </div>
-->
<body>
	<div class="container">
		<h1 class="text-center">영화목록</h1>
		<div class="row" id="list">
			
		</div>
		<div class="row">
			<div id="dialog" title="영화 상세보기" style="display: none">
				<table class="table">
					<tr>
						<td width="30%" class="text-center" rowspan="7">
							<img src="" width="100%" id="img">
						</td>
						<td colspan="2" id="title"></td>
					</tr>
					<tr>
						<td width="10%" class="text-right">감독</td>
						<td width="60%" id="director"></td>
					</tr>
					<tr>
						<td width="10%" class="text-right">출연</td>
						<td width="60%" id="actor"></td>
					</tr>
					
					<tr>
						<td width="10%" class="text-right">장르</td>
						<td width="60%" id="genre"></td>
					</tr>
					
					<tr>
						<td width="10%" class="text-right">등급</td>
						<td width="60%" id="grade"></td>
					</tr>
					
					<tr>
						<td width="10%" class="text-right">평점</td>
						<td width="60%" id="score"></td>
					</tr>
					
					<tr>
						<td width="10%" class="text-right">상영일</td>
						<td width="60%" id="regdate"></td>
					</tr>
					
					<tr>
						<td colspan="3" class="text-left" id="story"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>