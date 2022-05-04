<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/coffee-style.css">

<head>
    <meta charset="UTF-8">
    <title>Buy a Drink</title>
</head>
<body>
<div class="header">
    <h1>Coffee</h1>
</div>

<div class="menu">
    <a href="coffee-servlet">Home</a>
    <a class="active" href="coffee-servlet?action=buy">Buy</a>
    <c:if test="${role=='admin'}"><a href="coffee-servlet?action=service">Service</a></c:if>
    <a href="coffee-servlet?action=log-out">Log Out</a>
</div>
<form method="post" action="coffee-servlet">
    <input name="action" value="order" style="visibility: hidden"/>
    <p class="header-like">Select a Drink:</p>
    <c:forEach items="${drinks}" var="drink">
        <input required type="radio" name="drink" id=${drink.id} value=${drink.id}>
        <label for=${drink.id}>${drink.name}</label><br>
    </c:forEach>
    <br>

    <p class="header-like">Select Additional Components:</p>
    <c:forEach items="${ingrs}" var="ingr">
        <input type="checkbox" name="ingr" id=${ingr.id} value=${ingr.id}>
        <label for=${ingr.id}>${ingr.name}</label>
    </c:forEach>
    <br><br>

    <input class="big-button" type="submit" value="Buy">
</form>
</body>
</html>