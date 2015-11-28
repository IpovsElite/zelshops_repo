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


<title>ZELshops.ru - только лучшие магазины Зеленограда</title>
</head>
<body>
	<img src="<c:url value='/resources/images/zelshop.png'/>" class="logo"/>
    <div align="center">
        <form:form action="search" method="post" commandName="searchForm">
            <div class="search-block">
            	<form:input path="pattern" class="search"/><br/>
            	<input type="submit" value="" class="search-icon"/>
            	<div class="sel">
            		<form:select path="spec" items="${specList}" />
            	</div>
            </div>
        </form:form>
    </div>
      <div align="center">
      		<c:set var="first" value="${isFirstVisit}"/>
      		<c:if test="${!first}">
            <h1>Результаты</h1>
            </c:if>
            
                <c:forEach var="shop" items="${shopList}" varStatus="status">
                <div class="shops">
                	<div class = "shop">${shop.name}
                	<div class = "crop"><img src = "resources/images/5ka.jpg" class = "shop-image"></div>
                	<div class = "info">
                		<strong>Cайт:</strong><a href="http://${shop.site}">${shop.site}</a><br>
						<strong>Адрес:</strong>${shop.address}<br>
						<strong>Телефон:</strong>${shop.telephone}<br>
						<strong>Сфера:</strong>${shop.spec}<br>
					</div>	
                	</div>
            
                	</div>
                </c:forEach>              
           
        
        </div>
</body>
</html>