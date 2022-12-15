<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>message.jsp</title>
  <!-- 순서 서버 언어 => 뷰 언어 -->
  <script>
  	'use strict'
  	 
  	let msg = "${msg}";
  	let url = "${url}";
  	let val = "${val}";
  	
  	if(msg == "createCPNo1") msg = "회원가입을 먼저해주세요.";
  	else if(msg == "createCPNo2") msg = "일반 회원만 가능합니다.";
  	else if(msg == "createUserOk") msg = "회원가입이 완료되었습니다. 다시 로그인해주세요.";
  	else if(msg == "createUserNo") msg = "회원가입에 실패했습니다.";
  	else if(msg == "createCompanyOk") msg = "업체가입이 완료되었습니다. 관리자가 승인할시 업체계정으로 승격됩니다.";
  	else if(msg == "createCompanyNo") msg = "업체가입에 실패했습니다. 등록하신 아이디로 로그인해서 다시 업체등록을 진행해주세요.";
  	else if(msg == "noSession") msg = "비로그인 상태거나 세션이 만료되었습니다. 로그인을 진행해주세요.";
  	else if(msg == "haveSession") msg = "";
  	
  	if(msg != "") alert(msg);
		if(url != "") location.href = url;
  </script>
  <style></style>
</head>
<body>
</body>
</html>