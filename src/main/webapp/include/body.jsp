<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="width">
  <div id="slideImg" class="carousel slide" data-ride="carousel">
    <!-- (하단 바 스타일) -->
    <div id="slideNumbar"><span id="numIns">01</span><span> / 03</span></div>

    <!-- The slideshow -->
    <div class="carousel-inner">
      <div class="carousel-item active">
        <a href="#"><img src="images/viewPage/test1.webp"/></a>
      </div>
      <div class="carousel-item">
        <a href="#"><img src="images/viewPage/test2.webp"/></a>
      </div>
      <div class="carousel-item">
        <a href="#"><img src="images/viewPage/test3.webp"/></a>
      </div>
    </div>
    <!-- Left and right controls -->
    <a class="carousel-control-prev carousel-btn" href="#slideImg" data-slide="prev" style="left: -15px;" >
      <i class="fa-solid fa-chevron-left"></i>
    </a>
    <a class="carousel-control-next carousel-btn" href="#slideImg" data-slide="next" style="right: -15px;" >
      <i class="fa-solid fa-chevron-right"></i>
    </a>
  </div>
</div>