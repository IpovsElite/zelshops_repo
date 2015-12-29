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
<title>Добавление магазина</title>
</head>
<body>
	<ul>
		<span class="ul-ri">
			<c:if test="${empty currentUser}">
			<a href="login">Вход</a>
			</c:if>
		</span>
		<c:if test="${not empty currentUser}">
		
		
		<span class="ul-ri"><a href="logout">Выход</a></span>
		<span class="ul-ri"><t>${currentAccess}</t></span>
		<span class="ul-ri"><t>${currentUser.username}</t></span>
		</c:if>
		<span class="ul-li"><a href="search">На главную</a></span>
	</ul>
	<img src="<c:url value='/resources/images/zelshop.png'/>" class="logo"/>
	<c:if test="${not empty msg}"> 
		<c:if test="${not empty errors['name'] || not empty errors['address'] || errors['site'] == 'Есть незаполненные поля' || errors['telephone'] == 'Есть незаполненные поля' }"> 
		<div class="error">Есть незаполненные поля</div> 
		</c:if> 
		<c:if test="${errors['site'] == 'Неверный формат URL.'}"> 
		<div class="error">${errors['site']}</div> 
		</c:if> 
		<c:if test="${errors['telephone'] == 'Неверный формат номера телефона.'}"> 
		<div class="error">${errors['telephone']}</div> 
		</c:if> 		
	</c:if>
	
	<div class="update-shop-box">

		<h2>Добавление магазина</h2>
		<hr>
		
		<form:form commandName="shopForm"
		  action="addshop" method="post">

		  <table>
			<tr>
				<td>Название:</td>
				<td><form:input path="name" type='text'/></td><td>
				<c:if test="${not empty errors['name']}"> <img src="<c:url value='/resources/images/error.png'/>" height="15px"></c:if></td>
			</tr>
			<tr>
				<td>Сайт:</td>
				<td><form:input path="site" type='text'/></td>
				<td><c:if test="${not empty errors['site']}"> <img src="<c:url value='/resources/images/error.png'/>" height="15px"></c:if>
				</td>
			</tr>
			<tr>
				<td>Адрес:</td>
				<td colspan='2'><form:input path="address" type='text' /></td>
			</tr>
			<tr>
				<td>Телефон:&nbsp;&nbsp;&nbsp;&nbsp;+7</td>
				<td> <form:input path="telephone" type='text'/></td><td>
				<c:if test="${not empty errors['telephone']}"> <img src="<c:url value='/resources/images/error.png'/>" height="15px"></c:if>
				</td>
			</tr>
			<tr>
				<td>Сфера:</td>
				<td colspan='2'><form:select path="spec" items="${specList}" /></td>
			</tr>
			<tr>
				<td>Описание:</td>
				<td colspan='2'><form:input path="description" type='text'/></td>
			</tr>		
			<tr>
				<td>Широта(Lat):</td>
				<td colspan='2'><form:input path="lat" type='text' /></td>
			</tr>
			<tr>
				<td>Долгота(Lng):</td>
				<td colspan='2'><form:input path="lng" type='text' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Добавить" /></td>
			</tr>
		  </table>
		</form:form>
	</div>
</body>
</html>