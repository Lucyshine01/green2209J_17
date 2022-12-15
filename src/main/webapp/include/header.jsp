<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<div id="headMain" class="container-fluid-xl" style="z-index: 9;background-color:#fff;" >
  <div class="width d-flex">
    <div id="logo" class="d-flex fCol_center header pl-4">
    <!-- http://192.168.50.79:9090/green2209J_17/ -->
      <a href="${ctp}/"><img src="${ctp}/images/viewPage/logo3.png" width="120px"/></a>
    </div>
    <div></div>
    <div id="searchBox" class="ml-auto d-flex text-center fCol_center header">
      <input type="text" id="search" name="search" placeholder="서비스, 업체를 검색해 보세요" autocomplete='off' spellcheck="false">
      <i class="fa-solid fa-magnifying-glass"></i>
      <i class="fa-solid fa-circle-xmark iconHidden" onclick="removeSearch()"></i>
    </div>
    <div id="loginBox" class="d-flex fCol_center header">
      <div>
      	<c:if test="${sUserLevel == '일반' || empty sMid}">
      		<button type="button" onclick="location.href='${ctp}/createCP.us'" class="btn w3-hover-light-grey text-dark ml-3 pl-4 pr-4">
          업체등록
        	</button>
      	</c:if>
      	<c:if test="${sUserLevel == '업체'}">
      		<button type="button" onclick="location.href='${ctp}/createCP.us'" class="btn w3-hover-light-grey text-dark ml-3 pl-4 pr-4">
          업체정보
        	</button>
      	</c:if>
        <c:if test="${empty sMid}">
	        <button type="button" onclick="loginModalOn();" class="btn w3-hover-light-grey text-dark ml-3 pl-4 pr-4">
	          로그인
	        </button>
	        <button type="button" onclick="location.href='${ctp}/create.us'" style="margin-right: 15px" class="btn w3-2017-primrose-yellow w3-hover-amber btn-warning text-dark ml-3 pl-4 pr-4">
	          회원 가입
	        </button>
        </c:if>
        <c:if test="${!empty sMid}">
	        <button type="button" onclick="logout();" class="btn w3-hover-light-grey text-dark ml-3 pl-4 pr-4">
	          로그아웃
	        </button>
	        <button type="button" onclick="location.href='${ctp}/myInfo.us'" style="margin-right: 15px" class="btn w3-2017-primrose-yellow w3-hover-amber btn-warning text-dark ml-3 pl-4 pr-4">
	          내정보
	        </button>
        </c:if>
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
            <a class="dropdown-item" href="#">홈 인테리어</a>
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
      <div style="width: 150px;"></div>
    </nav>
    <div class="ml-auto d-flex">
    <div class="d-flex fCol_center" style="padding-right: 15px">
      <a href="#" class="nav-link nav-icon d-flex fCol_center" style="font-size: 1em;">
        <span>원하는 서비스를 못 찾겠다면, <b>직접 의뢰</b>를 해보세요! ></span>
      </a>
    </div>
    </div>
  </div>
  <hr/>
</div>

<!-- fix시 공백채우기 -->
<div id="headMainSpace"></div>
<!-- fix시 공백채우기 -->

