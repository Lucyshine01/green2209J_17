'use strict';
let fixSW = 0;
// 요소나온후 미리 읽어와야함
window.onload = function() {
  let loadingBar = document.getElementById("loading_Bar");
  if(isNaN(loadingBar))fixSW = 1;
  else fixSW = 0;
  $(window).scroll(function(){
  let space = document.getElementById("headMain").clientHeight + "px";
  if(fixSW == 1){
    if($(this).scrollTop() > 60) {
      $("#headMain").addClass("fixTop");
      $("#headMainSpace").css("height",space);
    }
    else {
      $("#headMain").removeClass("fixTop");
      $("#headMainSpace").css("height","0px");
    }
  }
  else {
    $("#headerTop").addClass("fixTop");
    $("#headMainSpace").css("height",space);
  }
});
};

$(document).ready(function(){
  $("#headerTop").hide();
  $("#loading_Bar").css("width","0px");
  $("#loading_Bar").animate({width:"2000px"},700);
  $("#loading_Bar").slideUp();
  $("#headerTop").delay(700);
  $("#headerTop").slideDown(700);
});