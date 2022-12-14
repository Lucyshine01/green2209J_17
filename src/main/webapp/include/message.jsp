<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>message.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <!-- 순서 서버 언어 => 뷰 언어 -->
  <script>
  	'use strict'
  	 
  	let msg = "${msg}";
  	let url = "${url}";
  	let val = "${val}";
  	
  	if(msg == "") msg = "";
  	else(msg == "") msg = "";
  	
  	if(msg != "") alert(msg);
		if(url != "") location.href = url;
  </script>
  <style></style>
</head>
<body>
</body>
</html>