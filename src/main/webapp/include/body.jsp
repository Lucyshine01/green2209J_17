<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="mainView">
  <div class="width d-flex">
    <div id="mainView_1">
      <div id="mainViewTitle" >
        인테리어 전문 인테모아에서<br/>
        원하는 업체를 찾아보세요!
      </div>
      <div id="mainView_searchBox">
        <input type="text" id="search2" name="saerch" placeholder="인테리어 업체 찾기" autocomplete='off' spellcheck="false">
        <i class="fa-solid fa-magnifying-glass"></i>
      </div>
      <div class="d-flex">
        <span class="hasTag">#인테리어</span>
        <span class="hasTag">#유명업체</span>
        <span class="hasTag">#프리랜서</span>
        <span class="hasTag">#후기</span>
      </div>
    </div>
    <div id="slideImg" class="carousel slide ml-auto" data-ride="carousel">
      <!-- (하단 바 스타일) -->
      <div id="slideNumbar"><span id="numIns"></span><span id="numTot"></span></div>
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
          <div class="carousel-item">
            <a href="#"><img src="images/viewPage/test4.png"/></a>
          </div>
          <div class="carousel-item">
            <a href="#"><img src="images/viewPage/test5.png"/></a>
          </div>
          <div class="carousel-item">
            <a href="#"><img src="images/viewPage/test6.png"/></a>
          </div>
        </div>
      <!-- Left and right controls -->
      <a class="carousel-control-prev carousel-btn" href="#slideImg" data-slide="prev" style="left: -18px;" >
        <i style="color: #000;" class="fa-solid fa-chevron-left"></i>
      </a>
      <a class="carousel-control-next carousel-btn" href="#slideImg" data-slide="next" style="right: -18px;" >
        <i style="color: #000;" class="fa-solid fa-chevron-right"></i>
      </a>
    </div>
  </div>
</div>