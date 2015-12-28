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
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
<title>ZELshops.ru - только лучшие магазины Зеленограда</title>
</head>
<body>
	<ul>
		<span class="ul-li"><a href="search">На главную</a></span>
	</ul>
	<img src="<c:url value='/resources/images/zelshop.png'/>" class="logo"/>

	<c:if test="${not empty msg}">
			<div class="error">Неправильный логин или пароль</div>
	</c:if>

	<div class="login-box">

		<form:form commandName="loginForm"
		  action="login" method="post">
		<label class="login-label" for="username">Логин<br></label> <form:input path="username" type='text' class="login-input"/><br>
		<label class="login-label" for="password">Пароль<br></label> <form:input path="password" type='password' class="login-input"/><br>
		<input name="submit" type="submit" value="Войти" class="login-button"/>
		</form:form>
	</div>
	
</body>
</html>