<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>CPInfo.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <link href="include/viewPage.css" rel="stylesheet" type="text/css">
  <script>
	  'use strict';
	  function inputFile(me) {
	    $(me).next(".filebox").click();
	  }
	  function onChangefile(me) {
	    let val = $(me).val();
	    val = val.substring(val.lastIndexOf('\\')+1);
	    let exp = val.substring(val.lastIndexOf('.')+1).toUpperCase();
	    if(exp != 'PNG' && exp != "JPG") {
	      alert("PNG,JPG파일만 허용합니다!");
	      return;
	    }
	    $(me).prev(".fIcon").hide();
	    $("#fileBoxs").append('<div class="fileName mr-3 mt-2">'+val+'</div>');
	    let next = '<i class="fIcon fa-solid fa-plus mt-2" onclick="inputFile(this)"></i></div><input type="file" name="files" id="files" class="filebox" onchange="onChangefile(this)">'
	    $("#fileBoxs").append(next);
	  }
	  function inputImg() {
			let ans = confirm("이미지를 추가하시겠습니까?");
			if(!ans) return;
			
			plusImgForm.submit();
		}
  </script>
  <style>
  	.tit {
  		width: 110px;
  		font-family: 'Spoqa Han Sans Neo', 'sans-serif';
  		border-right: 2px solid #e0e0e0;
  		padding: 10px;
  		font-size: 1.1em;
  		font-weight: 400;
  		color: #333;
  	}
  	.cont {
  		width: 75%;
  		font-family: 'Spoqa Han Sans Neo', 'sans-serif';
  		margin-left: 30px;
  		padding: 10px;
  		font-size: 1.1em;
  		font-weight: 400;
  		color: #333;
  	}
  </style>
