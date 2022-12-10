'use strict';
$(document).ready(function(){
	// carousel
	$("#slideImg").on('slide.bs.carousel', function(e){
	  let val = e.to;
	  $("#numIns").html("0"+(val+1));
	});
	$('#slideImg').carousel({
	  // 슬리아딩 자동 순환 지연 시간
	  // false면 자동 순환하지 않는다.
	  interval: 3000,
	  // hover를 설정하면 마우스를 가져대면 자동 순환이 멈춘다.
	  pause: "hover",
	  // 순환 설정, true면 1 -> 2가면 다시 1로 돌아가서 반복
	  wrap: true,
	});
});