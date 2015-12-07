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
<div id="googleMap" style="width:500px;height:380px;"></div>
</body>
</html>