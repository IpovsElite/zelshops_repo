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
<spring:url value="/resources/style.css" var="styleCSS"/>
<spring:url value="/resources/zelshop.png" var="zelshopPNG"/>
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
<title>Сообщение об ошибке</title>
</head>
<body>
<ul>	
		<li><a href="search">На главную</a></li>
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
		<c:if test="${currentAccess == 'Администратор' and not empty currentUser }">
		<li><a href="addshop">Добавить магазин</a></li>
		<li><a href="newshops">Новые магазины</a></li>
		<li><a href="checkshops">Магазины, требующие проверки</a></li>
		<li><a href="inactiveshops">Неактивные магазины</a></li>
		</c:if>
	</ul>
	
  <img src="<c:url value='/resources/images/zelshop.png'/>" class="logo"/>
  <div align="center">
  <div class="shops">
  <div class = "shop"><b>${shop.name}</b>
  <div class = "crop"><img src = "resources/images/5ka.jpg" class = "shop-image"></div>
  <div class = "info">
           <c:if test="${shop.site != 'нет'}">
              <strong>Cайт:</strong><a href="http://${shop.site}">${shop.site}</a><br>
           </c:if>
		   <strong>Адрес:</strong>${shop.address}<br>
		   <c:if test="${shop.telephone != 'нет'}">
			 <strong>Телефон:</strong>${shop.telephone}<br>
		   </c:if>
		   <strong>Сфера:</strong>${shop.spec}<br>
  </div>
  </div>
  <form:form action="checkshop?id=${shop.id}" method="post" commandName="comment">
  <table>
	<tr>
	<td><form:textarea path="text" rows="5" cols="30"/></td>
	</tr>
    <tr>
	<td colspan='2'><input name="submit" type="submit" value="Отправить" /></td>
	</tr>
  </table>
  </form:form>
  </div>
</body>
</html>