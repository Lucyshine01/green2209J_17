<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>home.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  
  <link href="include/viewPage.css" rel="stylesheet" type="text/css">
  <script></script>
  <style></style>
</head>

<body ondragstart="return false" onselectstart="return false">
	<jsp:include page="/include/header.jsp"></jsp:include>

	<jsp:include page="/include/body.jsp"></jsp:include>
	<script src="include/viewPage.js"></script>
</body>
</html>