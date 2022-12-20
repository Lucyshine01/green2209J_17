<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>404.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <link href="include/viewPage.css" rel="stylesheet" type="text/css">
  <script>
  </script>
  <style></style>
</head>
<body ondragstart="return false" onselectstart="return false">
	<div id="loading_Bar"></div>
	<div class="width" style="padding-top: 250px">
		<div class="text-center" style="font-size: 2.7em;">
			<div style="font-size: 3em;">404</div>
			<div class="mt-2 mb-4" ><i class="fa-solid fa-triangle-exclamation"></i> 페이지를 찾을수 없습니다!</div>
		</div>
		<div class="text-center">
			<span class="text-center"><button type="button" onclick="location.href='${ctp}/'" class="btn btn-success" ><i class="fa-solid fa-house-chimney"></i> 홈으로 이동</button></span>
			<span class="text-center"><button type="button" onclick="history.back();" class="btn btn-info" ><i class="fa-solid fa-rotate-right"></i> 이전페이지로</button></span>
		</div>
	</div>
	<script src="include/viewPage.js"></script>
</body>
</html>