<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>title.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  <link href="include/viewPage.css" rel="stylesheet" type="text/css">
  <script>
  	'use strict';
  	function ansboxOpen(idx) {
  		if($("#sw"+idx).val() != 1){
	  		$("#sw"+idx).val("1");
				$("#ansbox"+idx).show();
  		}
  		else  if($("#sw"+idx).val() == 1) {
  			$("#sw"+idx).val("0");
				$("#ansbox"+idx).hide();
  		}
		}
  	function helpInputModalOn() {
  		$("#helpInputModal").show();
  		$("#helpInputModal").animate({opacity:"1"},200);
  	}
  	function helpInputCheck() {
			let title = $("#title").val();
			let content = $("#content").val();
			if(title.length > 50){
				$("#helpModalInfo").html("제목의 길이가 너무 깁니다!<br/>");
				return;
			}
			let query = {
					title: title,
					content: content,
					mid : '${sMid}'
			}
			
			$.ajax({
				type: "post",
				url : "${ctp}/helpInput.he",
				data: query,
				success: function(res) {
					if(res == '1') {
						alert("문의 내용이 정상적으로 전달 되었습니다.");
						location.reload();
					}
					else alert("서버 오류로 인해 문의 작성에 실패했습니다.");
				},
				error: function() {
					alert("전송오류");
				}
			});
		}
  </script>
  <style>
  	.helpBox {
  		font-size: 4.5em;
  		padding: 10px;
  		color: #aaa;
  		width: 90px;
  	}
  	.mainBox {
  		padding: 5px;
  		padding-left: 60px;
  		font-size: 1.2em;
		  font-weight: 400;
		  font-family: 'Spoqa Han Sans Neo', 'sans-serif';
		  color: #333;
	  	word-wrap:break-word;
  	}
  	.contentBox {
  		width: 650px;
  		padding: 30px 0px;
  		font-size: 1em;
  		padding-left: 50px;
  		word-wrap:break-word;
  	}
  	.rightBox {
  		padding: 5px;
  		width: 15%;
  		font-size: 1.1em;
		  font-weight: 400;
		  font-family: 'Spoqa Han Sans Neo', 'sans-serif';
		  color: #333;
		  cursor: pointer;
  	}
  	.dateBox {font-size: 1em;}
  	.ansbox {display: none;}
  </style>
