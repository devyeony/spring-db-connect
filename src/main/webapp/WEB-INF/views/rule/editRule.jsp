<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>SDTM</title>

	<script
			src="${pageContext.request.contextPath}/resource/js/jquery-3.5.1.min.js"></script>

	<script
			src="${pageContext.request.contextPath}/resource/vendor/codemirror/codemirror.js"></script>
	<link
			rel="stylesheet" href="${pageContext.request.contextPath}/resource/vendor/codemirror/codemirror.css">
	<link
			rel="stylesheet" href="https://codemirror.net/theme/hopscotch.css">
	<script
			src="${pageContext.request.contextPath}/resource/vendor/codemirror/clike.js"></script>

	<!-- Custom fonts for this template-->
	<link
			href="${pageContext.request.contextPath}/resource/vendor/fontawesome-free/css/all.min.css"
			rel="stylesheet" type="text/css">
	<link
			href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
			rel="stylesheet">

	<!-- Custom styles for this template-->
	<link
			href="${pageContext.request.contextPath}/resource/css/sb-admin-2.min.css"
			rel="stylesheet">

	<!-- w3 css -->
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

	<style>
		#code_area{
			background: #322931;
			padding: 10px;
		}

		.{
			font-size: 1em;
			font-family: Nunito,-apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
		}

		.codeWhite{ color: white; }
		.codeRed{ color: #dd464c; }
		.codeOrange{ color: #fd8b19; }
		.codeYellowGreen{ color: #8fc13e }
		.codeRemoveMargin{ margin-left:-3px; margin-right:-3px; }

		.loading {
			position: absolute;
			top: 50%;
			left: 50%;
			width: 80px;
			height: 80px;
			margin: -50px 0 0 -50px;
		}
	</style>
</head>

<body id="page-top">
	<form id="deleteRuleFrm">
		<input type="hidden" name="level" value="bottom">
		<input type="hidden" name="top_level_id" value="${rule.top_level_id}">
		<input type="hidden" name="top_level_name" value="${rule.top_level_name}">
		<input type="hidden" name="middle_level_id" value="${rule.middle_level_id}">
		<input type="hidden" name="middle_level_name" value="${rule.middle_level_name}">
		<input type="hidden" name="bottom_level_id" value="${rule.bottom_level_id}">
		<input type="hidden" name="bottom_level_name" value="${rule.bottom_level_name}">
	</form>

	<!-- 업로드시 로딩 화면 -->
	<div id="loadingArea" class="w3-modal w3-animate-opacity">
		<img class="loading" width="100px"
			 src="${pageContext.request.contextPath}/resource/img/loading.gif">
	</div>

	<input id="msg" type="hidden" value="${msg}">

<!-- Page Wrapper -->
<div id="wrapper">

	<!-- 사이드바 include-->
	<%@ include file="/WEB-INF/views/include/sidebar.jsp"%>

	<!-- Content Wrapper -->
	<div id="content-wrapper" class="d-flex flex-column">

		<!-- Main Content -->
		<!-- 이 부분만 바꿔주면 됩니다 -->
		<div id="content">
			<!-- 툴바 include -->
			<%@ include file="/WEB-INF/views/include/toolbar.jsp"%>
			<!-- Begin Page Content -->
			<div class="container-fluid">

				<!-- Page Heading -->
				<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
					<h1 class="h3 mb-2 text-gray-800" style="display: inline-block">
						<b>룰 작성 - 메서드</b>
						<a href="${pageContext.request.contextPath}/rule/getRuleVersionList?data=${rule.bottom_level_id}&current_page_no=1&count_per_page=10&count_per_list=10&search_word=" style="font-size:0.6em;">버전 관리</a>
					</h1>
				</div>
				
				<!-- 룰 정보 -->
				<%@ include file="/WEB-INF/views/rule/include/ruleInfo.jsp"%>

					<!-- Page Body -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<br/>
							<div style="font-size: 1.2em;">
								제시된 양식을 참고하여 Java 코드를 작성하세요 
								<a class="small" id="goToRuleApi" href="#" data-dismiss="modal" data-toggle="modal"
									data-target="#ruleApiModal" style="color: #4e73df;"> API 문서 보기 </a>
							</div>
							<br />
							<form
								action="${pageContext.request.contextPath}/rule/saveRuleContents"
								id="editRuleFrm">
							<input name="${_csrf.parameterName}" type="hidden"
								   value="${_csrf.token}" />
							<div id="code_area" style="font-size: 1.2em; font-weight: bold;">
								<div style="margin-left:0.23%;">
									<span class="codeRed">package</span>
									<span class="codeYellowGreen">kr</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">com</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">inspect</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">rule</span>
									<span class="codeWhite codeRemoveMargin">;</span>
								</div><br/>
								<div style="margin-left: 0.23%;">
									<span class="codeRed">import</span>
									<span class="codeYellowGreen">kr</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">com</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">inspect</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">rule</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">Data</span>
									<span class="codeWhite codeRemoveMargin">;</span>
									<br/>
									<span class="codeRed">import</span>
									<span class="codeYellowGreen">kr</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">com</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">inspect</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">dto</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">Metadata</span>
									<span class="codeWhite codeRemoveMargin">;</span>
									<br/>
									<span class="codeRed">import</span>
									<span class="codeYellowGreen">kr</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">com</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">inspect</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">dto</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">Program</span>
									<span class="codeWhite codeRemoveMargin">;</span>
									<br/>
									<span class="codeRed">import</span>
									<span class="codeYellowGreen">kr</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">com</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">inspect</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">dto</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">Speaker</span>
									<span class="codeWhite codeRemoveMargin">;</span>
									<br>
									<span class="codeRed">import</span>
									<span class="codeYellowGreen">kr</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">com</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">inspect</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">dto</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">Utterance</span>
									<span class="codeWhite codeRemoveMargin">;</span>
									<br>
									<span class="codeRed">import</span>
									<span class="codeYellowGreen">kr</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">com</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">inspect</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">dto</span>
									<span class="codeWhite codeRemoveMargin">.</span>
									<span class="codeYellowGreen">EojeolList</span>
									<span class="codeWhite codeRemoveMargin">;</span>
								</div>
								<div style="height: 100px;"><br/>
									<div style="margin-left: 0.23%;">
									/* 아래에 필요한 라이브러리를 import 하세요 */
									<a class="small" id="goToRuleCustom" href="#" data-dismiss="modal" data-toggle="modal"
								data-target="#customLibraryModal" style="margin-left:5px; font-size:1em; color: #4e73df;"> 라이브러리 추가 </a>
									</div>
									<textarea id="imp_contents" name="imp_contents" cols="170" rows="20"
											  style="height: 50px;">${rule.imp_contents}</textarea>
								</div><br/><br/>
								<div style="margin-left: 0.23%;">
									<span class="codeRed">public class</span>
									<span class="codeOrange"> ${rule.file_name}</span>
									<span class="codeWhite">{</span>
								</div>
								<div style="margin-left: 3%;">
									<span class="codeRed">public </span>
									<span class="codeWhite">Object </span>
									<span class="codeOrange">run</span>
									<span class="codeWhite" style="margin-left:-5px;">()</span>
									<span class="codeWhite">throws </span>
									<span class="codeYellowGreen">Exception </span>
									<span class="codeWhite">{</span>
								</div>
								<div style="margin-left: 5%; height: 300px;">
									<div style="margin-left: 1%;">/* 아래에 코드를 입력하세요 */</div>
									<textarea id="contents" name="contents" cols="170" rows="20"
											  style="resize: none;">${rule.contents}</textarea>
								</div>

								<div style="margin-left: 3%;">
									<span class="codeWhite">}</span>
								</div>
								<div style="margin-bottom: 5px;">
									<span class="codeWhite">}</span>
								</div>
							</div>
							<input name="bottom_level_id" type="hidden" id="bottom_level_id"
								   value="${rule.bottom_level_id}">
							<br/>
							<div id="show_result_after_update">
								<b>실행결과</b>
								<textarea class="form-control" rows="5" style="resize: none;"
										  readonly></textarea>
							</div>
							<div style="display: block; margin-top: 5px;">
								<button class="btn btn-danger" type="button" id="deleteRuleBtn"
										style="float: left;">삭제</button>
								<button id="ruleUpdateBtn" type="button"
										class="btn btn-primary" style="float: right;">작성</button>
								<button class="btn btn-secondary" type="button"
										id="backRuleBtn" style="float: right; margin-right: 5px;">돌아가기</button>
							</div>
						</form>
					</div>
					<!-- card-body -->
				</div>
				<!-- card shadow mb-4 -->
			</div>
			<!-- container-fluid -->
		</div>
		<!-- content -->
	</div>
	<!-- content-wrapper -->
</div>
<!-- wrapper -->
</body>
<!-- /.container-fluid -->
<!-- End of Main Content -->

<!-- Rule API 문서 Modal -->
<%@ include file="ruleApi.jsp"%>

<!-- Rule Custom Modal -->
<%@ include file="customLibrary.jsp"%>

<!-- footer include-->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

<!-- End of Content Wrapper -->

<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
	<i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script
		src="${pageContext.request.contextPath}/resource/vendor/jquery/jquery.min.js"></script>
<script
		src="${pageContext.request.contextPath}/resource/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script
		src="${pageContext.request.contextPath}/resource/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script
		src="${pageContext.request.contextPath}/resource/js/sb-admin-2.min.js"></script>
<script 
		src="${pageContext.request.contextPath}/resource/js/rule/ruleCategory.js"></script>
<script
		src="${pageContext.request.contextPath}/resource/js/rule/editRule.js"></script>
<script
		src="${pageContext.request.contextPath}/resource/js/rule/ruleApi.js"></script>
</body>

</html>