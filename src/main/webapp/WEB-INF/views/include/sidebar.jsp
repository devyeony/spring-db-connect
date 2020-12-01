<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/main">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SDTM<sup>2</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/main">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        List
    </div>
	
    <!-- 데이터 관리 메뉴 -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#dataManage" aria-expanded="true" aria-controls="dataManage">
            <i class="fas fa-fw fa-folder"></i>
            <span>데이터 관리</span>
        </a>
        <div id="dataManage" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Data Management</h6>
                <sec:authorize access="hasRole('ROLE_INPUT')">
                	<a class="collapse-item" href="${pageContext.request.contextPath}/insertIntoPostgre">데이터 입력</a>
                </sec:authorize>
                <a class="collapse-item" href="${pageContext.request.contextPath}/getJsonLog">Json 파일 관리</a>
                <a class="collapse-item" href="${pageContext.request.contextPath}/getMetadataAndProgram?data=all">전사 데이터 목록</a>
            </div>
        </div>
    </li>
    
     <!-- 작업 관리 메뉴 -->
   <sec:authorize access="hasRole('ROLE_ADMIN')">
	   <li class="nav-item">
	       <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#workManage" aria-expanded="true" aria-controls="workManage">
	           <i class="fas fa-user-friends"></i>
	           <span>작업 관리</span>
	       </a>
	       <div id="workManage" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
	           <div class="bg-white py-2 collapse-inner rounded">
	               <h6 class="collapse-header">Work Status Management</h6>
	               <a class="collapse-item" href="${pageContext.request.contextPath}/getWorkListByAdmin">작업 관리</a>
	           </div>
	       </div>
	   </li>
   </sec:authorize>
	
	<!-- 룰 관리 메뉴 -->
   <sec:authorize access="hasRole('ROLE_ADMIN')">
	   <li class="nav-item">
	       <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#ruleManage" aria-expanded="true" aria-controls="ruleManage">
	           <i class="fas fa-user-friends"></i>
	           <span>RULE 관리</span>
	       </a>
	       <div id="ruleManage" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
	           <div class="bg-white py-2 collapse-inner rounded">
	               <h6 class="collapse-header">룰 관리</h6>
	               <!-- <a class="collapse-item"	href="#" data-toggle="collapse" data-target="#ruleManage2" aria-expanded="true" aria-controls="ruleManage2" >
	               <span>발화자표기(중분류)</span>
	               </a> -->
	               <a class="collapse-item" href="${pageContext.request.contextPath}/editRuleByAdmin">룰 작성</a>
	               <a class="collapse-item" href="${pageContext.request.contextPath}/registerRuleByAdmin">룰 등록</a>
	               <a class="collapse-item" href="${pageContext.request.contextPath}/runRuleByAdmin">룰 실행</a>
	           </div>
	       </div>
	       
	       <%-- <div id="ruleManage2" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
	           	<div class="bg-white py-2 collapse-inner rounded">
	           	<h6 class="collapse-header">발화자표기(중분류)</h6>
	       	 	<a class="collapse-item" href="${pageContext.request.contextPath}/getRuleListByAdmin?role=ALL">화자 이름(소분류 가칭)</a>
	       	 	<a class="collapse-item" href="${pageContext.request.contextPath}/getRuleListByAdmin?role=ALL">화자 성별(소분류 가칭)</a>
	       	 	<a class="collapse-item" href="${pageContext.request.contextPath}/getRuleListByAdmin?role=ALL">화자 정보(소분류 가칭)</a>
	       	 </div>
	      </div> --%>
	   </li>
   </sec:authorize>

   <!-- 회원 관리 메뉴 -->
   <sec:authorize access="hasRole('ROLE_ADMIN')">
	   <li class="nav-item">
	       <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#memberManage" aria-expanded="true" aria-controls="memberManage">
	           <i class="fas fa-user-friends"></i>
	           <span>회원 관리</span>
	       </a>
	       <div id="memberManage" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
	           <div class="bg-white py-2 collapse-inner rounded">
	               <h6 class="collapse-header">Member Management</h6>
	               <a class="collapse-item" href="${pageContext.request.contextPath}/getMemberListByAdmin?role=ALL">회원 관리</a>
	           </div>
	       </div>
	   </li>
   </sec:authorize>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
<!-- End of Sidebar -->