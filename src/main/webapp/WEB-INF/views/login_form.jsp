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
<title>Insert title here</title>
</head>
<body>
	<img src="<c:url value='/resources/images/zelshop.png'/>" class="logo"/>

	<div class="login-box">

		<h2>Вход в систему</h2>

		<c:if test="${msg!=''}">
			<div class="error">${msg}</div>
		</c:if>

		<form:form commandName="loginForm"
		  action="login" method="post">

		  <table>
			<tr>
				<td>Имя:</td>
				<td><form:input path="username" type='text'/></td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><form:input path="password" type='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		  </table>
		</form:form>
	</div>
	
</body>
</html>