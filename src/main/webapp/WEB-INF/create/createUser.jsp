<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>createUser.jsp</title>
  <jsp:include page="/include/bs4.jsp"></jsp:include>
  
  <link href="include/viewPage.css" rel="stylesheet" type="text/css">
  <script>
	  'use strict';
	  let idOverSw = 0;
	  let userSw = 0;
	  const date = new Date();
	  let year = date.getFullYear();
	  let month = date.getMonth() + 1;
	  let day = date.getDate();
	  let max = "" + (year-19) + "-" + month + "-" + day;
	  $(function(){
	    $("#cBtn1").click(function(){
	      $(this).addClass("activeBtn");
	      $("#cBtn2").removeClass("activeBtn");
	      $("#company").hide();
	      $("#companyTitle").hide();
	      $("#normalTitle").show();
	      userSw = 0;
	    });
	    $("#cBtn2").click(function(){
	      $(this).addClass("activeBtn");
	      $("#cBtn1").removeClass("activeBtn");
	      $("#company").show();
	      $("#companyTitle").show();
	      $("#normalTitle").hide();
	      userSw = 1;
	    });
	    $(".form-item").click(function(){
	      $(this).next().find("input").focus();
	    });
	
	    $("input[type=date]").attr("max",max);
	    
	    $("#mid").on("propertychange change paste input",function() {
	    	idOverSw = 0;
	    	$("#overCheckBtn").removeAttr("disabled");
	    	$("#check").hide();
	    });
	    
	  });
	  
	  function createUser() {
		  if(idOverSw = 0) {
			  alert("아이디 중복체크를 하세요!");
			  return;
		  }
			let mid = document.getElementById("mid").value;
			let pwd = document.getElementById("pwd").value;
			let email = document.getElementById("email").value;
			let birth = document.getElementById("birth").value;
			let tel = document.getElementById("tel").value;
			
			let regId = /^([a-zA-Z0-9]){6,20}$/g;   //아이디는 영문소문자,대문자,숫자,밑줄만 사용가능  
			let regPwd = /^([!@#$%^&+=<>?,\./\*()_-]?[a-zA-Z0-9]){6,20}$/g;  //비밀번호는 영문대,소문자,숫자,키보드에서 입력가능한 특수문자 사용가능 최소6자~최대20자
			let regEmail = /^([-_.]?[0-9a-zA-Z]){4,20}@+([-_.]?[0-9a-zA-Z]){4,20}.+[a-zA-Z]{2,3}$/i; //이메일 형식에 맞도록 체크(a@b.c)
			let regTel = /^([0-9]){2,3}-+([0-9]){3,4}-+([0-9]){3,4}$/g;
			
			if(mid.trim() == ""){
				alert("아이디를 입력해주세요!");
	      document.getElementById("mid").focus();
	      return false;
			}
			else if(pwd.trim() == ""){
				alert("비밀번호를 입력해주세요!");
	      document.getElementById("pwd").focus();
	      return false;
			}
			else if(email.trim() == ""){
				alert("이메일을 입력해주세요!");
	      document.getElementById("email").focus();
	      return false;
			}
			else if(birth.trim() == ""){
				alert("생일을 등록해주세요!");
	      document.getElementById("brith").focus();
	      return false;
			}
			else if(tel.trim() == ""){
				alert("전화번호를 입력해주세요!");
	      document.getElementById("tel").focus();
	      return false;
			}
			
			if(!mid.match(regId)){
				alert("허용되지 않는 아이디입니다!");
	      document.getElementById("mid").focus();
	      return false;
			}
			else if(!pwd.match(regPwd)){
				alert("허용되지 않는 비밀번호입니다!");
	      document.getElementById("pwd").focus();
	      return false;
			}
			else if(!email.match(regEmail)){
				alert("허용되지 않는 이메일입니다!");
	      document.getElementById("email").focus();
	      return false;
			}
			else if(!tel.match(regTel)){
				alert("허용되지 않는 전화번호입니다!");
	      document.getElementById("tel").focus();
	      return false;
			}
			
			if(userSw == 0){
				$("#sw").val("0");
				createForm.submit();
				return;
			}
			
			
			let name = document.getElementById("name").value;
			let cpName = document.getElementById("cpName").value;
			let cpIntro = document.getElementById("cpIntro").value;
			let cpAddr = document.getElementById("cpAddr").value;
			let cpHomePage = document.getElementById("cpHomePage").value;
			let cpExp = document.getElementsByName("cpExp");
			
			let cpImg = document.getElementById("cpImg").value;
			let exp = cpImg.substring(cpImg.lastIndexOf('.')).toUpperCase();
			cpImg = cpImg.substring(0,cpImg.lastIndexOf('.'));
			
			
			let strExp = "";
			for(let i=0; i<cpExp.length; i++){
				if(cpExp[i].checked){
					strExp += cpExp[i].value + "/";
				}
			}
			if(strExp.length > 1){
				strExp = strExp.substring(0, strExp.length-1);
			}
			$("#strExp").val(strExp);
			
			if(name.trim() == ""){
				alert("대표명을 입력해주세요!");
	      document.getElementById("name").focus();
	      return false;
			}
			else if(cpName.trim() == ""){
				alert("회사명을 입력해주세요!");
	      document.getElementById("cpName").focus();
	      return false;
			}
			else if(cpAddr.trim() == ""){
				alert("회사 주소를 입력해주세요!");
	      document.getElementById("cpAddr").focus();
	      return false;
			}
			
			let imgSize = 0;
			
			if(cpImg == "")imgSize = 0;
			else if(cpImg != null){
				if(exp != '.PNG' && exp != '.JPG'){
					alert("로고 파일명 확장자가 잘못 되었습니다.");
		      document.getElementById("cpImg").focus();
		      return false;
				}
				imgSize = document.getElementById("cpImg").files[0].size;
			}
			
			$("#imgSize").val(imgSize);
			
			if(userSw == 1){
				$("#sw").val("1");
				createForm.submit();
			}
		
		}
		
	  function idOverCheck() {
			let mid = $("#mid").val();
			let regId = /^([a-zA-Z0-9]){6,20}$/g;
			if(mid.trim() == ""){
				alert("아이디를 입력해주세요.");
	      document.getElementById("mid").focus();
	      return false;
			}
			else if(!mid.trim().match(regId)){
				alert("허용되지 않는 아이디입니다!");
	      document.getElementById("mid").focus();
	      return false;
			}
			$.ajax({
				type: "post",
				url : "${ctp}/idOverCheck.us",
				data: {mid:mid},
				success: function(res) {
					if(res=="0"){
						alert("사용하실수 있는 아이디입니다.");
						idOverSw = 1;
						$("#overCheckBtn").attr("disabled","");
						$("#check").show();
					}
					else {
						alert("중복된 아이디가 있습니다!");
					}
				},
				error: function() {
					alert("전송 오류");
				}
			});
		}
  </script>
  <style>
  	input[type=checkbox] {zoom: 1.3;}
    input[type=text]::placeholder,input[type=password]::placeholder {
      font-family: 'Spoqa Han Sans Neo', 'sans-serif';
      color: #bbb;
      font-size: 1em;
    }
    #check{display: none;}
    #changeBtnBar {
      padding-top: 80px;
      cursor: pointer !important;
    }
    #changeBtnBar .changeBtn {
      font-family: 'Spoqa Han Sans Neo', 'sans-serif';
      font-size: 1.3em;
      font-weight: 500;
      padding: 10px 137px;
      border: 1px solid #ddd;
      /* border-bottom: none; */
      background-color: #fff;
    }
    .activeBtn {
      /* background-color: #fafafa !important; */
      border: 1px solid #ffde4d !important;
    }
    #form {
      padding: 30px 0;
      margin: 0 auto;
      margin-top: 8px;
      /* margin-bottom: 9px; */
      width: 700px;
      box-shadow: 0px 1px 3px 1px #d0d0d0;
      /* border-radius: 1; */
      border-bottom-left-radius: 10px;
      border-bottom-right-radius: 10px;
      background-color: #fff;
    }
    #form #company, #form #companyTitle {display: none;}
    .form-item {
      font-size: 1.2em;
      border-radius: 20px;
      padding-top: 5px;
      padding-bottom: 5px;
      background-color: #fff;
      box-shadow: 0px 1px 3px 0px #d0d0d0;
      cursor: pointer;
      /* width: 30%; */
    }
    .form-item:hover {
      background-color: #f0f0f0;
    }
    .form-item + div > input {
      padding: 0px 5px;
      text-align: center;
    }
    .item-row {
      margin-top: 25px;
      margin-bottom: 35px;
    }
    .categori {
      font-size: 1.2em;
      font-weight: 500;
      box-shadow: 0px 1px 3px 0px #d6d6d6;
      border-radius: 30px;
      padding: 10px 0px;
      margin-bottom: 15px;
      margin-top: 12px;
      pointer-events: none;
    }
    .categori-item {
      /* margin-right: 10px; */
      font-family: 'Spoqa Han Sans Neo', 'sans-serif';
      font-weight: 500;
      font-size: 1em;
      pointer-events: none;
    }
    .checkFrom input[type=checkbox] {margin-left: 10px;}
    .invalid-feedback {
      position: absolute;
      top: 32px;
    }
  </style>
