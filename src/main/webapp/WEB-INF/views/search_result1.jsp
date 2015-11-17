<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Справочник магазинов Зеленограда</title>
</head>
<body>
    <div align="center">
        <form:form action="search" method="post" commandName="searchForm">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Найти магазины в Зеленограде</h2></td>
                </tr>
                <tr>
                    <td><form:input path="pattern" /></td>
                    <td><input type="submit" value="Найти" /></td>
                </tr>
                <tr>
                    <td>Сфера:</td>
                    <td><form:select path="spec" items="${specList}" /></td>
                </tr>
            </table>
        </form:form>
    </div>
      <div align="center">
      		<c:set var="first" value="${isFirstVisit}"/>
      		<c:if test="${!first}">
            <h1>Результаты</h1>
            </c:if>
                <c:forEach var="shop" items="${shopList}" varStatus="status">
                <tr>
                  <table border="0">
                  <tr>
                    <th>Название</th>
                    <td>${shop.name}</td>
                  </tr>
                  <tr>
                    <th>Адрес</th>
                    <td>${shop.address}</td>
                  </tr>
                  <c:set var="site" value="${shop.site}"/>
                  <c:if test="${site != 'нет'}">
                  <tr>
                    <th>Сайт</th>
                    <td><a href="http://${shop.site}">${shop.site}</a></td>
                  </tr>
                  </c:if>
                  <tr>
                    <th>Телефон</th>
                    <td>${shop.telephone}</td>
                  </tr>
                  <tr>
                    <th>Сфера</th>
                    <td>${shop.spec}</td>
                  </tr>
                </table>
                </tr>
                </c:forEach>              
            </table>
        </div>
</body>
</html>