<!-- 로그인 모달 -->
<div id="loginModal">
	<div class="modalBack"></div>
	<div class="width" style="position: relative; ">
		<div class="modalMain d-flex">
			<div class="d-flex fCol_center" style="width: 50%">
				<div class="text-center mb-3" style="font-size: 1.8em; font-weight: 500;">MEMBER LOGIN</div>
				<div class="p-4 ml-auto mr-auto" style="font-size: 1.1em; width: 80%;">
					<form>
						<div>로그인</div>
						<input type="text" name="loginMid" id="loginMid" class="form-control mt-2 mb-3" autocomplete='off'/>
						<div>비밀번호</div>
						<input type="password" name="loginPwd" id="loginPwd" class="form-control mt-2 mb-1" />
						<div id="loginInfo" class="mb-2 ml-1" style="color: red;font-size: 0.8em;font-weight: 300;">&nbsp;</div>
						<input type="button" value="로그인" onclick="loginCheck();" class="btn btn-success mb-2 form-control"/>
						<input type="button" value="회원가입" onclick="location.href='${ctp}/create.us'" class="btn btn-primary mb-2 form-control" />
					</form>
				</div>
				<div>
				</div>
			</div>
			<div class="ml-auto" style="width: 45%">
				<div class="modalClose" onclick="modalClose();"><i class="fa-solid fa-xmark"></i></div>
				<div class="d-flex fCol_center" style="height: 400px; border-left: 2px solid #e2e2e2; padding-left: 50px; padding-right: 30px;">
					<div class="text-left mb-2" style="font-size: 1.8em; font-weight: 500; font-family: 'Spoqa Han Sans Neo', 'sans-serif';">
						인테리어가 필요할땐
					</div>
					<img src="${ctp}/images/viewPage/logo-big.png" width="100%">
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 비밀번호 변경 모달 -->
<div id="pwdChangeModal">
	<div class="modalBack"></div>
	<div class="width" style="position: relative;">
		<div class="modalMain d-flex">
			<div class="d-flex fCol_center" style="width: 50%">
				<div class="text-center mb-3" style="font-size: 1.8em; font-weight: 500;">비밀번호 변경</div>
				<div class="p-4 ml-auto mr-auto" style="font-size: 1.1em; width: 80%;">
					<form>
						<div>기존 비밀번호</div>
						<input type="password" name="oldPwd" id="oldPwd" class="form-control mt-2 mb-3" autocomplete='off'/>
						<div>새로운 비밀번호</div>
						<input type="password" name="newPwd" id="newPwd" class="form-control mt-2 mb-1" />
						<div>비밀번호 재확인</div>
						<input type="password" name="newPwd2" id="newPwd2" class="form-control mt-2 mb-1" />
						<div id="pwdChangeInfo" class="mb-2 ml-1" style="color: red;font-size: 0.8em;font-weight: 300;">&nbsp;<br/>&nbsp;</div>
						<input type="button" value="비밀번호 변경" onclick="pwdChangeCheck();" class="btn btn-success mb-2 form-control"/>
					</form>
				</div>
				<div>
				</div>
			</div>
			<div class="ml-auto" style="width: 45%">
				<div class="modalClose" onclick="modalClose();"><i class="fa-solid fa-xmark"></i></div>
				<div class="d-flex fCol_center" style="height: 400px; border-left: 2px solid #e2e2e2; padding-left: 50px; padding-right: 30px;">
					<div class="text-left mb-2" style="font-size: 1.8em; font-weight: 500; font-family: 'Spoqa Han Sans Neo', 'sans-serif';">
						인테리어가 필요할땐
					</div>
					<img src="${ctp}/images/viewPage/logo-big.png" width="100%">
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	function pwdChangeCheck() {
		let sMid = "${sMid}";
		let oldPwd = $("#oldPwd").val();
		let newPwd = $("#newPwd").val();
		let newPwd2 = $("#newPwd2").val();
		let regPwd = /^([!@#$%^&+=<>?,\./\*()_-]?[a-zA-Z0-9]){6,20}$/g;
		if(oldPwd.trim() == "" || newPwd.trim() == "" || newPwd.trim() == ""){
			$("#pwdChangeInfo").html("모든 칸에 입력을 완료해주세요!<br/>&nbsp;");
			return;
		}
		if(newPwd != newPwd2){
			$("#pwdChangeInfo").html("재입력한 비밀번호가 불일치합니다<br/>&nbsp;");
			return;
		}
		if(!newPwd.match(regPwd)){
			$("#pwdChangeInfo").html("새 비밀번호가 양식에 올바르지 않습니다.<br/>다른 비밀번호를 입력해주세요.");
			return;
		}
		
		let res_sw = '0';
		$.ajax({
			type: "post",
			url : "{ctp}/oldPwdCheck.us",
			data: {mid:sMid,pwd:oldPwd},
			async: false,										// ajax 순차적(동기식 처리)
			success: function(res) {
				if(res == '1') res_sw = res;
				else $("#pwdChangeInfo").html("기존 비밀번호가 불일치합니다.<br/>&nbsp;");
			},
			error: function() {
				alert("전송 오류");
			}
		});
		
		if(res_sw == '0') return;
		
		if(oldPwd == newPwd){
			$("#pwdChangeInfo").html("기존과 같은 비밀번호입니다.<br/>다른 비밀번호를 입력해주세요.");
			return;
		}
		$.ajax({
			type: "post",
			url : "{ctp}/pwdUpdate.us",
			data: {mid:sMid,pwd:newPwd},
			async: false,
			success: function(res) {
				if(res == '1') {
					alert("비밀번호가 변경되었습니다.");
					location.href='${ctp}/myInfo.us';
				}
				else {
					$("#pwdChangeInfo").html("서버 오류로 인해 변경에 실패했습니다.<br/>&nbsp;");
					return;
				}
			},
			error: function() {
				alert("전송 오류");
			}
		});
	}
	
	function loginCheck() {
		let mid = $("#loginMid").val();
		let pwd = $("#loginPwd").val();
		
		if(mid.trim() == "" || pwd.trim() == ""){
			$("#loginInfo").html("아이디와 비밀번호를 입력후 로그인해주세요.");
			return;
		}
		
		$.ajax({
			type: "post",
			url : "${ctp}/loginCheck.us",
			data: {mid:mid,pwd:pwd},
			success: function(res){
				if (res == '1') location.href = "${ctp}/";
				else $("#loginInfo").html("아이디 혹은 비밀번호가 불일치합니다.");
			},
			error: function(){
				alert("전송 오류");
			}
		});
	}
	
	function logout() {
		$.ajax({
			url : "${ctp}/logoutCheck.us",
			success: function(){
				location.href = "${ctp}/";
			}
		});
	}
</script>
