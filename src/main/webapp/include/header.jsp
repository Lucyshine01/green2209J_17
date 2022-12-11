<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="loading_Bar"></div>
  <a href="#">
    <div id="headerTop">
      <div class="width d-flex">
        <div id="hTL" class="d-flex fCol_center text-dark">
          <span>인테리어가 필요할땐, 인테모아</span>
        </div>
        <div id="hTR" class="d-flex fCol_center text-dark ml-auto">
          <span>신규가입하고 <b>1만원 가량의 포인트</b>를 받으세요! &nbsp;></span>
        </div>
      </div>
    </div>
  </a>
  <div id="headMain" class="container-fluid-xl" style="z-index: 9;background-color:#fff;" >
    <div class="width d-flex">
      <div id="logo" class="d-flex fCol_center header pl-4">
        <a href="#"><img src="images/viewPage/logo3.png" width="120px"/></a>
      </div>
      <div id="searchBox" class="ml-auto d-flex text-center fCol_center header">
        <input type="text" id="search" name="search" placeholder="서비스, 업체를 검색해 보세요" autocomplete='off' spellcheck="false">
        <i class="fa-solid fa-magnifying-glass"></i>
        <i class="fa-solid fa-circle-xmark iconHidden" onclick="removeSearch()"></i>
      </div>
      <div id="loginBox" class="d-flex fCol_center header">
        <div>
          <button type="button" class="btn w3-hover-light-grey text-dark ml-3 pl-4 pr-4">
            업체등록
          </button>
          <button type="button" class="btn w3-hover-light-grey text-dark ml-3 pl-4 pr-4">
            로그인
          </button>
          <button type="button" class="btn w3-2017-primrose-yellow w3-hover-amber btn-warning text-dark ml-3 pl-4 pr-4">
            회원 가입
          </button>
        </div>
      </div>
    </div>
    <div class="d-flex width">
      <nav class="navbar">
        <div class="navbar-nav navIconBox">
          <div class="nav-item dropdown">
            <span class="nav-link dropdown-toggle nav-icon" id="navbardrop" data-toggle="dropdown">
              <i class="fa-solid fa-bars"></i>&nbsp;&nbsp;<b>전체카테고리</b>
            </span>
            <div class="dropdown-menu" style="position: absolute;">
              <span class="dropdown-item none-item" href="#">인테리어</span>
              <a class="dropdown-item" href="#">집 인테리어</a>
              <a class="dropdown-item" href="#">상업공간 인테리어</a>
              <a class="dropdown-item" href="#">조명 인테리어</a>
              <a class="dropdown-item" href="#">욕실/화장실 리모델링</a>
              <span class="dropdown-item none-item" href="#">시공</span>
              <a class="dropdown-item" href="#">타일시공</a>
              <a class="dropdown-item" href="#">페인트시공</a>
              <a class="dropdown-item" href="#">싱크대 교체</a>
              <a class="dropdown-item" href="#">도배장판 시공</a>
              <a class="dropdown-item" href="#">인테리어 필름 시공</a>
              <span class="dropdown-item none-item" href="#">디자인</span>
              <a class="dropdown-item" href="#">도면 제작·수정</a>
              <a class="dropdown-item" href="#">인테리어 컨설팅</a>
              <a class="dropdown-item" href="#">3D 모델링</a>
            </div>
          </div>
        </div>
        <div class="navIconBox">
          <a href="#" class="nav-link nav-icon d-flex fCol_center">의뢰 목록</a>
          <span id="help1" class="help"><b>다양한 분야의 전문가</b>에게 의뢰해보세요.</span>
        </div>
        <div class="navIconBox">
          <a href="#" class="nav-link nav-icon d-flex fCol_center">업체 목록</a>
          <span id="help2" class="help"><b>인테모아의 다양한 업체</b>를 만나보세요.</span>
        </div>
        <div class="navIconBox">
          <a href="#" class="nav-link nav-icon d-flex fCol_center">고객센터</a>
          <span id="help3" class="help">관리자와 <b>1대1 문의/답변</b>이 가능합니다.</span>
        </div>
        <div class="navIconBox">
          <a href="#" class="nav-link nav-icon d-flex fCol_center">게시판</a>
          <span id="help4" class="help">업체 및 인테모아의 <b>사용후기를 작성</b>해보세요.</span>
        </div>
        <div style="width: 200px;"></div>
      </nav>
    </div>
  </div>

  <!-- fix시 공백채우기 -->
  <div id="headMainSpace"></div>
  <!-- fix시 공백채우기 -->

  


