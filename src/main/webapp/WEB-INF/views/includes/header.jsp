<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
%>

<div id="header">
	<h1><a href="<%=request.getContextPath()%>/main">MySite</a></h1>
	<ul>
		<c:choose>
			<c:when test='${ empty authUser }'>
				<li><a href="${pageContext.servletContext.contextPath }/user?a=loginform">로그인</a>
				<li>
				<li><a href="${pageContext.servletContext.contextPath }/user?a=joinform">회원가입</a>
				<li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath }/user?a=updateform">회원정보수정</a>
				<li>
				<li><a href="${pageContext.servletContext.contextPath }/user?a=logout">로그아웃</a>
				<li>
				<li>${ authUser.name } 님 안녕하세요 ^^;</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>