<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>adLeft.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <script></script>
  <style></style>
</head>
<body>
<p><br/></p>
<div class="container">
  <div class="text-center" style="font-size: 1.7em;font-weight: bold;">관리자메뉴</div>
  <hr/>
  <p class="text-center">
  	<a href="${ctp}/adminMain.ad" target="_top" class="btn btn-primary btn-lg">관리자 메인</a>
  </p>
  <p class="text-center">
  	<a href="${ctp}/adUserList.ad?pag=1&pageSize=5" target="adContent" class="btn btn-success btn-lg">회원 관리</a>
  </p>
  <p class="text-center">
  	<a href="${ctp}/adCPList.ad?pag=1&pageSize=5" target="adContent" class="btn btn-warning btn-lg">업체 관리</a>
  </p>
</div>
<p><br/></p>
</body>
</html>