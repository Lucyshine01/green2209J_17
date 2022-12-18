<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>CPList.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <link href="include/viewPage.css" rel="stylesheet" type="text/css">
  <script>
	  'use strict';
		let pag = ${pag};
		let totPage = ${totPage};
		let pageSize = ${pageSize};
		if(pag > totPage) {
			location.href = '${ctp}/CPList.ad?pag='+totPage+'&pageSize='+pageSize+"&categori=${categori}&detail=${detail}";
		}
  </script>
  <style></style>
</head>
<body ondragstart="return false" onselectstart="return false">
	<div id="loading_Bar"></div>
	<c:if test="${empty sMid}"><jsp:include page="/include/headTop.jsp"></jsp:include></c:if>
	<jsp:include page="/include/header.jsp"></jsp:include>
	<div class="width">
		<div class="row">
			<div class="col-3">
				<div class="side-Menu">
					<div class="mb-3 ml-2" style="font-size: 1.7em; font-weight: 500;">
						<c:if test="${empty categori}">카테고리</c:if>
						<c:if test="${!empty categori}">${categori}</c:if>
					</div>
					<div style="border-bottom: 2px solid #fc3; margin-bottom: 20px"></div>
	        <div class="first-class">인테리어</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=인테리어&detail=1'">홈 인테리어</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=인테리어&detail=2'">상업공간 인테리어</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=인테리어&detail=3'">조명 인테리어</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=인테리어&detail=4'">욕실/화장실 리모델링</div>
	        <div class="first-class">시공</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=시공&detail=5'">타일시공</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=시공&detail=6'">페인트시공</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=시공&detail=7'">싱크대 교체</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=시공&detail=8'">도배장판 시공</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=시공&detail=9'">인테리어 필름 시공</div>
	        <div class="first-class">디자인</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=디자인&detail=10'">도면 제작·수정</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=디자인&detail=11'">인테리어 컨설팅</div>
	        <div class="second-class" onclick="location.href='${ctp}/CPList.co?categori=디자인&detail=12'">3D 모델링</div>
	       </div>
			</div>
			<div class="col-9">
				<div class="main-content">
					<div class="topCategori">
						<c:if test="${empty categori}">
							<a href="${ctp}/CPList.co">전체목록</a>
						</c:if>
						<c:if test="${!empty categori}">
							<a href="${ctp}/CPList.co">업체목록</a>&nbsp;>&nbsp;
							<a href="${ctp}/CPList.co?categori=${categori}">${categori}</a>
							<c:if test="${subCategori != 'no'}">
								&nbsp;>&nbsp;<a href="${ctp}/CPList.co?categori=${categori}&detail=${detail}">${subCategori}</a>
							</c:if>
						</c:if>
					</div>
					<div class="topTitle">인테모아 업체목록</div>
					<div style="border-bottom: 2px solid #d0d0d0;"></div>
				</div>
				<div class="d-flex" style="flex-wrap: wrap">
					<c:forEach var="vo" items="${vos}">
						<div onclick="location.href='${ctp}/CPContentView.co?mid=${vo.mid}'" class="d-flex" style="width: 33%; height: 300px; flex-direction: column;">
							<div class="d-flex justify-content-center cpItemBox" style="height: 200px">
								<img src="${ctp}/data/logo/${vo.cpImg}" width="80%" height="auto" style="object-fit: contain">
							</div>
							<div class="text-center">
								<div class="ml-auto mr-auto cpItemText" style="width: 80%; font-size: 1.05em;">${vo.cpName}</div>
								<div class="ml-auto mr-auto cpItemText" style="width: 90%; color: #bbb; font-size:0.9em; font-weight: 300;">
									${fn:substring(fn:replace(vo.cpIntro,'<br/>',''),0,40)}
									<c:if test="${fn:length(fn:replace(vo.cpIntro,'<br/>','')) > 40}">...</c:if>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="text-center">
				  <ul class="pagination justify-content-center">
				  	<c:if test="${empty searching}"><c:set var="CPList" value="${ctp}/CPList.co?pageSize=${pageSize}&categori=${categori}&detail=${detail}"/> </c:if>
				  	<c:if test="${!empty searching}"><c:set var="CPList" value="${ctp}/CPSearch.co?pageSize=${pageSize}&categori=${categori}&detail=${detail}&searching=${searching}&searchItem=${searchItem}"/></c:if>
				    <c:if test="${pag > 1}">
				      <li class="page-item"><a class="page-link text-secondary" href="${CPList}&pag=1">
				      	<i class="fa-solid fa-backward-fast"></i>
				      </a></li>
				    </c:if>
				    <c:if test="${curBlock > 0}">
				      <li class="page-item"><a class="page-link text-secondary" href="${CPList}&pag=${(curBlock-1)*blockSize + 1}">
				      	<i class="fa-solid fa-caret-left"></i>
				      </a></li>
				    </c:if>
				    <c:forEach var="i" begin="${(curBlock)*blockSize + 1}" end="${(curBlock)*blockSize + blockSize}" varStatus="st">
				      <c:if test="${i <= totPage && i == pag}">
				    		<li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${CPList}&pag=${i}">${i}</a></li>
				    	</c:if>
				      <c:if test="${i <= totPage && i != pag}">
				    		<li class="page-item"><a class="page-link text-secondary" href="${CPList}&pag=${i}">${i}</a></li>
				    	</c:if>
				    </c:forEach>
				    <c:if test="${curBlock < lastBlock}">
				      <li class="page-item"><a class="page-link text-secondary" href="${CPList}&pag=${(curBlock+1)*blockSize + 1}">
				      	<i class="fa-solid fa-caret-right"></i>
				      </a></li>
				    </c:if>
				    <c:if test="${pag < totPage}">
				      <li class="page-item"><a class="page-link text-secondary" href="${CPList}&pag=${totPage}">
				      	<i class="fa-solid fa-forward-fast"></i>
				      </a></li>
				    </c:if>
				  </ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<script src="include/viewPage.js"></script>
</body>
</html>