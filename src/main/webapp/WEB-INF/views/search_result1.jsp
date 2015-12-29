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
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
var initialLocation;
var siberia = new google.maps.LatLng(60, 105);
var newyork = new google.maps.LatLng(40.69847032728747, -73.9514422416687);
var browserSupportFlag =  new Boolean();

function initialize() {
  var myOptions = {
    zoom: 18,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById("googleMap"), myOptions);
 
  // Try W3C Geolocation (Preferred)
  if(navigator.geolocation) {
    browserSupportFlag = true;
    navigator.geolocation.getCurrentPosition(function(position) {
      initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
      map.setCenter(initialLocation);
      document.getElementById("curLat").value=position.coords.latitude;
      document.getElementById("curLng").value=position.coords.longitude;
      var marker=new google.maps.Marker({
    	  position:initialLocation,
    	  });

    	marker.setMap(map);
    }, function() {
      handleNoGeolocation(browserSupportFlag);
    });
  }
  // Browser doesn't support Geolocation
  else {
    browserSupportFlag = false;
    handleNoGeolocation(browserSupportFlag);
  }

  function handleNoGeolocation(errorFlag) {
    if (errorFlag == true) {
      alert("Geolocation service failed.");
      initialLocation = newyork;
    } else {
      alert("Your browser doesn't support geolocation. We've placed you in Siberia.");
      initialLocation = siberia;
    }
    map.setCenter(initialLocation);
    
  }
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
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
		<c:if test="${currentAccess == 'Администратор' and not empty currentUser }">
		<span class="ul-li"><a href="addshop">Добавить магазин</a></span>
		<span class="ul-li"><a href="newshops">Новые магазины</a></span>
		<span class="ul-li"><a href="checkshops">Магазины, требующие проверки</a></span>
		<span class="ul-li"><a href="inactiveshops">Неактивные магазины</a></span>
		</c:if>
	</ul>
	<div class="location-block"><a href="currentloc" class="location"><img src="<c:url value='/resources/images/aim.jpg'/>" class="loc-image">Ваше местоположение</a></div>
	<img src="<c:url value='/resources/images/zelshop.png'/>" class="logo"/>
    <div align="center">
        <form:form action="search" method="post" commandName="searchForm">
            <div class="search-block">
				<form:select path="spec" items="${specList}" class="sel"/>
            	<form:input path="pattern" class="search"/>
            	<input type="submit" value="" class="search-icon"/>
            	<form:input path="currentLat" type="hidden" id="curLat"/>
            	<form:input path="currentLng" type="hidden" id="curLng"/>
            	<div class="geo-check"><form:input path="currentLng" type="hidden" id="curLng"/>
				<b>Искать ближайшие</b> <form:checkbox path="isGeoEnabled" value="false"/></div> 	
            </div>
        </form:form>
    </div>
    <div id="googleMap"></div>
      		<c:set var="first" value="${isFirstVisit}"/>
      		<c:if test="${!first}">
            <c:if test="${empty shopList && !first}">
            <h2>Ничего не найдено</h2>
            </c:if>
            </c:if>
            	<div class="shops">
                <c:forEach var="shop" items="${shopList}" varStatus="status">
                
                
                <div class = "shop"><b>${shop.name}</b>
                	<div class = "crop"><img src = "resources/images/5ka.jpg" class = "shop-image"></div>
                	<div class = "info">
                		<c:if test="${shop.site != 'нет'}">
                		<strong>Cайт: </strong><a href="http://${shop.site}">${shop.site}</a><br>
                		</c:if>
						<strong>Адрес: </strong>${shop.address}<br>
						<c:if test="${shop.telephone != 'нет'}">
						<strong>Телефон: </strong>${shop.telephone}<br>
						</c:if>
						<strong>Сфера: </strong>${shop.spec}<br>
						<c:if test="${currentAccess == 'Администратор' and not empty currentUser}">
						<a href="updateshop?id=${shop.id}">Изменить</a>
						<a href="deleteshop?id=${shop.id}">Удалить</a>
						</c:if>
						<c:if test="${empty currentUser}">
						<a href="checkshop?id=${shop.id}">Информация неверна</a>
						</c:if>
					</div>	
                	

                	</div>
                </c:forEach>              
           </div>
        
       
</body>
</html>