</head>
<body ondragstart="return false" onselectstart="return false">
	<div id="loading_Bar"></div>
	
	<div id="helpInputModal">
		<div class="modalBack"></div>
		<div class="width" style="position: relative;">
			<div class="modalMain d-flex">
				<div class="d-flex fCol_center ml-auto mr-auto" style="width: 95%">
					<div class="text-center mb-3" style="font-size: 1.8em; font-weight: 500;">문의</div>
					<div class="p-4 ml-auto mr-auto" style="font-size: 1.1em; width: 80%;">
						<form>
							<div>문의 제목</div>
							<input type="text" name="title" id="title" class="form-control mt-2 mb-3" autocomplete='off'/>
							<div>건의 내용</div>
							<textarea rows="9" name="content" id="content" class="form-control mt-2 mb-1" autocomplete='off' style="resize: none;"></textarea>
							<div id="helpModalInfo" class="mb-2 ml-1" style="color: red;font-size: 0.8em;font-weight: 300;">&nbsp;<br/>&nbsp;</div>
							<input type="button" value="작성 완료" onclick="helpInputCheck();" class="btn btn-success mb-2 form-control"/>
						</form>
					</div>
					<div>
					</div>
				</div>
				<%-- <div class="ml-auto" style="width: 45%">
					<div class="modalClose" onclick="modalClose();"><i class="fa-solid fa-xmark"></i></div>
					<div class="d-flex fCol_center" style="height: 400px; border-left: 2px solid #e2e2e2; padding-left: 50px; padding-right: 30px;">
						<div class="text-left mb-2" style="font-size: 1.8em; font-weight: 500; font-family: 'Spoqa Han Sans Neo', 'sans-serif';">
							인테리어가 필요할땐
						</div>
						<img src="${ctp}/images/viewPage/logo-big.png" width="100%">
					</div>
				</div> --%>
			</div>
		</div>
	</div>
	
	<jsp:include page="/include/header.jsp"></jsp:include>
	<div style="background-color: rgb(245,245,245);">
	<div class="width" style="padding: 30px; padding-top: 40px">
		<div class="text-center" style="font-size: 2.8em; font-weight: 600; margin-bottom: 10px;">
			1대1 문의
		</div>
		<div class="text-center mb-4" style="color: #999; font-weight: 300">
			개인정보 혹은 업체정보가 잘못된 경우 문의를 통해 수정하실수 있습니다.<br/>
			그외 서비스 장애 등에 대한 문의를 주실경우 신속하게 처리하겠습니다.<br/>
		</div>
		<div style="padding: 40px 150px 60px 150px; background-color: #fff">
			<div class="d-flex">
				<div class="ml-auto m-3">
					<input type="button" onclick="helpInputModalOn();" value="문의 작성" class="btn btn-secondary btn-sm p-2" />
				</div>
			</div>
			<div style="border-top: 3px solid #aaa; padding-top: 30px;">
				<div class="d-flex">
					<div class="helpBox d-flex fCol_center text-center"></div>
					<div class="mainBox d-flex fCol_center text-center ml-auto mr-auto" >문의 제목</div>
					<div class="rightBox d-flex fCol_center text-center">답변 상태</div>
					<div class="rightBox arrowBox d-flex fCol_center text-center mr-3">작성일</div>
				</div>
				<c:if test="${tot == 0}">
					<div class="text-center pb-4" style="padding-top: 60px">
						문의내용이 없습니다.
					</div>
				</c:if>
				<c:if test="${tot != 0}">
					<c:forEach var="vo" items="${vos}" varStatus="st">
						<div style="border-bottom: 2px solid #ddd;">
							<div class="d-flex pb-4 pt-3">
								<div class="helpBox d-flex fCol_center text-center"><i class="fa-solid fa-circle-info"></i></div>
								<div onclick="ansboxOpen(${st.count})" class="mainBox d-flex fCol_center text-left" style="cursor: pointer;">${vo.title}</div>
								<div onclick="ansboxOpen(${st.count})" class="rightBox d-flex fCol_center text-center ml-auto">${vo.conf == 'off' ? '답변준비중' : '답변완료' }</div>
								<div onclick="ansboxOpen(${st.count})" class="rightBox d-flex fCol_center text-center mr-3 dateBox">${fn:substring(vo.writeDay,0,10)}<br/>${fn:substring(vo.writeDay,11,16)}</div>
							</div>
							<input type="hidden" id="sw${st.count}" value="0"/>
							<div id="ansbox${st.count}" class="ansbox" style="background-color: rgb(245,245,245);">
								<div class="d-flex">
									<div class="ml-3 helpBox d-flex fCol_center text-center" style="font-size: 3.5em"><i class="fa-solid fa-question"></i></div>
									<div class="mainBox contentBox d-flex fCol_center text-left">${vo.content}</div>
								</div>
								<div class="ml-4 mr-4" style="border-bottom: 2px solid #ddd;"></div>
								<div class="d-flex">
									<div class="ml-3 helpBox d-flex fCol_center text-center" style="font-size: 3.5em"><i class="fa-solid fa-circle-exclamation"></i></div>
									<div class="mainBox contentBox d-flex fCol_center text-left">
										<c:if test="${empty vo.answer}"><div class="text-center" style="font-size: 1.1em; color: #333">답변대기중</div></c:if>
										<c:if test="${!empty vo.answer}">${vo.answer}</c:if>
									</div>
									<c:if test="${!empty vo.answer}">
										<div class="ml-auto mr-3 text-center rightBox dateBox d-flex fCol_center">
											답변일<br/>${fn:substring(vo.answerDay,0,10)}<br/>${fn:substring(vo.answerDay,11,16)}
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
			
		</div>
	</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<script src="include/viewPage.js"></script>
</body>
</html>