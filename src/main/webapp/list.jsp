<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sonvu
  Date: 08/06/2022
  Time: 9:37 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Tìm Kiếm</h1>
<form method="post">
    <input type="text" name="name">
    <input type="hidden" name="action" value="search">
    <input type="submit" value="Tìm Kiếm">
</form>
<h2>Tìm kiếm theo giá</h2>
<form method="post">
    <input type="number" name="start" placeholder=" giá bắt đầu">
    <input type="number" name="end" placeholder=" giá kết thúc">
    <input type="hidden" name="action" value="searchPrice">
    <input type="submit" value="tìm kiếm">
</form>
<h1>Danh Sách Khuyến Mãi</h1>
<c:forEach var="list" items="${products}">
    <c:if test="${list.price>200}">
        <h3>${list.name} , ${list.price} (Khuyến mãi 10%)</h3>
    </c:if>
    <c:if test="${list.price<200}">
        <h3>${list.name} , ${list.price} (Khuyến mãi 20%)</h3>
    </c:if>
</c:forEach>
</body>
</html>
