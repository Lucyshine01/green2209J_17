<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>myInfo.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <link href="include/viewPage.css" rel="stylesheet" type="text/css">
  <script></script>
  <style>
  .item {
		position: relative; 
  }
  .form-item {
  	width : 115px;
  	text-align : center;
    font-size: 1.3em;
    border-radius: 20px;
    padding: 20px;
    padding-top: 5px;
    padding-bottom: 5px;
    background-color: #fff;
    box-shadow: 0px 1px 3px 0px #b0b0b0;
    color: #666;
    z-index: 2;
    /* width: 30%; */
  }
  .item .botLine {
  	position: absolute;
  	top: 38px;
  	left: 70px;
  	width: 80%;
  	border-bottom: 2px solid #e0e0e0;
  	z-index: 0;
  }
  .item .item-text{
  	width: 70%;
  	font-size: 1.1em;
  	font-weight: 400;
  	font-family: 'Spoqa Han Sans Neo';
  	color: #333;
  }
  </style>
</head>
<body ondragstart="return false" onselectstart="return false">
	<div id="loading_Bar"></div>
	<jsp:include page="/include/headTop.jsp"></jsp:include>
	<jsp:include page="/include/header.jsp"></jsp:include>
	<div class="width" style="padding: 30px; padding-top: 40px">
		<div class="text-center" style="font-size: 2.8em; font-weight: 600; margin-bottom: 30px;">
			회원 기본정보
		</div>
		<hr class="ml-auto mr-auto" style="margin-bottom: 50px; width: 90%; box-shadow: 0px 1px 1px 1px #aaa;"/>
		<div class="d-flex">
			<div class="d-flex fCol_center ml-auto mr-auto" style="width: 38%">
				<div class="d-flex mb-5 item">
					<div class="form-item">아이디</div>
					<div class="d-flex fCol_center text-center item-text mr-auto">
						${vo.mid}
					</div>
					<div class="botLine"></div>
				</div>
				<div class="d-flex mb-5 item">
					<div class="form-item">이메일</div>
					<div class="d-flex fCol_center text-center item-text mr-auto">
						${vo.email}
					</div>
					<div class="botLine"></div>
				</div>
				<div class="d-flex mb-5 item">
					<div class="form-item">연락처</div>
					<div class="d-flex fCol_center text-center item-text mr-auto">
						${vo.tel}
					</div>
					<div class="botLine"></div>
				</div>
				<div class="d-flex mb-5 item">
					<div class="form-item">가입일</div>
					<div class="d-flex fCol_center text-center item-text mr-auto">
						${fn:substring(vo.createDay,0,10)}
					</div>
					<div class="botLine"></div>
				</div>
			</div>
			<div class="d-flex fCol_center ml-auto mr-auto" style="width: 38%">
				<div class="d-flex mb-5 item">
					<div class="form-item">비밀번호</div>
					<div class="d-flex" style="width: 70%">
						<div class="ml-auto mr-auto">
							<input type="button" value="비밀번호 변경" onclick="pwdChangeModalOn();" class="btn btn-warning"/>
						</div>
					</div>
					<div class="botLine"></div>
				</div>
				<div class="d-flex mb-5 item">
					<div class="form-item">생년월일</div>
					<div class="d-flex fCol_center text-center item-text mr-auto">
						${fn:substring(vo.birth,0,10)}
					</div>
					<div class="botLine"></div>
				</div>
				<div class="d-flex mb-5 item">
					<div class="form-item">회원등급</div>
					<div class="d-flex fCol_center text-center item-text mr-auto">
						${vo.userLevel}
					</div>
					<div class="botLine"></div>
				</div>
				<div class="d-flex mb-5 item">
					<div class="form-item">포인트</div>
					<div class="d-flex fCol_center text-center item-text mr-auto">
						<fmt:formatNumber value="${vo.point}"/>pt
					</div>
					<div class="botLine"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<script src="include/viewPage.js"></script>
</body>
</html>