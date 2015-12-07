<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<spring:url value="/resources/zelshop.png" var="zelshopPNG"/>
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
<title>Ваше местоположение</title>
</head>
<body>
<ul>
		<ri>
			<c:if test="${empty currentUser}">
			<a href="login">Вход</a>
			</c:if>
		</ri>
		<c:if test="${not empty currentUser}">
		
		
		<ri><a href="logout">Выход</a></ri>
		<ri><t>${currentAccess}</t></ri>
		<ri><t>${currentUser.username}</t></ri>
		</c:if>
		<li><a href="search">На главную</a></li>
	</ul>
<img src="<c:url value='/resources/images/zelshop.png'/>" class="logo"/>
</body>
</html>