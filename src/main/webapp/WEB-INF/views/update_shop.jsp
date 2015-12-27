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
<title>Изменение магазина</title>
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
	<div class="update-shop-box">

		<h2>Изменение магазина</h2>

		<c:if test="${not empty msg}">
			<div class="error">Есть незаполненные поля</div>
		</c:if>

		<form:form commandName="updateShopForm"
		  action="updateshop" method="post">
		<label for="name">Название:</label><form:input path="name" type='text'/><br>
		<label for="">Сайт:</label> <form:input path="site" type='text'/><br>
		<label for="">Адрес:</label> <form:input path="address" type='text'/><br>
		<label for="">Телефон:</label> <form:input path="telephone" type='text'/><br>
		<label for="">Сфера:</label> <form:select path="spec" items="${specList}" /><br>
		<label for="">Широта:</label> <form:input path="lat" type='text' /><br>
		<label for="">Долгота:</label> <form:input path="lng" type='text' /><br>
		<input name="submit" type="submit" value="Изменить" /><br>
		</form:form>
	</div>
</body>
</html>