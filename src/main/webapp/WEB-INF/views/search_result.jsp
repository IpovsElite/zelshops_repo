<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            <h1>Shop List</h1>
            <h3><a href="/newContact">New Contact</a></h3>
            <table border="0">
                <th>No</th>
                <th>Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Telephone</th>
                <th>Action</th>
                 
                <c:forEach var="shop" items="${shopList}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${shop.name}</td>
                    <td>${shop.site}</td>
                    <td>${shop.address}</td>
                    <td>${shop.telephone}</td>
                    <td>
                        <a href="/editContact?id=${contact.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/deleteContact?id=${contact.id}">Delete</a>
                    </td>
                             
                </tr>
                </c:forEach>              
            </table>
        </div>
</body>
</html>