</head>
<body style="overflow-x: auto" ondragstart="return false" onselectstart="return false">
<div id="loading_Bar"></div>
<jsp:include page="/include/header.jsp"></jsp:include>
  <div style="background-color: #fafafa; padding-bottom: 100px;">
    <div class="width">
      <div id="changeBtnBar" class="text-center">
        <span id="cBtn1" class="changeBtn activeBtn" style="border-right: none;">일반 회원</span><span id="cBtn2" class="changeBtn" style="border-left: none;">기업 회원</span>
      </div>
      <div id="form">
        <div id="normal" class="text-center">
        	<div id="normalTitle">
	          <div class="mb-2 titleContent">일 반 회 원 가 입</div>
	          <span class="subtitleContent">회원가입은 만 19세 이상부터 가능합니다.</span>
          </div>
          <div id="companyTitle">
	          <div class="mb-2 titleContent">기 업 회 원 가 입</div>
	          <span class="subtitleContent">기업 회원은 가입 완료시 관리자의 승인 전까지<br/>일반 회원으로 분류되어집니다.</span>
          </div>
          <form name="createForm" method="post" class="was-validated" action="${ctp}/createUserOk.us" enctype="multipart/form-data" >
            <div class="container">
              <div class="row item-row">
                <div class="col-2"></div>
                <div class="col-3 d-flex fCol_center form-item">아이디</div>
                <div class="col-5 d-flex fCol_center">
                  <input type="text" id="mid" name="mid" class="form-control" placeholder="아이디를 입력하세요" required>
                  <div class="invalid-feedback text-left">
                    아이디는 최소 6자 최대 20자입니다.
                  </div>
                </div>
                <div class="col-2 d-flex" style="padding:0px;">
                	<div class="d-flex fCol_center"><input type="button" onclick="idOverCheck()" value="중복체크" id="overCheckBtn" style="float: left; width: 80px"/></div>
                	<div class="d-flex fCol_center ml-2"><i id="check" class="fa-regular fa-circle-check" style="color: #03E646; font-size: 1.3em"></i></div>
                </div>
              </div>
              <div class="row item-row">
                <div class="col-2"></div>
                <div class="col-3 d-flex fCol_center form-item">비밀번호</div>
                <div class="col-5 d-flex fCol_center">
                  <input type="password" name="pwd" id="pwd" class="form-control" placeholder="비밀번호를 입력하세요" required>
                  <div class="invalid-feedback text-left">
                    비밀번호는 영문,숫자를 합쳐 최소 8자 이상입니다.
                  </div>
                </div>
                <div class="col-2"></div>
              </div>
              <div class="row item-row">
                <div class="col-2"></div>
                <div class="col-3 d-flex fCol_center form-item">이메일</div>
                <div class="col-5 d-flex fCol_center">
                  <input type="text" name="email" id="email" class="form-control" placeholder="example@exam.ple" required>
                  <div class="invalid-feedback text-left">
                    이메일을 입력하세요.
                  </div>
                </div>
                <div class="col-2"></div>
              </div>
              <div class="row item-row">
                <div class="col-2"></div>
                <div class="col-3 d-flex fCol_center form-item">생년월일</div>
                <div class="col-5 d-flex fCol_center">
                  <input type="date" name="birth" id="birth" class="form-control" required>
                  <div class="invalid-feedback text-left">
                    생년월일을 입력해주세요
                  </div>
                </div>
                <div class="col-2"></div>
              </div>
              <div class="row item-row">
                <div class="col-2"></div>
                <div class="col-3 d-flex fCol_center form-item">Phone</div>
                <div class="col-5 d-flex fCol_center">
                  <input type="text" name="tel" id="tel" class="form-control" placeholder="ex (010)-####-###" required>
                  <div class="invalid-feedback text-left">
                    전화번호를 입력해주세요.
                  </div>
                </div>
                <div class="col-2"></div>
              </div>
              <div id="company">
	              <div class="titleContent" style="margin-top: 50px; margin-bottom: 20px"> 기업 회원용 </div>
	              <span class="subtitleContent">부적합한 내용으로 인해 관리자 승인을 받지 못할 경우<br/>1대1 문의를 통해 수정을 요청하실수 있습니다.</span>
	              <div class="row item-row">
		              <div class="col-2"></div>
		              <div class="col-3 d-flex fCol_center form-item">회사명</div>
		              <div class="col-5 d-flex fCol_center">
		                <input type="text" name="cpName" id="cpName" class="form-control" required>
		                <div class="invalid-feedback text-left">
		                  회사명을 입력해주세요.
		                </div>
		              </div>
		              <div class="col-2"></div>
		            </div>
		            <div class="row item-row">
		              <div class="col-2"></div>
		              <div class="col-3 d-flex fCol_center form-item">대표 성명</div>
		              <div class="col-5 d-flex fCol_center">
		                <input type="text" name="name" id="name" class="form-control" required>
		                <div class="invalid-feedback text-left">
		                  대표명을 입력해주세요.
		                </div>
		              </div>
		              <div class="col-2"></div>
		            </div>
		            <div class="row item-row">
		              <div class="col-2"></div>
		              <div class="col-3 d-flex fCol_center form-item">회사 주소</div>
		              <div class="col-5 d-flex fCol_center">
		                <input type="text" name="cpAddr" id="cpAddr" class="form-control" required>
		                <div class="invalid-feedback text-left">
		                  주소를 입력해주세요.
		                </div>
		              </div>
		              <div class="col-2"></div>
		            </div>
		            <div class="row item-row">
		              <div class="col-2"></div>
		              <div class="col-3 d-flex fCol_center form-item">홈페이지</div>
		              <div class="col-5 d-flex fCol_center">
		                <input type="text" name="cpHomePage" id="cpHomePage" class="form-control" placeholder="홈페이지가 있으시면 기입해주세요">
		              </div>
		              <div class="col-2"></div>
		            </div>
		            <div class="row item-row">
		              <div class="col-2"></div>
		              <div class="col-2 d-flex fCol_center form-item">전문분야</div>
		              <div class="col-6">
		                <div class="text-center categori">인테리어</div>
		                <div class="d-flex checkFrom justify-content-start mb-2">
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="홈 인테리어" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;홈 인테리어</div>
		                  </div>
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="상업 인테리어" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;상업 인테리어</div>
		                  </div>
		                </div>
		                <div class="d-flex checkFrom justify-content-start">
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="조명 인테리어" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;조명 인테리어</div>
		                  </div>
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="욕실,화장실 인테리어" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;욕실,화장실 인테리어</div>
		                  </div>
		                </div>
		                <div class="text-center categori">시공</div>
		                <div class="d-flex checkFrom justify-content-start mb-2">
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="타일시공" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;타일시공</div>
		                  </div>
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="페인트시공" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;페인트시공</div>
		                  </div>
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="싱크대 교체" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;싱크대 교체</div>
		                  </div>
		                </div>
		                <div class="d-flex checkFrom justify-content-start">
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="도배장판" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;도배장판시공</div>
		                  </div>
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="인테리어 필름" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;인테리어 필름시공</div>
		                  </div>
		                </div>
		                <div class="text-center categori">디자인</div>
		                <div class="d-flex checkFrom justify-content-start mb-2">
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="도면 제작·수정" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;도면 제작·수정</div>
		                  </div>
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="인테리어 컨설팅" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;인테리어 컨설팅</div>
		                  </div>
		                </div>
		                <div class="d-flex checkFrom justify-content-start">
		                  <div class="d-flex">
		                    <div class="d-flex fCol_center"><input type="checkbox" name="cpExp" value="3D 모델링" ></div>
		                    <div class="d-flex fCol_center categori-item">&nbsp;3D 모델링</div>
		                  </div>
		                </div>
		              </div>
		              <div class="col-2"></div>
		            </div>
		            <div class="row item-row">
		              <!-- <div class="col-1"></div> -->
		              <div class="col-1 d-flex fCol_center form-item" style="margin-left: 40px;">회사 소개</div>
		              <div class="col-10 d-flex fCol_center"><textarea rows="7" name="cpIntro" id="cpIntro" placeholder="소개" class="form-control" wrap="off" style="resize: none;" ></textarea></div>
		              <!-- <div class="col-1"></div> -->
		            </div>
		            <div class="row item-row">
		              <div class="col-2"></div>
		              <div class="col-3 d-flex fCol_center form-item">회사명 로고</div>
		              <div class="col-5 d-flex fCol_center">
		                <input type="file" name="cpImg" id="cpImg" class="mt-2 mb-3" required>
		                <div class="invalid-feedback text-left" style="color: #666; top: 40px;">
		                  회사로고 이미지를 넣어주세요.(jpg,png파일만 허용합니다. 최대 10MByte)
		                </div>
		              </div>
		              <div class="col-2"></div>
		            </div>
	            </div>
              <div class="row item-row">
                <div class="col-3"></div>
                <div class="col-6 d-flex fCol_center" style="margin-top: 30px;">
                  <input type="button" value="회원가입" onclick="createUser();" class="btn btn-warning">
                </div>
                <div class="col-3"></div>
              </div>
            </div>
            <input type="hidden" name="strExp" id="strExp"/>
            <input type="hidden" name="sw" id="sw" />
            <input type="hidden" name="imgSize" id="imgSize"/>
          </form>
        </div>
      </div>
    </div>
  </div>
<jsp:include page="/include/footer.jsp"></jsp:include>
<script src="include/viewPage.js"></script>
</body>
</html>