</head>
<body ondragstart="return false" onselectstart="return false">
	<div id="loading_Bar"></div>
	<c:if test="${empty sMid}"><jsp:include page="/include/headTop.jsp"></jsp:include></c:if>
	<jsp:include page="/include/header.jsp"></jsp:include>
	<div class="width" style="margin-top: 30px">
		<div class="d-flex"><div class="ml-3 mb-3 titleContent">회사 정보</div></div>
		<div class="d-flex">
			<div class="ml-2 mb-4 subtitleContent">
				고객님께서 등록하신 인테모아 업체 정보입니다.<br/>
				변경된 정보 혹은 잘못된 정보가 있으실 경우 문의를 통해 정보를 변경하실 수 있습니다.<p></p>
			</div>
		</div>
		<div class="d-flex ml-3 mb-3">
			<img src="${ctp}/data/logo/${vo.cpImg}" width="150px"/>
			<div class="m-2 d-flex fCol_center cont" style="font-size: 1.3em; font-weight: 500;">${vo.cpName}</div>
		</div>
		<div style="border-bottom: 2px solid #d0d0d0;"></div>
		<div class="mt-2 ml-2 mb-1" style="font-size: 1.8em; font-weight: 400; color: #333">
			업체 정보
		</div>
		<div class="mt-3 d-flex">
			<div class="d-flex fCol_center ml-4" style="width: 48%">
				<div class="d-flex fCol_center">
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">회사명</div>
						<div class="cont d-flex fCol_center">${vo.cpName}</div>
					</div>
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">대표명</div>
						<div class="cont d-flex fCol_center">${vo.name}</div>
					</div>
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">주소</div>
						<div class="cont d-flex fCol_center">${vo.cpAddr}</div>
					</div>
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">홈페이지</div>
						<div class="cont d-flex fCol_center">${vo.cpHomePage == '' ? '없음' : vo.cpHomePage}</div>
					</div>
				</div>
			</div>
			<div class="d-flex fCol_center ml-4" style="width: 48%">
				<div class="d-flex fCol_center">
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">연락처</div>
						<div class="cont d-flex fCol_center">${vo.tel}</div>
					</div>
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">이메일</div>
						<div class="cont d-flex fCol_center">${vo.email}</div>
					</div>
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">등록일</div>
						<div class="cont d-flex fCol_center">${fn:substring(vo.createDayCP,0,10)}</div>
					</div>
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">활동상태</div>
						<div class="cont d-flex fCol_center">${vo.act == 'off' ? '중단' : '활동중'}</div>
					</div>
				</div>
			</div>
		</div>
		<div class="mt-3 mb-5">
			<div class="d-flex fCol_center ml-4">
					<div class="d-flex mb-4">
						<div class="tit d-flex fCol_center">전문분야</div>
						<div class="cont d-flex fCol_center">${vo.cpExp == '' ? '없음' : fn:replace(vo.cpExp,'/',' | ')}</div>
					</div>
					<div class="d-flex mb-3">
						<div class="tit d-flex fCol_center">소개</div>
						<div class="cont d-flex fCol_center">${vo.cpIntro == '' ? '없음<br/>&nbsp;' : vo.cpIntro}</div>
					</div>
			</div>
		</div>
		<div class="mt-3" style="border-bottom: 2px solid #d0d0d0;"></div>
		<div class="d-flex" style="padding: 30px 50px 0px 50px;">
			<div>
				<c:if test="${empty vo.cpIntroImg}">
					<div class="d-felx fCol_center text-center" style="width: 600px; line-height: 400px; font-size: 1.8em;">
						NO IMAGE
					</div>
				</c:if>
				<c:if test="${!empty vo.cpIntroImg}">
					<c:set var="imgs" value="${fn:split(vo.cpIntroImg,'/')}" />
					<div id="demo" class="carousel slide ml-auto mr-auto" data-ride="carousel" style="width: 600px">
						  <!-- Indicators -->
						  <ul class="carousel-indicators">
						  	<c:forEach var="img" items="${imgs}" varStatus="st">
						    	<li data-target="#demo" data-slide-to="${st.index}"></li>
						    </c:forEach>
						  </ul>
						  <!-- The slideshow -->
						  <div class="carousel-inner">
						  	<c:forEach var="img" items="${imgs}" varStatus="st">
									<div class="carousel-item <c:if test="${st.index == 0}">active</c:if>">
										<img src="${ctp}/data/picture/${img}" alt="${st.count}.img"/> 
									</div>
								</c:forEach>
						  </div>
						  <!-- Left and right controls -->
						  <a class="carousel-control-prev" href="#demo" data-slide="prev">
						    <span class="carousel-control-prev-icon"></span>
						  </a>
						  <a class="carousel-control-next" href="#demo" data-slide="next">
						    <span class="carousel-control-next-icon"></span>
						  </a>
					</div>
				</c:if>
			</div>
			<div class="mt-3 mb-5 ml-auto text-center" style="width: 40%">
				<form name="plusImgForm" method="post" action="${ctp}/inputCPImg.co" enctype="multipart/form-data">
					<div>
				    <div id="fileBoxs" class="d-flex" style="flex-wrap: wrap">
				      <i class="fIcon fa-solid fa-plus mt-2" onclick="inputFile(this)"></i>
				      <input class="filebox" type="file" name="files" id="files" onchange="onChangefile(this)">
				    </div>
				  </div>
			  </form>
			</div>
		</div>
		<div class="d-flex">
			<c:if test="${!empty vo.cpIntroImg}">
				<select id="imgDel" name="imgDel" class="form-control ml-auto m-3" style="width: 10%">
					<c:forEach var="img" items="${imgs}" varStatus="st">
						<option value="${img}">${st.count}번 이미지</option>
					</c:forEach>
				</select>
				<input type="button" value="선택 이미지삭제" class="btn btn-danger m-3"/>
				<input type="button" value="이미지 추가" onclick="inputImg();" class="btn btn-success m-3"/>
			</c:if>
			<c:if test="${empty vo.cpIntroImg}">
				<input type="button" value="이미지 추가" onclick="inputImg();" class="btn btn-success ml-auto m-3"/>
			</c:if>
		</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<script src="include/viewPage.js"></script>
</body>
</html>