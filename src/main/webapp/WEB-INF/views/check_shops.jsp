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
<title>Магазины, требующие проверки</title>
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
<h2>Магазины, требующие проверки</h2>
<div class="shops">
                <c:forEach var="shop" items="${shopList2}" varStatus="status">
                
                
                <div class = "shop">${shop.name}<br>
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
						<a href="makeinactive?id=${shop.id}">Сделать неактивным</a><br>
						<a href="makeactive?id=${shop.id}">Сделать активным</a><br>
						<a href="updateshop?id=${shop.id}">Изменить</a>
						<a href="deleteshop?id=${shop.id}">Удалить</a><br>
						<a href="watchcomments?id=${shop.id}">Посмотреть комментарии</a><br>
					</div>	
                	

                	</div>
                </c:forEach>              
           </div>
</body>
</html>