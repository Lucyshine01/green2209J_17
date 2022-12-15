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
	  
	  function cpCreateCheck() {
		  let name = document.getElementById("name").value;
			let cpName = document.getElementById("cpName").value;
			let cpIntro = document.getElementById("cpIntro").value;
			let cpAddr = document.getElementById("cpAddr").value;
			let cpHomePage = document.getElementById("cpHomePage").value;
			let cpExp = document.getElementsByName("cpExp");
			
			let cpImg = document.getElementById("cpImg").value;
			let exp = cpImg.substring(cpImg.lastIndexOf('.')).toUpperCase();
			cpImg = cpImg.substring(0,cpImg.lastIndexOf('.'));
			
			let regName = /^([가-힣a-zA-Z]{2,10})$/g;  //성명은 한글/영문만 가능하도록 길이는 2~20자까지
			let regCPName = /^([가-힣a-zA-Z]{2,50})$/g;
			let regCPImg = /^([가-힣a-zA-Z0-9]{1,40})$/g;
			
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
			else if(cpImg.trim() == ""){
				alert("회사로고를 등록해주세요!");
	      document.getElementById("cpImg").focus();
	      return false;
			}
			else if(cpAddr.trim() == ""){
				alert("회사 주소를 입력해주세요!");
	      document.getElementById("cpAddr").focus();
	      return false;
			}
			
			
			if(!name.match(regName)){
				alert("대표명을 확인해주세요!");
	      document.getElementById("name").focus();
	      return false;
			}
			else if(!cpName.match(regCPName)){
				alert("회사명을 확인해주세요!");
	      document.getElementById("cpName").focus();
	      return false;
			}
			else if(!cpImg.substring(cpImg.lastIndexOf('\\')+1).match(regCPImg)){
				alert("로고 파일명이 잘못되었습니다!");
	      document.getElementById("cpImg").focus();
	      return false;
			}
			else if(exp != '.PNG' && exp != '.JPG'){
				alert("로고 파일명 확장자가 잘못 되었습니다.");
	      document.getElementById("cpImg").focus();
	      return false;
			}
			
			let imgSize = document.getElementById("cpImg").files[0].size;
			$("#imgSize").val(imgSize);
			
			companyForm.submit();
		
		}
		
  </script>
  <style>
  	input[type=checkbox] {zoom: 1.3;}
    input[type=text]::placeholder,input[type=password]::placeholder {
      font-family: 'Spoqa Han Sans Neo', 'sans-serif';
      color: #bbb;
      font-size: 1em;
    }
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
      border-bottom: none;
      background-color: #fff;
    }
    .activeBtn {
      /* background-color: #fafafa !important; */
      border: 1px solid #ffde4d !important;
    }
    #form {
      padding: 30px 0;
      margin: 0 auto;
      margin-top: 9px;
      /* margin-bottom: 9px; */
      width: 700px;
      box-shadow: 0px 1px 3px 1px #d0d0d0;
      /* border-radius: 1; */
      border-top: 1px solid #ffde4d;
      border-bottom-left-radius: 10px;
      border-bottom-right-radius: 10px;
      background-color: #fff;
    }
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
<body ondragstart="return false" onselectstart="return false">
<div id="loading_Bar"></div>
<jsp:include page="/include/header.jsp"></jsp:include>
  <div style="background-color: #fafafa; padding-bottom: 100px;">
    <div class="width">
      <div id="form">
      	<form name="companyForm" method="post" class="was-validated" action="${ctp}/createCPOk.us" enctype="multipart/form-data">
        <div id="company" class="text-center">
        <div style="margin-top: 50px; font-size: 1.3em;"> 기업 회원용 </div>
        <span style="color: #bbb;">부적합한 내용으로 인해 관리자 승인을 받지 못할 경우<br/>1대1 문의를 통해 수정을 요청하실수 있습니다.</span>
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
              [필수]로고 이미지를 넣어주세요.(jpg,png파일만 허용합니다. 최대 10MByte)
            </div>
          </div>
          <div class="col-2"></div>
        </div>
        <div class="row item-row">
          <div class="col-3"></div>
          <div class="col-6 d-flex fCol_center" style="margin-top: 30px;">
              <input type="button" onclick="cpCreateCheck()" value="회원가입" class="btn btn-warning">
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
<jsp:include page="/include/footer.jsp"></jsp:include>
<script src="include/viewPage.js"></script>
</body>
</html>